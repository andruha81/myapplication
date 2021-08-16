package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.StopService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DispatcherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StopService stopService;
    @MockBean
    private TransportTypeService transportTypeService;
    @MockBean
    private TransportService transportService;
    @MockBean
    private RouteNumberService routeNumberService;

    private TransportTypeDto transportType;
    private final List<TransportTypeDto> types = new ArrayList<>();
    private final List<StopDto> stops = new ArrayList<>();
    private final List<TransportDto> transports = new ArrayList<>();
    private final List<RouteNumberDto> numbers = new ArrayList<>();
    private final int typeId = 1;

    @Before
    public void setUp() {
        String typeDescription = "Test type";

        transportType = TransportTypeDto.builder()
                .transportTypeDtoId(typeId)
                .description(typeDescription)
                .build();

        stops.add(StopDto.builder()
                .stopDtoId(1)
                .name("test")
                .build());

        types.add(TransportTypeDto.builder()
                .transportTypeDtoId(1)
                .description("test type")
                .build());

        transports.add(TransportDto.builder()
                .transportDtoId(1)
                .type(typeDescription)
                .route(1)
                .model("Test model")
                .carNum(1)
                .seatNum(1)
                .build());

        numbers.add(RouteNumberDto.builder()
                .routeNumberDtoId(1)
                .number(1)
                .build());

        when(transportTypeService.getTransportTypeById(typeId))
                .thenReturn(transportType);

        when(stopService.getStops())
                .thenReturn(stops);

        when(transportTypeService.getTypes())
                .thenReturn(types);

        when(transportService.getTransportByTransportType(transportType))
                .thenReturn(transports);

        when(routeNumberService.getRoutes(transportType))
                .thenReturn(numbers);
    }

    @Test
    public void showStopsTest() throws Exception {
        mockMvc.perform(get("/dispatcher/stop").with(user("testUser")))
                .andExpect(status().isOk())
                .andExpect(view().name("dispatcher/stop-list"))
                .andExpect(model().attributeExists("stops"))
                .andExpect(model().attribute("stops", stops))
                .andExpect(model().size(2));

        verify(stopService, times(1)).getStops();
        verifyNoMoreInteractions(stopService);
    }

    @Test
    public void showDispatcherControlTest() throws Exception {
        mockMvc.perform(get("/dispatcher/all").with(user("testUser")))
                .andExpect(status().isOk())
                .andExpect(view().name("dispatcher/dispatcher"))
                .andExpect(model().attributeExists("transportTypes"))
                .andExpect(model().attribute("transportTypes", types))
                .andExpect(model().size(2));

        verify(transportTypeService, times(1)).getTypes();
        verifyNoMoreInteractions(transportTypeService);
    }

    @Test
    public void showTransportListTest() throws Exception {
        mockMvc.perform(get("/dispatcher/list")
                .with(user("testUser"))
                .param("type", String.valueOf(typeId)))
                .andExpect(status().isOk())
                .andExpect(view().name("dispatcher/transport-list"))
                .andExpect(model().attributeExists("transportType"))
                .andExpect(model().attributeExists("transports"))
                .andExpect(model().attribute("transportType", transportType))
                .andExpect(model().attribute("transports", transports))
                .andExpect(model().size(3));

        verify(transportTypeService, times(1)).getTransportTypeById(typeId);
        verify(transportService, times(1)).getTransportByTransportType(transportType);
        verifyNoMoreInteractions(transportTypeService);
        verifyNoMoreInteractions(transportService);
    }

    @Test
    public void showTransportRoutesTest() throws Exception {
        mockMvc.perform(get("/dispatcher/route")
                .with(user("testUser"))
                .param("type", String.valueOf(typeId)))
                .andExpect(status().isOk())
                .andExpect(view().name("dispatcher/route-list"))
                .andExpect(model().attributeExists("transportType"))
                .andExpect(model().attributeExists("routesNumber"))
                .andExpect(model().attribute("transportType", transportType))
                .andExpect(model().attribute("routesNumber", numbers))
                .andExpect(model().size(3));

        verify(transportTypeService, times(1)).getTransportTypeById(typeId);
        verify(routeNumberService, times(1)).getRoutes(transportType);
        verifyNoMoreInteractions(transportTypeService);
        verifyNoMoreInteractions(routeNumberService);
    }

}
