//package vn.techmaster.demoupload.controller;
//
//import com.example.demo.controller.StorageService;
//import com.example.demo.model.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//@RequestMapping("/api")
//public class UploadAPI {
//    @Autowired
//    private StorageService storageService;
//
//    @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
//    public String upload(@ModelAttribute Product product, Model model) {
//        storageService.uploadFile(product.getPhoto());
//        return product.getId()+"-"+ product.getName() + "-" + product.getCategory()
//                +"-"+product.getDetail();
//    }
//}