package by.transport.myapp.controller;

import by.transport.myapp.dto.*;
import by.transport.myapp.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimetableControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StopService stopService;
    @MockBean
    private RouteLineService routeLineService;
    @MockBean
    private TransportTypeService typeService;
    @MockBean
    private RouteNumberService routeNumberService;
    @MockBean
    private RouteService routeService;

    private final List<StopDto> stops = new ArrayList<>();
    private final HashMap<String, List<RouteLineStopDto>> routeLines = new HashMap<>();
    private final List<RouteNumberDto> routeNumbers = new ArrayList<>();
    private StopDto stopDto;

    private RouteStopDto routeStopDto;
    private TransportTypeDto typeDto;
    private final Integer stopId = 1;
    private final Integer typeId = 1;
    private final Integer routeId = 1;

    private final String typeDescription = "Test type";

    @Before
    public void setUp() {
        RouteLineStopDto routeLineStopDto;
        RouteNumberDto routeNumberDto;
        int routeNumber = 1;
        String routeDescription = "Test route";

        stopDto = StopDto.builder()
                .stopDtoId(stopId)
                .name("Test stop")
                .build();

        stops.add(stopDto);

        typeDto = TransportTypeDto.builder()
                .transportTypeDtoId(typeId)
                .description(typeDescription)
                .build();

        routeLineStopDto = RouteLineStopDto.builder()
                .routeLineStopDtoId(1)
                .routeId(routeId)
                .routeDescription(routeDescription)
                .routeNumber("1")
                .stopOrder(1)
                .type(typeDescription)
                .closestTime(LocalTime.of(11, 11))
                .nextTime(LocalTime.of(11, 11))
                .build();

        routeLines.put(typeDescription, Collections.singletonList(routeLineStopDto));

        routeNumberDto = RouteNumberDto.builder()
                .routeNumberDtoId(1)
                .number(routeNumber)
                .build();

        routeNumbers.add(routeNumberDto);

        routeStopDto = RouteStopDto.builder()
                .routeStopDtoId(routeId)
                .description(routeDescription)
                .type(typeDescription)
                .number(routeNumber)
                .routeLines(new TreeSet<>())
                .build();

        when(stopService.getStops())
                .thenReturn(stops);

        when(stopService.getStopById(stopId))
                .thenReturn(stopDto);

        when(routeLineService.getStopDetails(stopDto))
                .thenReturn(routeLines);

        when(typeService.getTransportTypeById(typeId))
                .thenReturn(typeDto);

        when(routeNumberService.getRoutes(typeDto))
                .thenReturn(routeNumbers);

        when(routeService.getRouteDetails(routeId))
                .thenReturn(routeStopDto);
    }

    @Test
    public void showStopsTest() throws Exception {
        mockMvc.perform(get("/timetable/stops/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetable/stop"))
                .andExpect(model().attributeExists("stops"))
                .andExpect(model().attribute("stops", stops))
                .andExpect(model().size(4));

        verify(stopService, times(1)).getStops();
        verifyNoMoreInteractions(stopService);
    }

    @Test
    public void showStopDetailTest() throws Exception {
        mockMvc.perform(get("/timetable/stops/{stopId}/{detail}", stopId, "route"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetable/route::stopDetail"))
                .andExpect(model().attributeExists("stopN"))
                .andExpect(model().attributeExists("stopDetails"))
                .andExpect(model().attribute("stopN", stopDto.getName()))
                .andExpect(model().attribute("stopDetails", routeLines))
                .andExpect(model().size(2));

        verify(stopService, times(1)).getStopById(stopId);
        verify(routeLineService, times(1)).getStopDetails(stopDto);
        verifyNoMoreInteractions(stopService);
        verifyNoMoreInteractions(routeLineService);
    }

    @Test
    public void showRoutesTest() throws Exception {
        mockMvc.perform(get("/timetable/routes/type/{id}", typeId))
                .andExpect(status().isOk())
                .andExpect(view().name("timetable/route"))
                .andExpect(model().attributeExists("routesNumber"))
                .andExpect(model().attribute("routesNumber", routeNumbers))
                .andExpect(model().attribute("headerMessage", typeDescription))
                .andExpect(model().size(7));

        verify(typeService, times(1)).getTransportTypeById(typeId);
        verify(routeNumberService, times(1)).getRoutes(typeDto);
        verifyNoMoreInteractions(typeService);
        verifyNoMoreInteractions(routeNumberService);
    }

    @Test
    public void showRouteDetailsTest() throws Exception {
        mockMvc.perform(get("/timetable/routes/{id}", routeId))
                .andExpect(status().isOk())
                .andExpect(view().name("timetable/route::routeStop"))
                .andExpect(model().attributeExists("routeN"))
                .andExpect(model().attributeExists("descN"))
                .andExpect(model().attributeExists("routeDetail"))
                .andExpect(model().attribute("routeN", routeStopDto.getType() + " № " + routeStopDto.getNumber()))
                .andExpect(model().attribute("descN", routeStopDto.getDescription()))
                .andExpect(model().attribute("routeDetail", routeStopDto.getRouteLines()))
                .andExpect(model().size(4));

        verify(routeService, times(1)).getRouteDetails(routeId);
        verifyNoMoreInteractions(routeService);
    }
}
