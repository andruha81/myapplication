package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransportLiteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer transportDtoId;
    private String model;
    private TransportTypeDto transportTypeDto;
}
