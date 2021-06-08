package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransportMapper {
    @Mapping(target = "transportDtoId", source = "transport.id")
    @Mapping(target = "transportTypeDto", source = "transport.transportType")
    @Mapping(target = "routeDto", source = "transport.route")
    TransportDto transportToDto(Transport transport);

    @Mapping(target = "transportTypeDtoId", source = "transportType.id")
    TransportTypeDto transportTypeToDto(TransportType transportType);

    @Mapping(target = "routeDtoId", source = "route.id")
    RouteDto transportTypeToDto(Route route);
}
