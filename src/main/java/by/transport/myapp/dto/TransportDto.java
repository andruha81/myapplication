package by.transport.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class TransportDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer transportDtoId;
    private String model;
    private TransportTypeDto transportTypeDto;
    private int seatNum;
    private String driveType;
    private BigDecimal len;
    private BigDecimal width;
    private int doorNum;
    private int carNum;
}
