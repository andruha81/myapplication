package by.transport.myapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportTypeDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer transportTypeDtoId;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Description is mandatory")
    private String descriptionEn;
    @NotBlank(message = "Description is mandatory")
    private String descriptionBy;
}
