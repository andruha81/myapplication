package by.transport.myapp.service;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.dto.StopDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public interface RouteLineService {
    Map<String, List<RouteLineStopDto>> getStopDetails(StopDto stopDto) throws EntityNotFoundException;
}
