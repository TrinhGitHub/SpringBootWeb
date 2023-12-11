package com.example;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Config.CustomSiteMeshFilter;
import com.example.Services.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OriShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OriShopApplication.class, args);
    }


    @Bean
    CommandLineRunner init(IStorageService storageService){
        return (args -> {storageService.init();});
    }

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlulljayk",
                "api_key", "329154932364285",
                "api_secret", "3ftXnMHUJyPpLF90hmmyA05H5yc"));
        return c;
    }

    @Bean
    public FilterRegistrationBean<CustomSiteMeshFilter> siteMeshFilter(){
        FilterRegistrationBean<CustomSiteMeshFilter> filterFilterRegistrationBean = new FilterRegistrationBean<CustomSiteMeshFilter>();
        filterFilterRegistrationBean.setFilter(new CustomSiteMeshFilter());//add siteMesh filter
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
}
