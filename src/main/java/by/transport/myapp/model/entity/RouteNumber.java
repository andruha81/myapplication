package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route_number")
public class RouteNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, unique = true)
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 999, message = "Number should not be greater than 999")
    private int number;

    @OneToMany(mappedBy = "routeNumber", cascade = CascadeType.REMOVE)
    private Set<Route> routes;

    @OneToMany(mappedBy = "route")
    private Set<Transport> transports;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private TransportType type;
}
