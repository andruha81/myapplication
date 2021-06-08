package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportDao extends JpaRepository<Transport, Integer> {
}
