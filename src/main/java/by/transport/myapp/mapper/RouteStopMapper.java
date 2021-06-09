package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public abstract class RouteStopMapper {

    private final StopMapper mapper = Mappers.getMapper(StopMapper.class);

    public RouteStopDto routeToDto(Route route) {
        RouteStopDto routeStopDto = new RouteStopDto();
        routeStopDto.setRouteStopDtoId(route.getId());
        routeStopDto.setDescription(route.getDescription());
        routeStopDto.setNumber(route.getNumber());
        routeStopDto.setStopsDto(route.getRouteLines().stream().map(RouteLine::getStop).map(mapper::stopToDto).collect(Collectors.toSet()));
        return routeStopDto;
    }
}
