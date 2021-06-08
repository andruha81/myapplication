package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDao extends JpaRepository<Route, Integer> {
}
