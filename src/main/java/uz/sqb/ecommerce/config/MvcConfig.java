package uz.sqb.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    private  String path = "C:/Users/Javlon-Bezop/Desktop/ecommerce/src/main/resources/static/images";


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img/**").addResourceLocations("file:///" + path + "/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/","classpath:/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/","classpath:/static/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
    }
}
