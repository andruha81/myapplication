package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.RouteLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteLineDao extends JpaRepository<RouteLine, Integer> {
}
