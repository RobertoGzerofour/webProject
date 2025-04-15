package com.humber.WebProject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "First name is required")
    private String first_name;

    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    private String last_name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // ✅ Add this to prevent infinite loop
    private List<Order> orders;
}
