package com.fa.DPA.dto;


import lombok.Data;


@Data
public class SubcategoryDTO extends AbstractDTO{

    private String name;

    private boolean status;

    private String category;

    private long idCategory;


}
