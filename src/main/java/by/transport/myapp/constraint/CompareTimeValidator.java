package by.transport.myapp.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalTime;

public class CompareTimeValidator implements ConstraintValidator<CompareTime, Object> {
    private String beforeWeekFieldName;
    private String afterWeekFieldName;
    private String beforeOffFieldName;
    private String afterOffFieldName;

    @Override
    public void initialize(final CompareTime constraintAnnotation) {
        beforeWeekFieldName = constraintAnnotation.beforeWeek();
        afterWeekFieldName = constraintAnnotation.afterWeek();
        beforeOffFieldName = constraintAnnotation.beforeOff();
        afterOffFieldName = constraintAnnotation.afterOff();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Field beforeWeekField = value.getClass().getDeclaredField(beforeWeekFieldName);
            beforeWeekField.setAccessible(true);

            final Field afterWeekField = value.getClass().getDeclaredField(afterWeekFieldName);
            afterWeekField.setAccessible(true);

            final Field beforeOffField = value.getClass().getDeclaredField(beforeOffFieldName);
            beforeOffField.setAccessible(true);

            final Field afterOffField = value.getClass().getDeclaredField(afterOffFieldName);
            afterOffField.setAccessible(true);

            final LocalTime beforeWeekTime = (LocalTime) beforeWeekField.get(value);
            final LocalTime afterWeekTime = (LocalTime) afterWeekField.get(value);

            final LocalTime beforeOffTime = (LocalTime) beforeOffField.get(value);
            final LocalTime afterOffTime = (LocalTime) afterOffField.get(value);

            return (beforeWeekTime == null && afterWeekTime == null
                    && beforeOffTime == null && afterOffTime == null)
                    || (beforeWeekTime != null && beforeWeekTime.isBefore(afterWeekTime)
                    && beforeOffTime != null && beforeOffTime.isBefore(afterOffTime));
        } catch (final Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
