package by.transport.myapp.controller;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.service.StopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/stop")
public class StopController {
    private final StopService stopService;
    private final MessageSource messageSource;
    private final Logger logger = LogManager.getLogger(StopController.class);

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String STOP_PARAMETERS = "stop/stop-parameters";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";

    public StopController(StopService stopService,
                          MessageSource messageSource) {
        this.stopService = stopService;
        this.messageSource = messageSource;
    }

    @GetMapping("/edit")
    public String showStopParameters(@RequestParam(name = "id") Integer stopId,
                                     Model model,
                                     Locale locale) {
        StopDto stopDto;

        if (stopId == null || stopId <= 0) {
            logger.error(String.format("Incorrect stop id %d", stopId));
            return REDIRECT_DISPATCHER;
        }

        try {
            stopDto = stopService.getStopById(stopId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find stop by id %d", stopId));
            return REDIRECT_DISPATCHER;
        }

        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerEditStop", null, locale));
        model.addAttribute("stop", stopDto);
        return STOP_PARAMETERS;
    }

    @GetMapping("/add")
    public String addStop(Model model, Locale locale) {
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerAddStop", null, locale));
        model.addAttribute("stop", new StopDto());
        return STOP_PARAMETERS;
    }

    @PostMapping("/save")
    public String saveStop(@ModelAttribute("stop") @Valid StopDto stopDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return STOP_PARAMETERS;
        }

        if (stopService.save(stopDto) == null) {
            logger.error(String.format("Didn't save stop %s to database", stopDto.getName()));
        } else {
            logger.info(String.format("Saved stop %s to database", stopDto.getName()));
        }

        return "redirect:/dispatcher/stop";
    }
}
