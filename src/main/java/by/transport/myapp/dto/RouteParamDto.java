package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RouteParamDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeParamDtoId;
    @Min(value = 1)
    private Integer typeId;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Start Weekday is mandatory")
    private LocalTime startWeekday;
    @NotNull(message = "End Weekday is mandatory")
    private LocalTime endWeekday;
    @Min(value = 1, message = "Interval should not be less than 1")
    @Max(value = 120, message = "Interval should not be greater than 120")
    private int intervalWeekday;
    @NotNull(message = "Start Dayoff is mandatory")
    private LocalTime startDayoff;
    @NotNull(message = "End Dayoff is mandatory")
    private LocalTime endDayoff;
    @Min(value = 1, message = "Interval should not be less than 1")
    @Max(value = 120, message = "Interval should not be greater than 120")
    private int intervalDayoff;
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 999, message = "Number should not be greater than 999")
    private int routeNumber;
    private List<RouteLineParamDto> routeLines;
}
