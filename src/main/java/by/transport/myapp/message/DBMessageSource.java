package by.transport.myapp.message;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.dto.TransportTypeDto;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DBMessageSource implements MessageSource {

    @Override
    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        return resolveMessage(s, objects, locale);
    }

    @Override
    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        return resolveMessage(s, objects, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }

    private String resolveMessage(String s, Object[] objects, Locale locale) {
        if (objects[0].getClass() == TransportTypeDto.class) {
            TransportTypeDto typeDto = (TransportTypeDto) objects[0];

            switch (locale.toString()) {
                case "by":
                    return typeDto.getDescriptionBy();
                case "en":
                    return typeDto.getDescriptionEn();
                default:
                    return typeDto.getDescription();
            }
        } else if (objects[0].getClass() == RouteStopDto.class) {
            RouteStopDto routeStopDto = (RouteStopDto) objects[0];

            switch (locale.toString()) {
                case "by":
                    return routeStopDto.getTypeBy();
                case "en":
                    return routeStopDto.getTypeEn();
                default:
                    return routeStopDto.getType();
            }
        } else {
            return "";
        }
    }
}
