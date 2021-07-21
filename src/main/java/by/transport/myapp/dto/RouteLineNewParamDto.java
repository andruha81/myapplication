package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RouteLineNewParamDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeLineNewParamDtoId;
    private Integer stopId;
    private int stopOrder;
    private int timePrev;
    private int distancePrev;
}
