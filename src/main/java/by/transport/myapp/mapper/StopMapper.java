package by.transport.myapp.mapper;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.entity.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StopMapper {
    @Mapping(target = "stopDtoId", source = "stop.id")
    StopDto stopToDto(Stop stop);
}
