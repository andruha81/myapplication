package by.transport.myapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String model;

    @Column(name = "seat_num", nullable = false)
    private int seatNum;

    @Column(name = "car_num", nullable = false)
    private int carNum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "route_number_id")
    private RouteNumber route;
}
