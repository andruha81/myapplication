package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.RouteNumberService;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransportControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TransportTypeService transportTypeService;
    @MockBean
    private RouteNumberService routeNumberService;
    @MockBean
    private TransportService transportService;

    private TransportTypeDto transportType;
    private TransportType type;
    private TransportDto transport;
    private final List<Integer> numbers = Collections.singletonList(1);
    private final Integer typeId = 1;
    private final Integer transportId = 1;
    private final String typeDescription = "Test type";

    @Before
    public void setUp() {
        transportType = TransportTypeDto.builder()
                .transportTypeDtoId(typeId)
                .description(typeDescription)
                .build();

        type = TransportType.builder()
                .id(typeId)
                .description(typeDescription)
                .build();

        transport = TransportDto.builder()
                .transportDtoId(transportId)
                .model("Test model")
                .type(typeDescription)
                .carNum(1)
                .seatNum(1)
                .build();

        when(transportTypeService.getTransportTypeById(typeId))
                .thenReturn(transportType);

        when(routeNumberService.getRouteNumbersByType(transportType))
                .thenReturn(numbers);

        when(transportService.getTransportById(transportId))
                .thenReturn(transport);

        when(transportTypeService.getTransportTypeByDescription(typeDescription))
                .thenReturn(transportType);

        when(transportService.save(transport, transportType))
                .thenReturn(transportId);
    }

    @Test
    public void addTransportFormTest() throws Exception {
        mockMvc.perform(get("/transport/add")
                .with(user("testUser"))
                .param("type", String.valueOf(typeId)))
                .andExpect(status().isOk())
                .andExpect(view().name("transport/new-transport"))
                .andExpect(model().attributeExists("transportType"))
                .andExpect(model().attributeExists("routeNumbers"))
                .andExpect(model().attributeExists("transport"))
                .andExpect(model().attribute("transportType", transportType))
                .andExpect(model().attribute("routeNumbers", numbers))
                .andExpect(model().size(4));

        verify(transportTypeService, times(1)).getTransportTypeById(typeId);
        verify(routeNumberService, times(1)).getRouteNumbersByType(transportType);
        verifyNoMoreInteractions(transportTypeService);
        verifyNoMoreInteractions(routeNumberService);
    }

    @Test
    public void editTransportFormTest() throws Exception {
        mockMvc.perform(get("/transport/edit")
                .with(user("testUser"))
                .param("id", String.valueOf(transportId))
                .param("type", String.valueOf(typeId)))
                .andExpect(status().isOk())
                .andExpect(view().name("transport/edit-transport"))
                .andExpect(model().attributeExists("routeNumbers"))
                .andExpect(model().attributeExists("transport"))
                .andExpect(model().attribute("transport", transport))
                .andExpect(model().attribute("routeNumbers", numbers))
                .andExpect(model().size(3));

        verify(transportService, times(1)).getTransportById(typeId);
        verify(routeNumberService, times(1)).getRouteNumbersByType(transportType);
        verifyNoMoreInteractions(transportService);
        verifyNoMoreInteractions(routeNumberService);
    }

    @Test
    public void addTransportTest() throws Exception {
        String model = "Test model";
        String typeString = "Test type";
        String seatNum = "1";
        String carNum = "1";
        String route = "1";

        mockMvc.perform(post("/transport/add")
                .with(user("testUser"))
                .param("model", model)
                .param("type", typeString)
                .param("seatNum", seatNum)
                .param("carNum", carNum)
                .param("route", route)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dispatcher/list?type=" + type.getId()));

        verify(transportTypeService, times(1)).getTransportTypeByDescription(typeDescription);
        verifyNoMoreInteractions(transportTypeService);
    }
}
