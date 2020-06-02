package pl.brych.carservice.controller;

import org.springframework.web.bind.annotation.*;
import pl.brych.carservice.model.Car;
import pl.brych.carservice.repository.CarRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return (List<Car>) carRepository.findAll();
    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car){
        carRepository.save(car);
    }
}
