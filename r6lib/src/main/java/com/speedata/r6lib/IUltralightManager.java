package com.speedata.r6lib;

/**
 * Created by xu on 2017/1/17.
 */

public interface IUltralightManager {
    public int InitDev();
    public void ReleaseDev();
    public byte[] SearchCard();
    public byte[] ReadBlock(int block);
    public int WriteBlock(int block, byte[] data);
    public int compatibility_Write_Block(int block, byte[] data);
    public int HaltCard();

}
