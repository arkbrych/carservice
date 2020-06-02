package pl.brych.carservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.brych.carservice.model.Car;
import pl.brych.carservice.repository.CarRepository;

@SpringBootApplication
public class CarserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CarRepository carRepository) {
        return args -> {
            carRepository.save(new Car("Fiat", "126p", "czerwony"));
            carRepository.save(new Car("Polonez", "FSO", "czerwony"));
            carRepository.save(new Car("Syrena", "105", "biały"));

            carRepository.findAll().forEach(System.out::println);
        };
    }
}
