package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.RouteLine;
import by.transport.myapp.model.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteLineDao extends JpaRepository<RouteLine, Integer> {
    List<RouteLine> getRouteLinesByStop(Stop stop);
}
