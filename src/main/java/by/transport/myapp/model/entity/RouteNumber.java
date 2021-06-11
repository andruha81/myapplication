package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private int number;

    @OneToMany(mappedBy = "routeNumber", cascade = CascadeType.REMOVE)
    private Set<Route> routes;

    @OneToMany(mappedBy = "route")
    private Set<Transport> transports;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private TransportType type;
}
