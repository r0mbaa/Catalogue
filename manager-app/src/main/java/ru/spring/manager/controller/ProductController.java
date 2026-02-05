package ru.spring.manager.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.spring.manager.entity.Product;
import ru.spring.manager.payload.NewProductPayload;
import ru.spring.manager.payload.UpdatedProductPayload;
import ru.spring.manager.service.ProductService;

import java.net.http.HttpResponse;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId){
        return this.productService.findProductById(productId)
                .orElseThrow(()-> new NoSuchElementException("Product not found"));
    }

    @GetMapping()
    public String getProduct(){
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getEditPage(){
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String UpdateProduct(@ModelAttribute(name = "product", binding = false) Product product, @Valid UpdatedProductPayload payload,
                                BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage).toList());
            return "catalogue/products/edit";
        }
        else{
            this.productService.updateProduct(product.getId(), payload.title(), payload.details(), payload.price(), payload.quantity());
            return "redirect:/catalogue/products/%d".formatted(product.getId());
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handlerNoSuchElementExeption(NoSuchElementException exception, Model model, HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",exception.getMessage());
        return "errors/404";
    }

}
