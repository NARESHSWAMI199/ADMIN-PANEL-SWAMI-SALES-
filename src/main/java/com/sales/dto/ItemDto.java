package com.sales.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private String wholesaleSlug;
    private float price;
    private float discount;
    private float rating;
    private String description;
    private String InStock;
    private  String slug;
    private String label;
}
