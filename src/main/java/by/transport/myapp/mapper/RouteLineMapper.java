package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteLineDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.entity.RouteLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StopMapper.class)
public interface RouteLineMapper {
    @Mapping(target = "routeLineDtoId", source = "routeLine.id")
    @Mapping(target = "stopDto", source = "routeLine.stop")
    RouteLineDto routeLineToRouteLineDto(RouteLine routeLine);
}
