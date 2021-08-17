package by.transport.myapp.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CompareTimeValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface CompareTime {
    String message() default "Время начала должно быть меньше времени окончания";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String beforeWeek();
    String afterWeek();
    String beforeOff();
    String afterOff();
}
