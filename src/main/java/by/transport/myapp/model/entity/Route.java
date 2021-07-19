package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_weekday", nullable = false)
    private LocalTime startWeekday;

    @Column(name = "end_weekday", nullable = false)
    private LocalTime endWeekday;

    @Column(name = "interval_weekday", nullable = false)
    private int intervalWeekday;

    @Column(name = "start_dayoff", nullable = false)
    private LocalTime startDayoff;

    @Column(name = "end_dayoff", nullable = false)
    private LocalTime endDayoff;

    @Column(name = "interval_dayoff", nullable = false)
    private int intervalDayoff;

    @ManyToOne
    @JoinColumn(name = "route_number_id")
    private RouteNumber routeNumber;

    @OneToMany(mappedBy = "routeRouteLine", cascade = CascadeType.ALL)
    private List<RouteLine> routeLines;
}
