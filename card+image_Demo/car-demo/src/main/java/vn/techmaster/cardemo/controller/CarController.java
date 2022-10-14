package vn.techmaster.cardemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.cardemo.entity.Car;
import vn.techmaster.cardemo.repository.CarRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepo;
    @GetMapping
    public String home(){
        return "index";
    }

    @GetMapping("/listAll")
    public String listAll(Model model){
        model.addAttribute("cars",carRepo.getAll());
        return "listAll";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "carForm";
    }

    @PostMapping("/post")
    public String postInfo(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if(car.getId()>0){
                carRepo.edit(car);
            }else{
                carRepo.create(car);
            }
            model.addAttribute("cars", carRepo.getAll());
            return "redirect:/listAll";
        }
        return "carForm";
    }

    @GetMapping("/car/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Car> car = carRepo.get(id);
        if(car.isPresent()){
            model.addAttribute("car",car.get());
        }
        return "carForm";
    }

    @GetMapping("/car/delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model){
        carRepo.deleteById(id);
        model.addAttribute("cars",carRepo.getAll());
        return "redirect:/listAll";
    }

    @GetMapping("/search")
    public String searchPerson(HttpServletRequest request, Model model){
        String brand = request.getParameter("brand");
        if(brand==""){
            model.addAttribute("cars",carRepo.getAll());
            return "redirect:/listAll";
        }
        else{
            Car car = carRepo.search(brand);
            model.addAttribute("cars",car);
            return "listAll";
        }
    }

    @GetMapping("/listAll/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Car car = carRepo.get(id).get();
        model.addAttribute("car",car);
        return "detail";
    }


}
