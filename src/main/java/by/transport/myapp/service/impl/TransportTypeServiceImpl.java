package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.mapper.TransportTypeMapper;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.service.TransportTypeService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportTypeServiceImpl implements TransportTypeService {
    private final TransportTypeDao transportTypeDao;
    private final TransportTypeMapper mapper = Mappers.getMapper(TransportTypeMapper.class);

    public TransportTypeServiceImpl(TransportTypeDao transportTypeDao) {
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public List<TransportTypeDto> getTypes() {
        return transportTypeDao.findAll().stream().map(mapper::transportTypeToDto).collect(Collectors.toList());
    }

    @Override
    public String getTypeDescription(Integer id) {
        return transportTypeDao.getById(id).getDescription();
    }

    @Override
    public TransportTypeDto getTransportTypeById(Integer id) {
        return mapper.transportTypeToDto(transportTypeDao.getById(id));
    }
}
