package com.speedata.r6lib;

import com.android.hflibs.Mifare_native;

/**
 * Created by xu on 2017/1/3.
 */

public class Mifare implements IMifareManager {
    private Mifare_native devMifare = new Mifare_native();

    @Override
    public int InitDev() {
        if (devMifare.InitDev() != 0){
            return 1;
        }
        return 0;
    }

    @Override
    public void ReleaseDev() {
        devMifare.ReleaseDev();
    }

    @Override
    public byte[] SearchCard() {

        byte[] uid = devMifare.SearchCard();

        if (uid == null){
            return  null;
        }
        return uid;
    }

    @Override
    public int AuthenticationCardByKey(int type, byte[] cid, int block, byte[] key) {

        return devMifare.AuthenticationCardByKey(type, cid, block, key);
    }

    @Override
    public int WriteBlock(int block, byte[] data) {

        return devMifare.WriteBlock(block, data);
    }

    @Override
    public byte[] ReadBlock(int block) {

        return devMifare.ReadBlock(block);
    }

    @Override
    public int WriteBlockValue(int block, int value) {

        return devMifare.WriteBlockValue(block, value);
    }

    @Override
    public int[] ReadBlockValue(int block) {

        return devMifare.ReadBlockValue(block);
    }

    @Override
    public int IncrementBlockValue(int block, int value) {
        return devMifare.IncrementBlockValue(block, value);
    }

    @Override
    public int DecrementBlockValue(int block, int value) {

        return devMifare.DecrementBlockValue(block, value);
    }

    @Override
    public int HaltCard() {

        return devMifare.HaltCard();

    }


}
