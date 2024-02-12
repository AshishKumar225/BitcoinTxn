package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.API_URL;
import static org.example.MakeApiCalls.makeAPICall;

public class FetchTransactions {
    public static String getBlockHash(String blockHeight) throws IOException {
        return makeAPICall(API_URL + "block-height/" + blockHeight).trim();
    }

    public static List<Transaction> getAllTransactions(String blockHash) throws IOException, JSONException {
        List<Transaction> transactions = new ArrayList<>();
        int startIndex = 0;
        JSONArray txsArray;
        do {
            if(startIndex==2850) {
                return transactions;
            }
            String txsJson = makeAPICall(API_URL + "block/" + blockHash + "/txs/" + startIndex);
            txsArray = new JSONArray(txsJson);
            for (int i = 0; i < txsArray.length(); i++) {
                JSONObject tx = txsArray.getJSONObject(i);
                String txid = tx.getString("txid");
                JSONArray vin = tx.getJSONArray("vin");
                List<String> parentTxIds = new ArrayList<>();
                for (int j = 0; j < vin.length(); j++) {
                    JSONObject vinObj = vin.getJSONObject(j);
                    if (vinObj.has("txid")) {
                        parentTxIds.add(vinObj.getString("txid"));
                    }
                }
                transactions.add(new Transaction(txid, parentTxIds));

            }

            startIndex += txsArray.length();
        } while (txsArray.length() > 0);

        return transactions;
    }

}
