package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
public class RouteLineStopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeLineStopDtoId;
    private String type;
    private String routeDescription;
    private String routeNumber;
    private LocalTime closestTime;
    private LocalTime nextTime;
}
