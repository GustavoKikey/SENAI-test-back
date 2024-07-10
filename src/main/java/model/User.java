package model;

import enums.Institution;
import enums.Specialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    @NotBlank(message = "CPF is required")
    private String cpf;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization", nullable = false)
    @NotNull(message = "Specialization is required")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @Column(name = "institution", nullable = false)
    @NotNull(message = "Institution is required")
    private Institution institution;
}