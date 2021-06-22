package by.transport.myapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@Data
@Builder
public class RouteLineDto implements Serializable, Comparable<RouteLineDto> {
    private static final long serialVersionUID = 1L;
    private Integer routeLineDtoId;
    private StopDto stopDto;
    private int stopOrder;
    private LocalTime closestTime;
    private LocalTime nextTime;


    @Override
    public int compareTo(RouteLineDto o) {
        return this.stopOrder - o.stopOrder;
    }
}
