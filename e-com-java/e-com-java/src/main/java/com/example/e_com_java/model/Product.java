package com.example.e_com_java.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String desc;
    private BigDecimal price;
    private   String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releasedate;
    private boolean available;
    private int quantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    public void setImageName(String originalFilename) {
        this.imageName=originalFilename;
    }

    public void setImageType(String contentType) {
        this.imageType=contentType;

    }

    public void setImageData(byte[] bytes) {
        this.imageData=bytes;
    }

    public byte[] getimageData() {
        return this.imageData;
    }

    public String getimageType() {
        return this.imageType;
    }

    public String getimageName(String originalFilename) {
    return this.imageName;
    }



}
