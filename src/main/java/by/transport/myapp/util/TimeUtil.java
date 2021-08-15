package by.transport.myapp.util;

import by.transport.myapp.dto.RouteLineDto;
import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteLine;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeUtil {
    private static LocalTime startTime;
    private static LocalTime endTime;
    private static int interval;

    private TimeUtil() {
    }

    public static void findTime(RouteLineDto routeLineDto, Route route) {
        setTime(route);

        LocalTime closestTime = calcClosestTime(routeLineDto.getStopOrder(), route);
        LocalTime nextTime = calcNextTime(closestTime);

        routeLineDto.setClosestTime(closestTime);
        routeLineDto.setNextTime(nextTime);
    }

    public static void findTimeStop(RouteLineStopDto routeLineStopDto, Route route) {
        setTime(route);

        LocalTime closestTime = calcClosestTime(routeLineStopDto.getStopOrder(), route);
        LocalTime nextTime = calcNextTime(closestTime);

        routeLineStopDto.setClosestTime(closestTime);
        routeLineStopDto.setNextTime(nextTime);
    }

    private static LocalTime calcClosestTime(int stopOrder, Route route) {
        var currentTime = LocalTime.now();

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
            while (compareTime(closestTime, currentTime)) {
                closestTime = closestTime.plusMinutes(interval);
            }

            if (compareTime(endTime, closestTime)) {
                closestTime = startTime;
            }
        }
        return closestTime;
    }

    private static LocalTime calcNextTime(LocalTime nextTime) {
        nextTime = nextTime.plusMinutes(interval);
        if (compareTime(nextTime, endTime)) {
            return nextTime;
        }
        return null;
    }

    private static boolean compareTime(LocalTime firstTime, LocalTime secondTime) {
        return firstTime.compareTo(secondTime) < 0;
    }

    private static void setTime(Route route) {
        LocalDate dateNow = LocalDate.now();
        if ((dateNow.getDayOfWeek() == DayOfWeek.SATURDAY) || (dateNow.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            startTime = route.getStartDayoff();
            endTime = route.getEndDayoff();
            interval = route.getIntervalDayoff();
        } else {
            startTime = route.getStartWeekday();
            endTime = route.getEndWeekday();
            interval = route.getIntervalWeekday();
        }
    }
}
