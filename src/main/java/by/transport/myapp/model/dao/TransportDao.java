package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportDao extends JpaRepository<Transport, Integer> {
    List<Transport> findTransportByTransportType(TransportType transportType);
    Transport findByModel(String model);
}
