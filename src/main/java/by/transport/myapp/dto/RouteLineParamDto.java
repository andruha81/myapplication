package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RouteLineParamDto implements Serializable, Comparable<RouteLineParamDto> {
    private static final long serialVersionUID = 1L;
    private Integer routeLineParamDtoId;
    private String stopName;
    private int stopOrder;
    private int timePrev;
    private int distancePrev;

    @Override
    public int compareTo(RouteLineParamDto o) {
        return this.stopOrder - o.stopOrder;
    }
}
