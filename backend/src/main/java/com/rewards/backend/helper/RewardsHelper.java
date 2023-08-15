package com.rewards.backend.helper;

import com.rewards.backend.model.RewardsModel;
import com.rewards.backend.model.TransactionModel;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardsHelper {
    private final BigDecimal REWARD_1_MIN_TRANSACTION = new BigDecimal("50.00");
    private final BigDecimal REWARD_2_MIN_TRANSACTION = new BigDecimal("100.00");
    private final List<TransactionModel> transactions;

    public RewardsHelper(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public List<RewardsModel> calculateMonthlyRewards() {
        Map<Month, List<TransactionModel>> monthlyTransaction = getMonthlyTransactions();
        List<RewardsModel> monthlyRewards = new ArrayList<>();

        for (Map.Entry<Month, List<TransactionModel>> entry : monthlyTransaction.entrySet()) {
            RewardsModel rewardsModel = new RewardsModel();
            rewardsModel.setMonth(entry.getKey());

            for (TransactionModel transaction : entry.getValue()) {
                rewardsModel.setTotalTransactions(rewardsModel.getTotalTransactions() + 1);
                rewardsModel.setTotalSpent(rewardsModel.getTotalSpent().add(transaction.getAmount()));

                BigDecimal amount = transaction.getAmount();
                int rewards = 0;

                if (amount.compareTo(REWARD_2_MIN_TRANSACTION) >= 0) {
                    BigDecimal rewards2Amount = amount.subtract(REWARD_2_MIN_TRANSACTION);
                    rewards += rewards2Amount.multiply(new BigDecimal("2")).intValue();
                    amount = amount.subtract(rewards2Amount);
                }

                if (amount.compareTo(REWARD_1_MIN_TRANSACTION) >= 0) {
                    BigDecimal rewards1Amount = amount.subtract(REWARD_1_MIN_TRANSACTION);
                    rewards += rewards1Amount.multiply(new BigDecimal("1")).intValue();
                }

                rewardsModel.setTotalRewards(rewardsModel.getTotalRewards() + rewards);
            }

            monthlyRewards.add(rewardsModel);
        }

        return monthlyRewards;
    }

    private Map<Month, List<TransactionModel>> getMonthlyTransactions() {
        Map<Month, List<TransactionModel>> monthlyTransactions = new HashMap<>();

        for (TransactionModel transaction : transactions) {
            Month month = transaction.getDate().getMonth();

            if (!monthlyTransactions.containsKey(month)) {
                monthlyTransactions.put(month, new ArrayList<>(List.of(transaction)));
            } else {
                monthlyTransactions.get(month).add(transaction);
            }
        }

        return monthlyTransactions;
    }
}
