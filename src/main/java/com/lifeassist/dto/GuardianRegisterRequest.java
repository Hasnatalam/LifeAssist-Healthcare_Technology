package com.lifeassist.dto;

import com.lifeassist.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuardianRegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private String  gurdianUsername;
}