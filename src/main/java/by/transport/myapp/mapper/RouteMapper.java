package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = RouteLineMapper.class)
public interface RouteMapper {
    @Mapping(target = "routeDtoId", source = "route.id")
    RouteDto routeToDto(Route route);

    @Mapping(target = "routeStopDtoId", source = "route.id")
    @Mapping(target = "number", source = "route.routeNumber.number")
    @Mapping(target = "type", source = "route.routeNumber.type.description")
    @Mapping(target = "typeBy", source = "route.routeNumber.type.descriptionBy")
    @Mapping(target = "typeEn", source = "route.routeNumber.type.descriptionEn")
    RouteStopDto routeToRouteStopDto(Route route);

    @Mapping(target = "routeParamDtoId", source = "route.id")
    @Mapping(target = "routeNumber", source = "route.routeNumber.number")
    @Mapping(target = "typeId", source = "route.routeNumber.type.id")
    RouteParamDto routeToRouteParamDto(Route route);

    @Mapping(target = "id", source = "routeParamDto.routeParamDtoId")
    @Mapping(target = "routeNumber", source = "routeN")
    Route RouteParamDtoToRoute(RouteParamDto routeParamDto, RouteNumber routeN);
}
