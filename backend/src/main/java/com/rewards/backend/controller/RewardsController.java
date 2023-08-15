package com.rewards.backend.controller;

import com.rewards.backend.helper.RewardsHelper;
import com.rewards.backend.model.RewardsModel;
import com.rewards.backend.model.TransactionModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    @PostMapping("/calculate-monthly-rewards")
    public ResponseEntity<List<RewardsModel>> calculateMonthlyRewards(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<TransactionModel> csvToBean = new CsvToBeanBuilder<TransactionModel>(reader)
                    .withType(TransactionModel.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<TransactionModel> transactions = csvToBean.parse();
            RewardsHelper rewardsHelper = new RewardsHelper(transactions);
            List<RewardsModel> monthlyRewards = rewardsHelper.calculateMonthlyRewards();

            return ResponseEntity.ok(monthlyRewards);

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
