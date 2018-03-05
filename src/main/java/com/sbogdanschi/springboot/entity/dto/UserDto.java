package com.sbogdanschi.springboot.entity.dto;

import com.sbogdanschi.springboot.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Boolean active;

    private Set<Role> roles;
}
