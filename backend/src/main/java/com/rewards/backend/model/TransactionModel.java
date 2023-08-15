package com.rewards.backend.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionModel {
    @CsvDate(value = "M/dd/yyyy")
    @CsvBindByName(column = "Date")
    private LocalDate date;

    @CsvBindByName(column = "Amount")
    private BigDecimal amount;
}
