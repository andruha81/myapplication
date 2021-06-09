package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteTransportDto;
import by.transport.myapp.model.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TransportMapper.class)
public interface RouteTransportMapper {
    @Mapping(target = "routeTransportDtoId", source = "route.id")
    @Mapping(target = "transportsDto", source = "route.transports")
    RouteTransportDto routeTransportToDto(Route route);
}
