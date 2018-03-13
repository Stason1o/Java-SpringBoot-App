package com.sbogdanschi.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SearchCriteria {

    @NotBlank(message = "username can't empty!")
    private String username;

}
