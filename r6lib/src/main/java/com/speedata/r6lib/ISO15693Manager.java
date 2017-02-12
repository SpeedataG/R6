package com.speedata.r6lib;

/**
 * Created by xu on 2017/1/16.
 */

public interface ISO15693Manager {
        public int InitDevice();
        public void ReleaseDevice();
        public byte[] SearchCard();
        public byte[] ReadCardInfo();
        public int WriteSingleBlock(int option, int block, byte[] data);
        public byte[] ReadSingleBlock(int option, int block);
        public int WriteMultipleBlocks(int option, int firstblock, int block_nr, byte[] data);
        public byte[] ReadMultipleBlocks(int option, int firstblock, int block_nr);
        public int WriteAFI(int option, byte AFI);
        public int WriteDSFID(int option, byte DSFID);
        public int LockBlock(int option, int block);
        public int LockAFI(int option);
        public int LockDSFID(int option);
        public byte[] GetMultipleBlockSecurityStatus(int firstblock, int nr_block);

}
