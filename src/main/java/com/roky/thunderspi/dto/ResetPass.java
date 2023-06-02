package com.roky.thunderspi.dto;
import lombok.Data;

@Data
public class ResetPass {
    private String token;
    private String password;
}