package com.example.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.UserDTO;
import com.example.bank.model.Transaction;
import com.example.bank.model.User;
import com.example.bank.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "204", description = "no user found")
    })
    @GetMapping
    public ResponseEntity<?> getAllusers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> userService.toDTO(user)).collect(Collectors.toList());
        if (usersDTO.isEmpty()) {
            return new ResponseEntity<>(" no Users found ", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
    }

    @Operation(summary = "insert new user")
    @ApiResponse(responseCode = "201", description = "Successfully inserted")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UserDTO userDTO) {

        userService.insert(userDTO);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user by id"),
            @ApiResponse(responseCode = "404", description = "no user found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO userDTO = userService.toDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Operation(summary = "make a withdrawal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping("/{id}/withdraw/{value}")
    public ResponseEntity<Transaction> makeWithdrawal(@PathVariable Long id, @PathVariable Double value) {
        User user = userService.findById(id);
        Transaction transaction = userService.makeWithdrawal(user, value);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Operation(summary = "make a deposit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping("/{id}/deposit/{value}")
    public ResponseEntity<Transaction> makeDeposit(@PathVariable Long id, @PathVariable Double value) {
        User user = userService.findById(id);
        Transaction transaction = userService.makeDeposit(user, value);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

}
