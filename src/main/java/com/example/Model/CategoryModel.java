package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private int cateid;

    private String catename;
    private String image;
    private MultipartFile imageFile;
    private Boolean isSource = false;
}
