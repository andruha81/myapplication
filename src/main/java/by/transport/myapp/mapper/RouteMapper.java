package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import org.mapstruct.Mapper;

@Mapper
public interface RouteMapper {
    RouteDto routeToDto()
}
