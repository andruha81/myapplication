package by.transport.myapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer stopDtoId;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopDto stopDto = (StopDto) o;
        return Objects.equals(stopDtoId, stopDto.stopDtoId) && Objects.equals(name, stopDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopDtoId, name);
    }
}
