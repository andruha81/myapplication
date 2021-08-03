package by.transport.myapp.service;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

        when(transportTypeDao.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void getTypesTest() {
        List<TransportType> transportTypes = transportTypeDao.findAll();
        assertThat(transportTypes).isNotNull();
    }

    @Test
    public void getTypeDescriptionTest() {
        String foundDescription = transportTypeService.getTypeDescription(id);
        assertThat(foundDescription).isEqualTo(description);
    }

    @Test
    public void getTransportTypeByIdTest() {
        TransportTypeDto found = transportTypeService.getTransportTypeById(id);
        assertThat(found).isNotNull();
    }

    @Test
    public void getTransportTypeByDescriptionTest() {
        TransportType found = transportTypeService.getTransportTypeByDescription(description);
        assertThat(found).isNotNull();
    }
}
