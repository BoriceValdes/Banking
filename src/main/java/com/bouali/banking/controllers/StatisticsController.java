package com.bouali.banking.controllers;

import com.bouali.banking.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private  final StatisticsService service;

    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity <Map<LocalDate, BigDecimal>> findSumTransactionsByDate(
            @PathVariable("user-id")  Integer userId,
            @RequestParam("start-date") LocalDate startDate,
            @RequestParam("end-date") LocalDate endDate){

         return ResponseEntity.ok(service.findSumTransactionsByDate(startDate,endDate,userId));
    }
    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity <BigDecimal> getAcccountBalance(
          @PathVariable("user-id")  Integer userId
    ){
        return ResponseEntity.ok(service.getAcccountBalance(userId));
    }

    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity <BigDecimal> highestTransfert(
            @PathVariable("user-id")  Integer userId
    ){
        return ResponseEntity.ok(service.highestTransfert(userId));
    }
    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity <BigDecimal> highestDeposit(
            @PathVariable("user-id")  Integer userId
    ){
        return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
