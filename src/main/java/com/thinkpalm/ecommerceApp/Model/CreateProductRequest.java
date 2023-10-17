package com.thinkpalm.ecommerceApp.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductRequest {
    private String title;
    private String description;
    private Float price;
    private Integer quantity;
    private Integer category_id;
    private String image;

}
