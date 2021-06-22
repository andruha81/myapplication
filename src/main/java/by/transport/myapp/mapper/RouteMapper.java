package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = RouteLineMapper.class)
public interface RouteMapper {
    @Mapping(target = "routeDtoId", source = "route.id")
    RouteDto routeToDto(Route route);

    @Mapping(target = "routeStopDtoId", source = "route.id")
    @Mapping(target = "number", source = "route.routeNumber.number")
    @Mapping(target = "type", source = "route.routeNumber.type.description")
    RouteStopDto routeToRouteStopDto(Route route);
}
