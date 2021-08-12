package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotBlank(message = "Login is mandatory")
    private String login;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "user_role")
    private String userRole;
}
