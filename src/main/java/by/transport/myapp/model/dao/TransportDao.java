package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportDao extends JpaRepository<Transport, Integer> {
    //@Query("SELECT t FROM Transport t WHERE t.transportType = :transportType")
    List<Transport> findTransportByTransportType(@Param("transportType") TransportType transportType);
}
