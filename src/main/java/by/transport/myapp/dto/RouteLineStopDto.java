package by.transport.myapp.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteLineStopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeLineStopDtoId;
    private String type;
    private String routeDescription;
    private String routeNumber;
    private Integer routeId;
    private int stopOrder;
    private LocalTime closestTime;
    private LocalTime nextTime;
}
