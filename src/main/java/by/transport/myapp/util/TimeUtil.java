package by.transport.myapp.util;

import by.transport.myapp.dto.RouteLineDto;
import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteLine;

import java.time.LocalTime;

public class TimeUtil {

    private TimeUtil() {
    }

    public static void findTime(RouteLineDto routeLineDto, Route route) {
        LocalTime closestTime = calcClosestTime(routeLineDto.getStopOrder(), route);
        LocalTime nextTime = calcNextTime(closestTime, route.getIntervalWeekday(), route.getEndWeekday());

        routeLineDto.setClosestTime(closestTime);
        routeLineDto.setNextTime(nextTime);
    }

    public static void findTimeStop(RouteLineStopDto routeLineStopDto, Route route) {
        LocalTime closestTime = calcClosestTime(routeLineStopDto.getStopOrder(), route);
        LocalTime nextTime = calcNextTime(closestTime, route.getIntervalWeekday(), route.getEndWeekday());

        routeLineStopDto.setClosestTime(closestTime);
        routeLineStopDto.setNextTime(nextTime);
    }

    private static LocalTime calcClosestTime(int stopOrder, Route route) {
        var currentTime = LocalTime.now();
        LocalTime endTime = route.getEndWeekday();
        LocalTime startTime = route.getStartWeekday();

        if (stopOrder > 1) {
            for (RouteLine routeLine : route.getRouteLines()) {
                if (stopOrder <= routeLine.getStopOrder()) {
                    startTime = startTime.plusMinutes(routeLine.getTimePrev());
                    endTime = endTime.plusMinutes(routeLine.getTimePrev());
                }
            }
        }

        LocalTime closestTime = startTime;
        if (compareTime(currentTime, endTime)) {
            var interval = route.getIntervalWeekday();

            while (compareTime(closestTime, currentTime)) {
                closestTime = closestTime.plusMinutes(interval);
            }

            if (compareTime(endTime, closestTime)) {
                closestTime = startTime;
            }
        }
        return closestTime;
    }

    private static LocalTime calcNextTime(LocalTime nextTime, int interval, LocalTime endTime) {
        nextTime = nextTime.plusMinutes(interval);
        if (compareTime(nextTime, endTime)) {
            return nextTime;
        }
        return null;
    }

    private static boolean compareTime(LocalTime firstTime, LocalTime secondTime) {
        return firstTime.compareTo(secondTime) < 0;
    }
}
