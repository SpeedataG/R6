package com.speedata.r6lib;

import com.android.hflibs.Iso15693_native;

/**
 * Created by xu on 2016/12/29.
 */

public class ISO_15693 implements ISO15693Manager {
    private static final String TAG = "rc663_15693_java";
    private Iso15693_native dev15693=new Iso15693_native();


    @Override
    public int InitDevice() {
        if (dev15693.InitDevice() != 0){
            return 1;
        }
        return 0;
    }

    @Override
    public void ReleaseDevice() {

        dev15693.ReleaseDevice();
    }

    @Override
    public byte[] SearchCard() {

        byte[] uid = dev15693.SearchCard(Iso15693_native.ISO15693_ACTIVATE_ADDRESSED, Iso15693_native.ISO15693_FLAG_UPLINK_RATE_HIGH, Iso15693_native.ISO15693_FLAG_NO_USE_AFI, (byte)0, Iso15693_native.ISO15693_FLAG_ONE_SLOTS, null, 0);

        if (uid == null){
            return  null;
        }
        return uid;
    }


    @Override
    public byte[] ReadCardInfo() {
        byte[] cinfo = dev15693.ReadCardInfo();
        if (cinfo==null){
            return null;
        }
        return cinfo;
    }


    @Override
    public int WriteSingleBlock(int option, int block, byte[] data) {
        return dev15693.WriteSingleBlock(option, block, data);
    }

    @Override
    public byte[] ReadSingleBlock(int option, int block) {
        return dev15693.ReadSingleBlock(option, block);
    }

    @Override
    public int WriteMultipleBlocks(int option, int firstblock, int block_nr, byte[] data) {
        return dev15693.WriteMultipleBlocks(option, firstblock, block_nr, data);
    }

    @Override
    public byte[] ReadMultipleBlocks(int option, int firstblock, int block_nr) {
        return dev15693.ReadMultipleBlocks(option, firstblock, block_nr);
    }

    @Override
    public int WriteAFI(int option, byte AFI) {
        return dev15693.WriteAFI(option, AFI);
    }

    @Override
    public int WriteDSFID(int option, byte DSFID) {
        return dev15693.WriteDSFID(option, DSFID);
    }

    @Override
    public int LockBlock(int option, int block) {
        return dev15693.LockBlock(option, block);
    }

    @Override
    public int LockAFI(int option) {
        return dev15693.LockAFI(option);
    }

    @Override
    public int LockDSFID(int option) {
        return dev15693.LockDSFID(option);
    }

    @Override
    public byte[] GetMultipleBlockSecurityStatus(int firstblock, int nr_block) {
        return dev15693.GetMultipleBlockSecurityStatus(firstblock, nr_block);
    }


}
