package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RouteNumberDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeNumberDtoId;
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 999, message = "Number should not be greater than 999")
    private int number;
    private Set<RouteDto> routesDto;
}
