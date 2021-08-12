package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
    @Min(value = 1, message = "Stop Order should not be less than 1")
    @Max(value = 99, message = "Stop Order should not be greater than 99")
    private int stopOrder;

    @Column(name = "time_prev", nullable = false)
    @Min(value = 1, message = "Time Prev should not be less than 1")
    @Max(value = 59, message = "Time Prev should not be greater than 59")
    private int timePrev;

    @Column(name = "distance_prev", nullable = false)
    @Min(value = 0, message = "Time Prev should not be less than 0")
    @Max(value = 4999, message = "Time Prev should not be greater than 4999")
    private int distancePrev;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id")
    private Route routeRouteLine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "stop_id")
    private Stop stop;
}
