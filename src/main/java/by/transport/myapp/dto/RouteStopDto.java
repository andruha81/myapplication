package by.transport.myapp.dto;

import lombok.*;

import java.io.Serializable;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteStopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeStopDtoId;
    private int number;
    private String type;
    private String description;
    private TreeSet<RouteLineDto> routeLines;
}
