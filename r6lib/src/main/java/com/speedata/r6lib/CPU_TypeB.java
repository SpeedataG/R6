package com.speedata.r6lib;

import android.util.Log;

import com.android.hflibs.CpuB_native;

/**
 * Created by brxu on 2016/12/27.
 */

public class CPU_TypeB implements IR6Manager {
    private CpuB_native devB=new CpuB_native();
    private static String TAG="CPUB";
    private byte[][] nkB;

    public String result="未改动";

    public String AIDString;
    public String Aai;

    @Override
    public int InitDevice() {
        if(devB.InitDevice() != 0)
        {
            return -1;
        }

        return 0;
    }

    @Override
    public void ReleaseDevice() {

        devB.ReleaseDevice();
    }

    @Override
    public byte[] SearchCard() {
        byte[] IDall=null;

        byte[][] nk = devB.SearchCard();
        if(nk == null)
        {
            result="搜不到卡";
            return null;
        }
        byte[] ID = nk[0];
        IDall=ID;
        nkB=nk;
        String IDStringB = new String("");
        for(byte a : ID)
        {
            IDStringB += String.format("%02X", a);
        }
        AIDString=IDStringB;

        return IDall;
    }

    @Override
    public int Deselect() {
        int desall=0;

        desall=devB.Deselect();

        return desall;
    }

    @Override
    public byte[] ReadCard() {
        byte[] atsall=null;

        if (nkB==null){
            result="没有数据";
            return null;
        }

        byte[] ATQB = nkB[1];
        atsall=ATQB;
        String ATQBString = new String("");
        for(byte a: ATQB)
        {
            ATQBString += String.format("%02X", a);
        }
        Aai=ATQBString;

        return atsall;
    }



    @Override
    public byte[] ExecCmdInput(byte[] input) {

        byte[] execall=null;
        execall = devB.Exec_cmd(input);
        Log.i(TAG,execall+"");
        return execall;
    }

}
