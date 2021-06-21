package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.model.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RouteMapper {
    @Mapping(target = "routeDtoId", source = "route.id")
    RouteDto routeToDto(Route route);
}
