package com.speedata.r6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.speedata.r6lib.IMifareManager;
import com.speedata.r6lib.R6Manager;

import static com.android.hflibs.Mifare_native.AUTH_TYPEA;
import static com.speedata.r6lib.R6Manager.CardType.MIFARE;


public class MifareActivity extends AppCompatActivity implements OnClickListener {

	private static final String TAG = "mifare";
	private IMifareManager dev;
	private Button start_demo;
	private TextView main_info;
	private EditText block_nr;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mifare);
        Log.d(TAG, "in oncreate");
       
        start_demo = (Button)findViewById(R.id.button_mifare_set);
        start_demo.setOnClickListener(this);
        start_demo.setEnabled(false);
        
        main_info = (TextView)findViewById(R.id.textView_mifare_info);
        main_info.setMovementMethod(ScrollingMovementMethod.getInstance());
        block_nr = (EditText)findViewById(R.id.editText_mifare_block);
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
        
        Log.d(TAG, "begin to init");
		dev = R6Manager.getMifareInstance(MIFARE);
        if(dev.InitDev() != 0)
        {
        	main_info.setText(R.string.msg_error_dev);
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
    

	@Override
	public void onClick(View arg0) {

		if(arg0 == start_demo)
		{
			int block;
//			main_info.setText(R.string.msg_start);
			try
			{
				block = Integer.valueOf(block_nr.getText().toString()).intValue();
			}
			catch(NumberFormatException p)
			{
				main_info.setText(R.string.msg_error_input);
				return;
			}
/*			if(block < 0)
			{
//				block = 0;
//				block_nr.setText("" + block);
				return;
			}*/
			if(block >= 64)
			{
//				block = 63;
//				block_nr.setText("" + block);
				Toast t = Toast.makeText(getApplicationContext(), "Rang 0 ~ 63", Toast.LENGTH_SHORT);
				t.setGravity(Gravity.TOP, 0, 0);
				t.show();
				return;
			}
			
			//search a valid card
			byte[] ID = dev.SearchCard();
			if(ID == null)
			{
				main_info.setText(R.string.msg_mifare_error_nocard);
				return;
			}
			String IDString = new String(" 0x");
			for(byte a : ID)
			{
				IDString += String.format("%02X", a);
			}
			main_info.setText(R.string.msg_mifare_ok_findcard);
			main_info.append(IDString);
			main_info.append("\n\n");
			
			//auth the block to read/write
			byte[] key = new byte[6];
			for(int i = 0; i < 6; i++)
			{
				key[i] = (byte)0xff;
			}
			if(dev.AuthenticationCardByKey(AUTH_TYPEA, ID, block, key) != 0)
			{
				main_info.append(getString(R.string.msg_mifare_error_auth));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_auth));
			main_info.append("\n\n");
			
			//write data to block directly
			byte[] data = new byte[16];
			String dataString = new String();
			for(int i = 0; i < 16; i++)
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
			
			//wrtie a value to block
			if(dev.WriteBlockValue(block, 66) != 0)
			{
				main_info.append(getString(R.string.msg_mifare_error_writevalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_writevalue));
			main_info.append(String.format(" %d\n\n", 66));
			
			//read the value
			int value[] = dev.ReadBlockValue(block);
			if(value == null)
			{
				main_info.append(getString(R.string.msg_mifare_error_readvalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_readvalue));
			main_info.append(String.format(" %d\n\n", value[0]));
			
			//increment the block's value
			if(dev.IncrementBlockValue(block, 12) != 0)
			{
				main_info.append(getString(R.string.msg_mifare_error_incvalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_incvalue));
			main_info.append(String.format(" %d\n\n", 12));
			
			//read the value again
			value = dev.ReadBlockValue(block);
			if(value == null)
			{
				main_info.append(getString(R.string.msg_mifare_error_readvalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_readvalue));
			main_info.append(String.format(" %d\n\n", value[0]));
			
			//decrement the block's value
			if(dev.DecrementBlockValue(block, 55) != 0)
			{
				main_info.append(getString(R.string.msg_mifare_error_decvalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_decvalue));
			main_info.append(String.format(" %d\n\n", 55));
			
			//read the value again
			value = dev.ReadBlockValue(block);
			if(value == null)
			{
				main_info.append(getString(R.string.msg_mifare_error_readvalue));
				return;
			}
			main_info.append(getString(R.string.msg_mifare_ok_readvalue));
			main_info.append(String.format(" %d\n\n", value[0]));
			
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