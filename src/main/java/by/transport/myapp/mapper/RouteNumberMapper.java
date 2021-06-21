package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = RouteMapper.class)
public interface RouteNumberMapper {
    @Mapping(target = "routeNumberDtoId", source = "routeNumber.id")
    @Mapping(target = "routesDto", source = "routeNumber.routes")
    RouteNumberDto routeNumberToDto(RouteNumber routeNumber);
}
