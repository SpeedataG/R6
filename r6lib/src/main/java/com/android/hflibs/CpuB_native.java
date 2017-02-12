package com.android.hflibs;


public class CpuB_native
{
	private static final String DEVPATH = "/dev/rc663";
	
	public int InitDevice()
	{
		return init_dev(DEVPATH);
	}
	
	public void ReleaseDevice()
	{
		release_dev();
	}
	
	public byte[][] SearchCard()
	{
		return search_card();
	}
	
	public byte[] Exec_cmd(byte[] i)
	{
		return exec_apdu(i);
	}
	
	public int Deselect()
	{
		return deselect();
	}
	
	private native int init_dev(String path);
	private native void release_dev();
	private native byte[][] search_card();
	private native byte[] exec_apdu(byte[] a);
	private native int deselect();
	
	static {
		System.loadLibrary("rc663nxp-cpu");
		System.loadLibrary("package-cpu");
		System.loadLibrary("rc663_cpuB");
	}
}