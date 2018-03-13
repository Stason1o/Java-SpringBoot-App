package com.sbogdanschi.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users", schema = "flyway_schema")
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_user_id_seq")
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide a username")
    @Length(min = 6, message = "*Length must be more than 6 symbols")
    private String username;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Transient
    private String repeatPassword;

    @Column(name = "first_name")
    @NotEmpty(message = "*Please provide your firstName")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last firstName")
    private String lastName;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

}
