package by.transport.myapp.mapper;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.TransportType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransportTypeMapper {
    @Mapping(target = "transportTypeDtoId", source = "transportType.id")
    TransportTypeDto transportTypeToDto(TransportType transportType);

    @Mapping(target = "id", source = "transportTypeDto.transportTypeDtoId")
    TransportType dtoToTransportType(TransportTypeDto transportTypeDto);
}
