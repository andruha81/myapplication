package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeDao extends JpaRepository<TransportType, Integer> {
    TransportType findTransportTypeByDescription(String description);
}
