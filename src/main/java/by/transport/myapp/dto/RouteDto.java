package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeDtoId;
    private int number;
    private String descriptionOne;
    private Integer idOne;
    private String descriptionTurn;
    private Integer idTurn;
}
