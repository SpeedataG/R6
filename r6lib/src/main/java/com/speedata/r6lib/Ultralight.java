package com.speedata.r6lib;

import com.android.hflibs.ultralight_native;

/**
 * Created by xu on 2017/1/3.
 */

public class Ultralight implements IUltralightManager {
    private ultralight_native devUltralight = new ultralight_native();

    @Override
    public int InitDev() {
        if (devUltralight.InitDev() != 0){
            return 1;
        }
        return 0;
    }

    @Override
    public void ReleaseDev() {

        devUltralight.ReleaseDev();
    }

    @Override
    public byte[] SearchCard() {

        byte[] uid = devUltralight.SearchCard(); 

        if (uid == null){
            return  null;
        }
        return uid;
    }

    @Override
    public byte[] ReadBlock(int block) {

        return devUltralight.ReadBlock(block);
    }

    @Override
    public int WriteBlock(int block, byte[] data) {

        return devUltralight.WriteBlock(block, data);
    }

    @Override
    public int compatibility_Write_Block(int block, byte[] data) {

        return devUltralight.compatibility_Write_Block(block, data);
    }

    @Override
    public int HaltCard() {

        return devUltralight.HaltCard();
    }


}
