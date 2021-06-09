package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RouteLineStopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeLineStopDtoId;
    private StopDto stopDto;
}
