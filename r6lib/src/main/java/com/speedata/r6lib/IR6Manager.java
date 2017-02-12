package com.speedata.r6lib;

/**
 * Created by brxu on 2016/12/27.
 */

public interface IR6Manager {
    public int InitDevice();
    public void ReleaseDevice();
    public byte[] SearchCard();
    public int Deselect();
    public byte[] ReadCard();
    public byte[] ExecCmdInput(byte[] input);
}
