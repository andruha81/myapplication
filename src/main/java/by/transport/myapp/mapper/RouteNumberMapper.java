package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {RouteMapper.class, TransportTypeMapper.class})
public interface RouteNumberMapper {
    @Mapping(target = "routeNumberDtoId", source = "routeNumber.id")
    @Mapping(target = "routesDto", source = "routeNumber.routes")
    @Mapping(target = "transportType", source = "routeNumber.type.description")
    RouteNumberDto routeNumberToDto(RouteNumber routeNumber);

    @Mapping(target = "id", source = "routeNumberDto.routeNumberDtoId")
    @Mapping(target = "type", source = "transportType")
    RouteNumber dtoToRouteNumber(RouteNumberDto routeNumberDto, TransportType transportType);
}
