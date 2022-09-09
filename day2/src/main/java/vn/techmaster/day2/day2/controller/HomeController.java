package vn.techmaster.day2.day2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.day2.day2.model.Animal;
import vn.techmaster.day2.day2.model.Car;
import vn.techmaster.day2.day2.repository.CarRepository;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    CarRepository repo;
    @Autowired
    public HomeController(CarRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String home(){
        return "Home";
    }

    @GetMapping(value = "/json",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Car> getCarJson(){
        CarRepository carRepository=new CarRepository();
        ArrayList<Car> carArrayList=new ArrayList<>();
        carArrayList=  carRepository.getAllCar();
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        return carArrayList;
    }

}
