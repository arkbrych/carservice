package pl.brych.carservice.controller;

import org.springframework.http.MediaType;
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
    public ResponseEntity<List<Car>> getCars() {
        List<Car> carList = (List<Car>) carRepository.findAll();
        return ResponseEntity.ok(carList);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carRepository.save(car);
        return ResponseEntity.ok(newCar);
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

    @GetMapping("/cars/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {

        List<Car> cars = carService.getCarsByColorService(color);

        if (cars.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cars);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        Optional<Car> first = carRepository.findById(id);
        if (first.isPresent()) {
            carRepository.deleteById(id);
            return ResponseEntity.ok(first.get());
        } else return ResponseEntity.notFound().build();
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> modOneCarField(@RequestParam("color") String newColor, @PathVariable("id") Integer id) {
//        Optional<Car> first = carServiceImp.getCarByIdService(id);
//        if (first.isPresent()) {
//            carServiceImp.getAllCarsService().get(id - 1).setColor(newColor);
//            return ResponseEntity.ok().build();
//        } else return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping
//    public ResponseEntity<Car> modCar(@RequestBody Car newCar) {
//        Optional<Car> first = carServiceImp.getCarByIdService(newCar.getId());
//        if (first.isPresent()) {
//            carServiceImp.getAllCarsService().remove(first.get());
//            carServiceImp.getAllCarsService().add(newCar);
//            return ResponseEntity.ok().build();
//        } else return ResponseEntity.notFound().build();
//    }
}
