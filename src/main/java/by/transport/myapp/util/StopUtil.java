package by.transport.myapp.util;

import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.StopDto;

import java.util.List;
import java.util.stream.Collectors;

public class StopUtil {

    private StopUtil(){}

    public static List<StopDto> removeStops(List<StopDto> stops, List<RouteLineParamDto> routeLines) {
        List<StopDto> removeStops = routeLines.stream().map(RouteLineParamDto::getStopDto).collect(Collectors.toList());
        stops.removeAll(removeStops);
        return stops;
    }
}
