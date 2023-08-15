package com.rewards.backend.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Month;

@Data
public class RewardsModel {
    private Month month;
    private BigDecimal totalSpent = BigDecimal.ZERO;
    private int totalTransactions = 0;
    private int totalRewards = 0;
}
