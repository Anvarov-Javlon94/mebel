package uz.sqb.ecommerce.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.sqb.ecommerce.entity.Category;
import uz.sqb.ecommerce.entity.Product;
import uz.sqb.ecommerce.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class ProductService {

    ProductRepository productRepository;

    private  String path = "C:\\Users\\Javlon-Bezop\\Desktop\\ecommerce\\src\\main\\resources\\static\\images\\";

    public void addProductByParam(Product product, MultipartFile file, Long category_id) throws IOException {
        Date dateTime = new Date();
        String imagePath = "/static/img/";
        String uuid = UUID.randomUUID().toString();
        uploadFile(file, uuid);
        String fileUUIDName = uuid + "." + file.getOriginalFilename();
        product.setPhoto(imagePath + fileUUIDName);
        product.setRegister_date(dateTime);
        product.setCategory(new Category(category_id));
        product.setView_count(1);
        save(product);
    }

    public void uploadFile(MultipartFile file, String uuid) throws IOException {
        file.transferTo(new File(path + uuid + "." + file.getOriginalFilename()));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void updateProductParams(Long id, String name, String character, Long category_id, String price, String description, Double discount) {
        Product product = productRepository.getById(id);
        product.setName(name);
        product.setProduct_character(character);
        product.setCategory(new Category(category_id));
        Double price1 = Double.parseDouble(price);
        product.setPrice(price1);
        Double afterDiscountPrice = (price1 * discount) / 100;
        product.setAfter_discount(price1 - afterDiscountPrice);
        product.setProduct_description(description);
        product.setDiscount(discount);
        productRepository.save(product);
    }

    public Product getProductById(Long id){
        return productRepository.getById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

}
