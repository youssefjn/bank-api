package com.example.bank.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "USERS")
@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String login;
    private String password;
    private Double balance;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<Transaction>();
    public User(Long id, String name, String cpf, String login, String password, Double balance) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.login = login;
		this.password = password;
		this.balance = balance;
	}
    public boolean makeDeposit(Double deposit) {
        try {
			this.setBalance(this.getBalance() + deposit);
		} catch (Exception e) {
			return false;
		}
		return true;
    }
    public boolean makeWithdrawal(Double valueOfWithdrawal) {
        if (this.getBalance() >= valueOfWithdrawal) {
			this.setBalance(this.getBalance() - valueOfWithdrawal);
		} else {
			return false;
		}
		return true;
    }

}
