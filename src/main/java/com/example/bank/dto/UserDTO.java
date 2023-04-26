package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String cpf;
    private String login;
    private String password;
    private Double balance;

}
