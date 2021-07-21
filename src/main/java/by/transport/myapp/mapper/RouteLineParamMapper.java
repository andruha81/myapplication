package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteLineNewParamDto;
import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.StopDto;

public class RouteLineParamMapper {

    private RouteLineParamMapper() {
    }

    public static RouteLineParamDto map(RouteLineNewParamDto routeLineNewParamDto, StopDto stopDto) {
        RouteLineParamDto routeLineParamDto = new RouteLineParamDto();
        routeLineParamDto.setRouteLineParamDtoId(routeLineNewParamDto.getRouteLineNewParamDtoId());
        routeLineParamDto.setStopDto(stopDto);
        routeLineParamDto.setStopOrder(routeLineNewParamDto.getStopOrder());
        routeLineParamDto.setTimePrev(routeLineNewParamDto.getTimePrev());
        routeLineParamDto.setDistancePrev(routeLineNewParamDto.getDistancePrev());

        return routeLineParamDto;
    }
}
