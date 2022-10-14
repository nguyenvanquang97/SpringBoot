package vn.techmaster.cardemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    @Size(min=3,max=30,message = "Please input car brand")
    private String brand;
    @NotBlank(message = "Please enter the car maker")
    private String manufacturer;
    @NotBlank(message = "Please input your photo")
    private String photo;
}
