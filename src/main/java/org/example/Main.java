package org.example;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import static org.example.AncestrySets.*;
import static org.example.FetchTransactions.getAllTransactions;
import static org.example.FetchTransactions.getBlockHash;
import static org.example.Response.printTopTransactions;

public class Main {


    public static void main(String[] args) {
        try {
            System.out.println("Start");
            String blockHash = getBlockHash("680000");
            List<Transaction> transactions = getAllTransactions(blockHash);
            List<TransactionAncestry> transactionsWithAncestry = calculateAncestryCounts(transactions);
            List<TransactionAncestry> topTransactions = getTopTransactions(transactionsWithAncestry);
            printTopTransactions(topTransactions);
            System.out.println("End");


        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
