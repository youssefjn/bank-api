package com.example.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.dto.UserDTO;
import com.example.bank.exception.ObjectNotFoundException;
import com.example.bank.model.Transaction;
import com.example.bank.model.User;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getName(), user.getCpf(), user.getLogin(), user.getPassword(), user.getBalance());
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found"));
    }
@Transactional
    public  Optional<User> insert(UserDTO userDTO) {
       User newUser = new User();
       newUser.setName(userDTO.getName());
       newUser.setCpf(userDTO.getCpf());
       newUser.setLogin(userDTO.getLogin());
       newUser.setPassword(userDTO.getPassword());
       newUser.setBalance(0.0);
       newUser.setTransactions(null);
      return  Optional.ofNullable(userRepository.save(newUser));
        
        
    }

    public Transaction makeWithdrawal(User user, Double value) {
        if (user.makeWithdrawal(value)) {
            Transaction transaction=new Transaction(null, LocalDateTime.now(), true, false, value, user.getBalance()+value, user.getBalance(), "Successful transaction", user);
            userRepository.save(user);
            transactionRepository.save(transaction);
            return transaction;
        } else {
            Transaction transaction=new Transaction(null, LocalDateTime.now(), true, false, value, user.getBalance(), user.getBalance(), "Error , transaction not approved", user);
            transactionRepository.save(transaction);
            return transaction;
        }
    }

    public Transaction makeDeposit(User user, Double value) {
        if (user.makeDeposit(value)) {
            Transaction transaction=new Transaction(null, LocalDateTime.now(), false, true, value, user.getBalance()-value, user.getBalance(), "Successful transaction", user);
            userRepository.save(user);
            transactionRepository.save(transaction);
            return transaction;
        } else {
            Transaction transaction=new Transaction(null, LocalDateTime.now(), false, true, value, user.getBalance(), user.getBalance(), "Error , transaction not approved", user);
            transactionRepository.save(transaction);
            return transaction;
        }
    }
}
