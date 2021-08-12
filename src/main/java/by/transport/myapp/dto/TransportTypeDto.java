package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class TransportTypeDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer transportTypeDtoId;
    @NotBlank(message = "Description is mandatory")
    private String description;
}
