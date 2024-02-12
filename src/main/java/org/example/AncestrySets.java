package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class AncestrySets {
    public static List<TransactionAncestry> getTopTransactions(List<TransactionAncestry> transactionsWithAncestry) {
        //return top 10 transactions after getting ancestor count for each txn
        return transactionsWithAncestry.stream()
                .sorted(Comparator.comparingInt(TransactionAncestry::getAncestrySetSize).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

   /* To calculate the ancestor count for each transaction and find the top 10 transactions with the highest ancestor count, I did

    1. Traversing each transaction to find its parents and incrementing the ancestor count for each parent found within the block.
    2. Repeating the process for each parent transaction recursively.
    3. Collecting the ancestor count for each transaction.
    4. then sorting the transactions by their ancestor count in descending order.
    5. then Selecting the top 10 transactions with the highest ancestor counts and then return.
    */
    public static List<TransactionAncestry> calculateAncestryCounts(List<Transaction> transactions) {
        Map<String, Integer> ancestorCountMap = new HashMap<>();

        for (Transaction tx : transactions) {
            ancestorCountMap.put(tx.getTxid(), 0);
        }

        for (Transaction tx : transactions) {
            Set<String> visited = new HashSet<>();
            updateAncestorCount(tx, transactions, ancestorCountMap, visited);
        }

        List<TransactionAncestry> transactionAncestryList = ancestorCountMap.entrySet().stream()
                .map(entry -> new TransactionAncestry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return transactionAncestryList.stream()
                .sorted(Comparator.comparingInt(TransactionAncestry::getAncestrySetSize).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    private static void updateAncestorCount(Transaction tx, List<Transaction> allTransactions,
                                            Map<String, Integer> ancestorCountMap, Set<String> visited) {
        if (visited.contains(tx.getTxid())) {
            return;
        }
        visited.add(tx.getTxid());

        for (String parentTxId : tx.getParentTxIds()) {
            if (ancestorCountMap.containsKey(parentTxId)) {
                ancestorCountMap.put(parentTxId, ancestorCountMap.get(parentTxId) + 1);

                Transaction parentTx = allTransactions.stream()
                        .filter(t -> t.getTxid().equals(parentTxId))
                        .findFirst()
                        .orElse(null);

                if (parentTx != null) {
                    updateAncestorCount(parentTx, allTransactions, ancestorCountMap, visited);
                }
            }
        }
    }

}
