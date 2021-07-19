package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route_line")
public class RouteLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "stop_order", nullable = false)
    private int stopOrder;

    @Column(name = "time_prev", nullable = false)
    private int timePrev;

    @Column(name = "distance_prev", nullable = false)
    private int distancePrev;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route routeRouteLine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "stop_id")
    private Stop stop;
}
