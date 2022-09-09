package vn.techmaster.day2.day2.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import vn.techmaster.day2.day2.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class CarRepository {
    private ArrayList<Car> cars = new ArrayList<>();
    /* Nhiệm vụ của constructor này là đọc dữ liệu từ file JSON vào một ArrayList<Person>
     */
    public CarRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/Car.json");
            ObjectMapper mapper = new ObjectMapper();
            cars.addAll(mapper.readValue(file, new TypeReference<List<Car>>(){}));
            cars.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Car> getAllCar() {
        return cars;
    }
}
