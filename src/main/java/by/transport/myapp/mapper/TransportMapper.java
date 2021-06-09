package by.transport.myapp.mapper;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportLiteDto;
import by.transport.myapp.model.entity.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TransportTypeMapper.class)
public interface TransportMapper {
    @Mapping(target = "transportDtoId", source = "transport.id")
    @Mapping(target = "transportTypeDto", source = "transport.transportType")
    TransportDto transportToDto(Transport transport);

    @Mapping(target = "transportDtoId", source = "transport.id")
    @Mapping(target = "transportTypeDto", source = "transport.transportType")
    TransportLiteDto transportToLiteDto(Transport transport);

    @Mapping(target = "id", source = "transportDto.transportDtoId")
    @Mapping(target = "transportType", source = "transportDto.transportTypeDto")
    Transport dtoToTransport(TransportDto transportDto);
}
