package com.example.bank.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
	private Long id;
	private LocalDateTime dateOfTransaction;
	private Boolean isItWithdraw;
	private Boolean isItDeposit;
	private Double valueOfTransaction;
	private Double balanceBeforeTransaction;
	private Double balanceAfterTransaction;
	private String message;

}
