package com.speedata.r6;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.speedata.r6lib.IUltralightManager;
import com.speedata.r6lib.R6Manager;

import static com.speedata.r6lib.R6Manager.CardType.ULTRALIGHT;


public class UltralightActivity extends AppCompatActivity implements OnClickListener{

	private static final String TAG = "ultralight";
	private IUltralightManager dev;
	private Button start_demo;
	private TextView main_info;
	private EditText block_nr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultralight);
        Log.d(TAG, "in oncreate");
       
        start_demo = (Button)findViewById(R.id.button_mifare_set);
        start_demo.setOnClickListener(this);
        start_demo.setEnabled(false);
        
        main_info = (TextView)findViewById(R.id.textView_mifare_info);
        block_nr = (EditText)findViewById(R.id.editText_mifare_block);
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
        
        Log.d(TAG, "begin to init");
		dev = R6Manager.getUltralightInstance(ULTRALIGHT);
        if(dev.InitDev() != 0)
        {
        	main_info.setText("msg_error_dev");
        	return;
        }
        Log.d(TAG, "init ok");
        start_demo.setEnabled(true);
    }

     @Override
       public void onDestroy()
    {
    	dev.ReleaseDev();
    	super.onDestroy();
    	Intent i = getIntent();
    	setResult(RESULT_OK, i);
    }
		public void onClick(View arg0)		 
		{

			if(arg0 == start_demo)
			{
				int block;
				main_info.setText("msg_start");
				try
				{
					block = Integer.valueOf(block_nr.getText().toString()).intValue();
				}
				catch(NumberFormatException p)
				{
					main_info.setText("msg_error_input");
					return;
				}
				if(block < 0)
				{
					block = 0;
					block_nr.setText("" + block);
				}
				if(block >= 16)
				{
					block = 15;
					block_nr.setText("" + block);
				}
				//search a valid card
				byte[] ID = dev.SearchCard();
				if(ID == null)
				{
					main_info.setText("msg_mifare_error_nocard");
					return;
				}
				String IDString = new String(" 0x");
				for(byte a : ID)
				{
					IDString += String.format("%02X", a);
				}
				main_info.setText("msg_mifare_ok_findcard");
				main_info.append(IDString);
				main_info.append("\n\n");
				
				//read data from the same block
				byte[] getdata = dev.ReadBlock(block);
				if(getdata == null)
				{
					main_info.append(getString(R.string.msg_mifare_error_readblock));
					return;
				}
				String getdataString = new String();
				for(byte i : getdata)
				{
					getdataString += String.format(" 0x%02x", i);
				}
				main_info.append(getString(R.string.msg_mifare_ok_readblock));
				main_info.append(getdataString);
				main_info.append("\n\n"); 
				
				//write data to block directly
				byte[] data = new byte[4];
				String dataString = new String();
				for(int i = 0; i < 4; i++)
				{
					data[i] = (byte)(i + 10);
					dataString += String.format(" 0x%02x", data[i]);
				}
				if(dev.WriteBlock(block, data) != 0)
				{
					main_info.append(getString(R.string.msg_mifare_error_writeblock));
					return;
				}
				main_info.append(getString(R.string.msg_mifare_ok_writeblock));
				main_info.append(dataString);
				main_info.append("\n\n");
				
				//write compatibility data to block directly
				byte[] data1 = new byte[16];
				String dataString1 = new String();
				for(int i = 0; i < 16; i++)
				{
					data1[i] = (byte)(i + 10);
					dataString1 += String.format(" 0x%02x", data1[i]);
				}
				if(dev.compatibility_Write_Block(block, data1) != 0)
				{
					main_info.append(getString(R.string.msg_mifare_error_compatibility_read));
					return;
				}
				main_info.append(getString(R.string.msg_mifare_ok_compatibility_read));
				main_info.append(dataString);
				main_info.append("\n\n");
				
				//halt current card
				if(dev.HaltCard() != 0)
				{
					main_info.append(getString(R.string.msg_mifare_error_haltcard));
					return;
				}
				main_info.append(getString(R.string.msg_mifare_ok_haltcard));
				main_info.append("\n\n");
				
				main_info.append(getString(R.string.msg_mifare_ok_allok));
				
			}	
		}

}
