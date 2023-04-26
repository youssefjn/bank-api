package com.example.bank.dto;

import java.time.LocalDateTime;

import com.example.bank.model.Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
	private Long id;
	private LocalDateTime dateOfTransaction;
	private Boolean isItWithdraw;
	private Boolean isItDeposit;
	private Double valueOfTransaction;
	private Double balanceBeforeTransaction;
	private Double balanceAfterTransaction;
	private String message;

	public TransactionDTO(Transaction obj) {
		super();
		this.id = obj.getId();
		this.dateOfTransaction = obj.getDateOfTransaction();
		this.isItWithdraw = obj.getIsItWithdraw();
		this.isItDeposit = obj.getIsItDeposit();
		this.valueOfTransaction = obj.getValueOfTransaction();
		this.balanceBeforeTransaction = obj.getBalanceBeforeTransaction();
		this.balanceAfterTransaction = obj.getBalanceAfterTransaction();
		this.message = obj.getMessage();
	}

}
