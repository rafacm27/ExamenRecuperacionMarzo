package com.cesur.examen.tienda.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items")
public class Item {
    @Id
    private String _id;
    private String id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private Double rate;
    private Integer count;
    private String color;
    private String manufacturer;
    private String EAN;
    private String image;
}
