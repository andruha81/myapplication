package by.transport.myapp.util;

import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.RouteParamDto;

public class RouteUtil {

    private RouteUtil() {
    }

    public static void removeStop(RouteParamDto routeParamDto, Integer rlId) {
        int stopOrder = routeParamDto
                .getRouteLines()
                .stream()
                .filter(x -> x.getRouteLineParamDtoId() == rlId)
                .mapToInt(RouteLineParamDto::getStopOrder)
                .findFirst()
                .orElse(0);

        routeParamDto.getRouteLines().removeIf(x -> x.getRouteLineParamDtoId().equals(rlId));

        routeParamDto
                .getRouteLines()
                .forEach(x -> {
            if(x.getStopOrder() > stopOrder) {
                x.setStopOrder(x.getStopOrder()-1);
            }
        });
    }
}
