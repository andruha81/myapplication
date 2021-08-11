package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "start_weekday", nullable = false)
    @NotNull(message = "Start Weekday is mandatory")
    private LocalTime startWeekday;

    @Column(name = "end_weekday", nullable = false)
    @NotNull(message = "End Weekday is mandatory")
    private LocalTime endWeekday;

    @Column(name = "interval_weekday", nullable = false)
    @Min(value = 0, message = "Interval should not be less than 0")
    @Max(value = 30, message = "Interval should not be greater than 30")
    private int intervalWeekday;

    @Column(name = "start_dayoff", nullable = false)
    @NotNull(message = "Start Dayoff is mandatory")
    private LocalTime startDayoff;

    @Column(name = "end_dayoff", nullable = false)
    @NotNull(message = "End Dayoff is mandatory")
    private LocalTime endDayoff;

    @Column(name = "interval_dayoff", nullable = false)
    @Min(value = 0, message = "Interval should not be less than 0")
    @Max(value = 30, message = "Interval should not be greater than 30")
    private int intervalDayoff;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "route_number_id")
    private RouteNumber routeNumber;

    @OneToMany(mappedBy = "routeRouteLine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteLine> routeLines;
}
