package com.android.hflibs;

//2014.4.9 add exchange_l4 & palp4a_rate and also change lib

public class CpuA_native {
	
	private static final String DEVPATH = "/dev/rc663";
	
	byte[] current_cid = null;
	
	public int InitDev()
	{
		return init_dev(DEVPATH);
	}
	
	public void ReleaseDev()
	{
		release_dev();
	}
	
	public byte[] SearchCard()
	{
		byte[] tag = search_card();
		if(tag == null)
		{
			return null;
		}
		current_cid = tag;
		return tag;
	}
	
	public int deselect()
	{
		current_cid = null;
		return de_select();
	}
	
	public byte[] exec_cmd(byte in[])
	{
		return exchange_l4(0, in);
	}
	
	public byte[] exec_rats()
	{
		return palp4a_rats(8, 0);
	}
	
	public int haltc()
	{
		return halt_card();
	}
	
	private native int init_dev(String path);
	private native void release_dev();
	private native byte[] search_card();
	private native int de_select();
	private native int halt_card();										//add 2016.3.3
	private native byte[] exchange_l4(int op, byte[] inbuf);			//add 2014.4.9
	public native byte[] palp4a_rats(int bFsdi, int bCid);				//add 2014.4.9
	//bFsdi：帧大小标识，取值0－8，对应的实际帧大小16－256字节。默认为0。
	//bCid：卡标识，取值0－14。默认为0。
	
	static {
		System.loadLibrary("rc663nxp-cpu");
		System.loadLibrary("package-cpu");
		System.loadLibrary("rc663_cpuA");
	}
}