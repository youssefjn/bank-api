package com.example.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.TransactionDTO;
import com.example.bank.model.Transaction;
import com.example.bank.model.User;
import com.example.bank.service.TransactionService;
import com.example.bank.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;

    @Operation(summary = "Get all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all transactions"),
            @ApiResponse(responseCode = "204", description = "no transaction found")
    })
    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        List<TransactionDTO> transactionsDTO = transactions.stream().map(obj -> transactionService.fromDTO(obj))
                .collect(Collectors.toList());
        if (transactionsDTO.isEmpty()) {
            return new ResponseEntity<>(" no Transactions found ", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TransactionDTO>>(transactionsDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction by id"),
            @ApiResponse(responseCode = "404", description = "no transaction found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        TransactionDTO transactionDTO = transactionService.fromDTO(transaction);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get all transactions by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all transactions"),
            @ApiResponse(responseCode = "204", description = "no transactions found"),
            @ApiResponse(responseCode = "404", description = "no user found")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUser(@PathVariable Long id) {
        User user = userService.findById(id);
        List<Transaction> transactions = transactionService.findByUser(user);
        List<TransactionDTO> transactionsDTO = transactions.stream().map(obj -> transactionService.fromDTO(obj))
                .collect(Collectors.toList());
        if (transactionsDTO.isEmpty()) {
            return new ResponseEntity<>(" no Transactions found ", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TransactionDTO>>(transactionsDTO, HttpStatus.OK);
    }
}