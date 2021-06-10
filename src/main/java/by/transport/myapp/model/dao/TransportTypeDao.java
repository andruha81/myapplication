package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeDao extends JpaRepository<TransportType, Integer> {
    //@Query("SELECT t FROM TransportType t WHERE LOWER(t.description) = LOWER(:description"))
    TransportType findTransportTypeByDescription(String description);
}
