package ru.spring.manager.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.manager.entity.Product;
import ru.spring.manager.payload.NewProductPayload;
import ru.spring.manager.payload.UpdatedProductPayload;
import ru.spring.manager.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId){
        return this.productService.findProductById(productId).orElseThrow();
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
    public String UpdateProduct( @ModelAttribute("product") Product product, UpdatedProductPayload payload){
        this.productService.updateProduct(product.getId(), payload.title(), payload.details(), payload.price(), payload.quantity());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
}
