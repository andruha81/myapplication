package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RouteStopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeStopDtoId;
    private int number;
    private String description;
    private Set<StopDto> stopsDto;
}
