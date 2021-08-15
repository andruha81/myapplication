package by.transport.myapp.controller;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.service.StopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StopService stopService;

    private StopDto stopDto;
    private final Integer stopId = 1;

    @Before
    public void setUp() {
        stopDto = StopDto.builder()
                .stopDtoId(stopId)
                .name("Test stop")
                .build();

        when(stopService.getStopById(stopId))
                .thenReturn(stopDto);
    }

    @Test
    public void showStopParametersTest() throws Exception {
        mockMvc.perform(get("/stop/edit")
                .with(user("testUser"))
                .param("id", String.valueOf(stopId)))
                .andExpect(status().isOk())
                .andExpect(view().name("stop/stop-parameters"))
                .andExpect(model().attributeExists("stop"))
                .andExpect(model().attribute("stop", stopDto))
                .andExpect(model().size(2));

        verify(stopService, times(1)).getStopById(stopId);
        verifyNoMoreInteractions(stopService);
    }

    @Test
    public void addStopTest() throws Exception {
        mockMvc.perform(get("/stop/add")
                .with(user("testUser")))
                .andExpect(status().isOk())
                .andExpect(view().name("stop/stop-parameters"))
                .andExpect(model().attributeExists("stop"))
                .andExpect(model().size(2));
    }

    @Test
    public void saveStopTest() throws Exception {
        mockMvc.perform(post("/stop/save")
                .with(user("testUser"))
                .param("stopDtoId", "1")
                .param("name", "Test stop")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dispatcher/stop"));
    }
}
