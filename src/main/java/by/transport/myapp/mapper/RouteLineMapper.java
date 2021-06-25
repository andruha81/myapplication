package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteLineDto;
import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.model.entity.RouteLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StopMapper.class)
public interface RouteLineMapper {
    @Mapping(target = "routeLineDtoId", source = "routeLine.id")
    @Mapping(target = "routeId", source = "routeLine.routeRouteLine.id")
    @Mapping(target = "stopDto", source = "routeLine.stop")
    RouteLineDto routeLineToRouteLineDto(RouteLine routeLine);

    @Mapping(target = "routeLineStopDtoId", source = "routeLine.id")
    @Mapping(target = "type", source = "routeLine.routeRouteLine.routeNumber.type.description")
    @Mapping(target = "routeDescription", source = "routeLine.routeRouteLine.description")
    @Mapping(target = "routeNumber", source = "routeLine.routeRouteLine.routeNumber.number")
    RouteLineStopDto routeLineToRouteLineStopDto(RouteLine routeLine);
}
