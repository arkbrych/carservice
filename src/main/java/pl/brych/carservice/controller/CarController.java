package pl.brych.carservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brych.carservice.model.Car;
import pl.brych.carservice.repository.CarRepository;
import pl.brych.carservice.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;

    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {
        return (List<Car>) carRepository.findAll();
    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car) {
        carRepository.save(car);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable long id) {
        Optional<Car> carById = carRepository.findById(id);
        if (carById.isPresent()) {
            return ResponseEntity.ok(carById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cars/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {

        List<Car> cars = carService.getCarsByColorService(color);

        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cars);
        }
    }
}
