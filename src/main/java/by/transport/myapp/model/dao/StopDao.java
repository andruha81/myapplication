package by.transport.myapp.model.dao;

import by.transport.myapp.model.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopDao extends JpaRepository<Stop, Integer> {
}
