package com.speedata.r6lib;

/**
 * Created by xu on 2017/1/17.
 */

public interface IMifareManager {
    public int InitDev();
    public void ReleaseDev();
    public byte[] SearchCard();
    public int AuthenticationCardByKey(int type, byte[] cid, int block, byte[] key);
    public int WriteBlock(int block, byte[] data);
    public byte[] ReadBlock(int block);
    public int WriteBlockValue(int block, int value);
    public int[] ReadBlockValue(int block);
    public int IncrementBlockValue(int block, int value);
    public int DecrementBlockValue(int block, int value);
    public int HaltCard();


}
