package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RouteNumberDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeNumberDtoId;
    private int number;
    private Set<RouteDto> routesDto;
}
