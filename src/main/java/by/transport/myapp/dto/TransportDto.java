package by.transport.myapp.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer transportDtoId;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Type is mandatory")
    private String type;
    @Min(value = 1, message = "Seat Number should not be less than 1")
    @Max(value = 999, message = "Seat Number should not be greater than 999")
    private int seatNum;
    @Min(value = 1, message = "Car Number should not be less than 1")
    @Max(value = 9, message = "Car Number should not be greater than 9")
    private int carNum;
    @Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 999, message = "Number should not be greater than 999")
    private int route;
}
