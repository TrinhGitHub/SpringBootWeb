package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private int productid;

    private String productname;

    private String description;

    private int price;

    private String image;
    private MultipartFile imageFile;
    private int quantity;

    private int categoryid;
    private CategoryModel categoryModel;
    private Boolean isSource = false;
    private Boolean addorupdate = false;
}
