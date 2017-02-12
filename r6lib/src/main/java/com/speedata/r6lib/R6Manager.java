package com.speedata.r6lib;

/**
 * Created by brxu on 2016/12/27.
 */

public class R6Manager {
    private static IR6Manager ir6Manager;
    private static ISO15693Manager iso15693Manager;
    private static IMifareManager mifareManager;
    private static IUltralightManager ultralightManager;

    //卡类型
    public enum CardType {
        CPUA, CPUB,ISO15693, MIFARE, ULTRALIGHT
    }

    private R6Manager() {
    }

    public static IR6Manager getInstance(CardType cardType) {
        switch (cardType) {
            case CPUA:
                ir6Manager = new CPU_TypeA();
                break;
            case CPUB:
                ir6Manager = new CPU_TypeB();
                break;

            default:
                ir6Manager = null;
        }
        return ir6Manager;
    }



    public static ISO15693Manager get15693Instance(CardType cardType) {
        switch (cardType) {
            case ISO15693:
                iso15693Manager = new ISO_15693();
                break;

            default:
                iso15693Manager = null;
        }
        return iso15693Manager;
    }



    public static IMifareManager getMifareInstance(CardType cardType) {
        switch (cardType) {
            case MIFARE:
                mifareManager = new Mifare();
                break;

            default:
                mifareManager = null;
        }
        return mifareManager;
    }



    public static IUltralightManager getUltralightInstance(CardType cardType) {
        switch (cardType) {
            case ULTRALIGHT:
                ultralightManager = new Ultralight();
                break;

            default:
                ultralightManager = null;
        }
        return ultralightManager;
    }




}
