package by.transport.myapp.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(mappedBy = "stop", cascade = CascadeType.REMOVE)
    private Set<RouteLine> routeLines;
}
