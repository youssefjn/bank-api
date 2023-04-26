package com.example.bank.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "TRANSACTIONS")

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateOfTransaction;
    private Boolean isItWithdraw;  
    private Boolean isItDeposit;
    private Double valueOfTransaction;
    private Double balanceBeforeTransaction;  
    private Double balanceAfterTransaction;
    private String message;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;  
}
