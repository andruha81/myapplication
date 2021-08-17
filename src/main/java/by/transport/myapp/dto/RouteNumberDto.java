package by.transport.myapp.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteNumberDto implements Serializable, Comparable<RouteNumberDto> {
    private static final long serialVersionUID = 1L;
    private Integer routeNumberDtoId;
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 999, message = "Number should not be greater than 999")
    private int number;
    @NotBlank
    private String transportType;
    private Set<RouteDto> routesDto;

    @Override
    public int compareTo(RouteNumberDto o) {
        return this.transportType.compareTo(o.transportType);
    }
}
