package org.example;

import java.util.List;

public class Transaction {
    private final String txid;
    private final List<String> parentTxIds;

    public Transaction(String txid, List<String> parentTxIds) {
        this.txid = txid;
        this.parentTxIds = parentTxIds;
    }

    public String getTxid() {
        return txid;
    }

    public List<String> getParentTxIds() {
        return parentTxIds;
    }
}
