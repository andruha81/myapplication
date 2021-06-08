package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeDtoId;
    private int number;
    private String description;
    Set<RouteLineDto> routeLinesDto;
}
