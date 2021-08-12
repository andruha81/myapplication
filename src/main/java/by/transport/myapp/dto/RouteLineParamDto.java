package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
public class RouteLineParamDto implements Serializable, Comparable<RouteLineParamDto> {
    private static final long serialVersionUID = 1L;
    private Integer routeLineParamDtoId;
    private StopDto stopDto;
    @Min(value = 1, message = "Stop Order should not be less than 1")
    @Max(value = 99, message = "Stop Order should not be greater than 99")
    private int stopOrder;
    @Min(value = 1, message = "Time Prev should not be less than 1")
    @Max(value = 59, message = "Time Prev should not be greater than 59")
    private int timePrev;
    @Min(value = 0, message = "Time Prev should not be less than 0")
    @Max(value = 4999, message = "Time Prev should not be greater than 4999")
    private int distancePrev;

    @Override
    public int compareTo(RouteLineParamDto o) {
        return this.stopOrder - o.stopOrder;
    }
}
