package pl.brych.carservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.brych.carservice.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
