package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteNumberDao extends JpaRepository<RouteNumber, Integer> {
    @Query("select r.number from RouteNumber r")
    List<Integer> getNumbers();

    List<RouteNumber> getRouteNumbersByType(TransportType transportType);
}
