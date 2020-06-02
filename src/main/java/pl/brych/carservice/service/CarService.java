package pl.brych.carservice.service;

import org.springframework.stereotype.Service;
import pl.brych.carservice.model.Car;
import pl.brych.carservice.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCarsByColorService(String color) {
        return carRepository.getCarsByColor(color);
    }

}
