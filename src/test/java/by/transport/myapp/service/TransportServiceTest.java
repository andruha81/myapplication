package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.entity.Transport;
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
public class TransportServiceTest {

    @Autowired
    private TransportService transportService;

    @MockBean
    private TransportDao transportDao;

    private final Integer id = 1;
    private final String model = "TestModel";
    private final int seatNum = 1;
    private final int carNum = 1;

    @Before
    public void setUp() {
        Transport transport = new Transport();
        transport.setId(id);
        transport.setModel(model);
        transport.setSeatNum(seatNum);
        transport.setCarNum(carNum);

        when(transportDao.getById(transport.getId()))
                .thenReturn(transport);

        when(transportDao.findTransportByTransportType(new TransportType()))
                .thenReturn(new ArrayList<>());
    }

    @Test
    public void getTransportByTransportTypeTest() {
        Integer typeId = 1;

        List<TransportDto> transports = transportService.getTransportByTransportType(typeId);
        assertThat(transports).isNotNull();
    }

    @Test
    public void transportModelTest() {
        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getModel())
                .isEqualTo(model);
    }

    @Test
    public void transportSeatNumTest() {
        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getSeatNum())
                .isEqualTo(seatNum);
    }

    @Test
    public void transportCarNumTest() {
        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getCarNum())
                .isEqualTo(carNum);
    }

    @Test
    public void saveTest() {
        var isSaved = transportService.save(new TransportDto());
        assertThat(isSaved).isTrue();
    }

    @Test
    public void getTransportByIdTest() {
        TransportDto found = transportService.getTransportById(id);
        assertThat(found).isNotNull();
    }
}
