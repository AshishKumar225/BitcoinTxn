package org.example;

public class TransactionAncestry {
    private final String txid;
    private final int ancestrySetSize;

    public TransactionAncestry(String txid, int ancestrySetSize) {
        this.txid = txid;
        this.ancestrySetSize = ancestrySetSize;
    }

    public String getTxid() {
        return txid;
    }

    public int getAncestrySetSize() {
        return ancestrySetSize;
    }
}
