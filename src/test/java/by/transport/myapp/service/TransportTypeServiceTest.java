package by.transport.myapp.service;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransportTypeServiceTest {

    @Autowired
    TransportTypeService transportTypeService;

    @MockBean
    TransportTypeDao transportTypeDao;

    private final Integer id = 1;
    String description = "Test type";

    @Before
    public void setUp() {
        TransportType transportType = new TransportType();
        transportType.setId(id);
        transportType.setDescription(description);

        when(transportTypeDao.getById(id))
                .thenReturn(transportType);

        when(transportTypeDao.findTransportTypeByDescription(description))
                .thenReturn(transportType);
    }

    @Test
    public void getTypeDescriptionTest() {
        String foundDescription = transportTypeService.getTypeDescription(id);
        assertThat(foundDescription).isEqualTo(description);
    }

    @Test
    public void getTransportTypeByIdTest() {
        TransportTypeDto found = transportTypeService.getTransportTypeById(id);
        Assertions.assertNotEquals(null, found);
    }

    @Test
    public void getTransportTypeByDescriptionTest() {
        TransportType found = transportTypeService.getTransportTypeByDescription(description);
        Assertions.assertNotEquals(null, found);
    }
}
