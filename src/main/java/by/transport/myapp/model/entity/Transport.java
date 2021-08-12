package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Model is mandatory")
    private String model;

    @Column(name = "seat_num", nullable = false)
    @Min(value = 1, message = "Seat Number should not be less than 1")
    @Max(value = 999, message = "Seat Number should not be greater than 999")
    private int seatNum;

    @Column(name = "car_num", nullable = false)
    @Min(value = 1, message = "Car Number should not be less than 1")
    @Max(value = 9, message = "Car Number should not be greater than 9")
    private int carNum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "route_number_id")
    private RouteNumber route;
}
