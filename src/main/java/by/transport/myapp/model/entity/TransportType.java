package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transport_type")
public class TransportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @OneToMany(mappedBy = "transportType", cascade = CascadeType.REMOVE)
    private Set<Transport> transports;

    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    private Set<RouteNumber> routeNumbers;
}
