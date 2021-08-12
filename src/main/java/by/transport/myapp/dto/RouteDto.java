package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer routeDtoId;
    @NotBlank(message = "Description is mandatory")
    private String description;
}
