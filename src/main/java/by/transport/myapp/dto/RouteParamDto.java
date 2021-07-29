package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RouteParamDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeParamDtoId;
    private Integer typeId;
    private String description;
    private LocalTime startWeekday;
    private LocalTime endWeekday;
    private int intervalWeekday;
    private LocalTime startDayoff;
    private LocalTime endDayoff;
    private int intervalDayoff;
    private int routeNumber;
    private List<RouteLineParamDto> routeLines;
}
