package uz.sqb.ecommerce.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.sqb.ecommerce.entity.Category;
import uz.sqb.ecommerce.service.CategoryService;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CategoryController {

    CategoryService categoryService;

    public static final String ADD_CATEGORY_PAGE = "/addCategoryPage";
    public static final String ADD_CATEGORY = "/addCategory";
    public static final String CATEGORY_LIST = "/getCategoryList";
    public static final String CATEGORY_DELETE_BY_ID = "/deleteCategoryById";
    public static final String CATEGORY_PUT_BY_ID = "/putCategoryById";
    public static final String CATEGORY_PUT = "/putCategory";

    @GetMapping(CATEGORY_LIST)
    public String addCategoryPage(Model model){
        model.addAttribute("categories", categoryService.getCategoryList());
        return "category-list";
    }

    @GetMapping(ADD_CATEGORY_PAGE)
    public String addCategoryPage(){
        return "category-add-pages";
    }

    @PostMapping(ADD_CATEGORY)
    public String addCategory(@ModelAttribute @Valid Category category, BindingResult result, Model model){
        if (!result.hasErrors()){
            categoryService.save(category);
            return "redirect:/getCategoryList";
        } else {
            log.info(String.valueOf(result.getAllErrors()));
            model.addAttribute("error", "error");
            return "category-add-pages";
        }
    }

    @GetMapping(CATEGORY_DELETE_BY_ID)
    public String categoryDeleteById(@RequestParam Long id){
        categoryService.deleteById(id);
        return "redirect:/getCategoryList";
    }

    @GetMapping(CATEGORY_PUT_BY_ID)
    public String categoryPutById(@RequestParam Long id, Model model){
        model.addAttribute("category",categoryService.getById(id));
        return "category-put-page";
    }

    @PostMapping(CATEGORY_PUT)
    public String categoryPut(@RequestParam String type_of_product, @RequestParam Long id){
        categoryService.putCategory(type_of_product, id);
        return "redirect:/getCategoryList";
    }
}
