package uz.sqb.ecommerce.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.sqb.ecommerce.entity.Product;
import uz.sqb.ecommerce.repository.CategoryRepository;
import uz.sqb.ecommerce.service.CategoryService;
import uz.sqb.ecommerce.service.OrderDetailsService;
import uz.sqb.ecommerce.service.ProductService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class ProductController {

    CategoryRepository categoryRepository;
    ProductService productService;
    CategoryService categoryService;
    OrderDetailsService orderDetailsService;

    public static final String ADD_PRODUCT_PAGE = "/addProductPage";
    public static final String ADD_PRODUCT_PROCESS = "/addProduct";
    public static final String GET_PRODUCT_LIST = "/getProductList";
    public static final String PUT_PRODUCT_BY_ID = "/putProduct";
    public static final String PUT_PRODUCT = "/putProductById";
    public static final String DELETE_PRODUCT = "/deleteProductById";
    public static final String EDIT_QUANTITY_PRODUCT = "/putQuantity";
    public static final String VIEW_PRODUCT_MORE_DETAILS = "/viewProductById/{id}";

    @GetMapping(ADD_PRODUCT_PAGE)
    public String addProductPage(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "product-add-page";
    }

    @PostMapping(ADD_PRODUCT_PROCESS)
    public String addProductProcess(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model, @RequestParam("image") MultipartFile file, @RequestParam Long category_id) throws IOException {
        if (!bindingResult.hasErrors() || !file.getOriginalFilename().isEmpty()){
            productService.addProductByParam(product, file, category_id);
            return "redirect:/addProductPage";
        } else {
            model.addAttribute("error", "error");
            model.addAttribute("categories", categoryRepository.findAll());
            return "product-add-page";
        }
    }

    @GetMapping(GET_PRODUCT_LIST)
    public String getProductList(Model model){
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @PostMapping(PUT_PRODUCT_BY_ID)
    public String putProductById(@RequestParam Long id,@RequestParam String name,@RequestParam String character,@RequestParam Long category_id,@RequestParam("p_price") String price, @RequestParam String description, @RequestParam(defaultValue = "0.0") Double discount){
        productService.updateProductParams(id, name, character, category_id, price, description, discount);
        return "redirect:/getProductList";
    }

    @GetMapping(PUT_PRODUCT)
    public String editPage(@RequestParam Long id, Model model){
        model.addAttribute("categories", categoryService.getCategoryList());
        model.addAttribute("product", productService.getProductById(id));
        return "product-put-page";
    }

    @GetMapping(DELETE_PRODUCT)
    public String deleteProduct(@RequestParam Long id){
        productService.deleteById(id);
        return "redirect:/getProductList";
    }

    @PostMapping(EDIT_QUANTITY_PRODUCT)
    public String putQuantityProduct(@RequestParam Long id, @RequestParam Integer quantity){
        orderDetailsService.putQuantityProduct(id, quantity);
        return "redirect:/getCartBySessionId";
    }

    @GetMapping(VIEW_PRODUCT_MORE_DETAILS)
    public String viewProductById(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id);
        product.setView_count(product.getView_count() + 1);
        productService.save(product);
        model.addAttribute("product", product);
        return "product-detail";
    }

}
