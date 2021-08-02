package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.entity.Transport;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransportServiceTest {

    @Autowired
    private TransportService transportService;

    @MockBean
    private TransportDao transportDao;

    @Before
    public void setUp() {
        Transport transport = new Transport();
        transport.setId(1);
        transport.setModel("TestModel");
        transport.setSeatNum(1);
        transport.setCarNum(1);

        Mockito.when(transportDao.getById(transport.getId()))
                .thenReturn(transport);
    }

    @Test
    public void transportModelTest() {
        Integer id = 1;
        String model = "TestModel";

        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getModel())
                .isEqualTo(model);
    }

    @Test
    public void transportSeatNumTest() {
        Integer id = 1;
        int seatNum = 1;

        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getSeatNum())
                .isEqualTo(seatNum);
    }

    @Test
    public void transportCarNumTest() {
        Integer id = 1;
        int carNum = 1;

        TransportDto found = transportService.getTransportById(id);
        assertThat(found.getCarNum())
                .isEqualTo(carNum);
    }

    @Test
    public void saveTransportTest() {
        var isSaved = transportService.save(new TransportDto());
        Assertions.assertTrue(isSaved);
    }
}
