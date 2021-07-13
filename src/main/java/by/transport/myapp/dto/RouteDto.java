package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.TreeSet;

@Getter
@Setter
public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeDtoId;
    private String description;
}