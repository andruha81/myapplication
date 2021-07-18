package by.transport.myapp.mapper;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TransportTypeMapper.class)
public abstract class TransportMapper {

    @Mapping(target = "transportDtoId", source = "transport.id")
    @Mapping(target = "type", source = "transport.transportType.description")
    @Mapping(target = "route", source = "transport.route.number")
    public abstract TransportDto transportToDto(Transport transport);

    @Mapping(target = "id", source = "transportDto.transportDtoId")
    @Mapping(target = "transportType", source = "type")
    @Mapping(target = "route", source = "routeNumber")
    public abstract Transport dtoToTransport(TransportDto transportDto,
                                             TransportType type,
                                             RouteNumber routeNumber);
}
