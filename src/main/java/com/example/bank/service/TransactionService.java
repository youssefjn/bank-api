package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bank.dto.TransactionDTO;
import com.example.bank.exception.ObjectNotFoundException;
import com.example.bank.model.Transaction;
import com.example.bank.model.User;
import com.example.bank.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TransactionService {
private final TransactionRepository transactionRepository;
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    public TransactionDTO fromDTO(Transaction obj) {
        return new TransactionDTO(obj);
    }
    public Transaction findById(Long id) {
        Optional<Transaction> transaction= transactionRepository.findById(id);
        return transaction.orElseThrow(() -> new ObjectNotFoundException("Transaction with id "+ id + " does not exist"));
    }
    public List<Transaction> findByUser(User user) {
        return transactionRepository.findByUser(user);
    }
    
}
