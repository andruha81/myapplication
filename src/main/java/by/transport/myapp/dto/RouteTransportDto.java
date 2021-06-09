package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class RouteTransportDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeTransportDtoId;
    private int number;
    private String description;
    private Set<TransportLiteDto> transportsDto;
}
