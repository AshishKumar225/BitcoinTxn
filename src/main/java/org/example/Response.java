package org.example;

import java.util.List;

public class Response {
    public static void printTopTransactions(List<TransactionAncestry> topAncestrySets) {
        for (TransactionAncestry ta : topAncestrySets) {
            System.out.println("Transaction id: " + ta.getTxid() + " with Ancestry Size: " + ta.getAncestrySetSize());
        }
    }
}
