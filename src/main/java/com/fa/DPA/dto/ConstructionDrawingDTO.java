package com.fa.DPA.dto;

import com.fa.DPA.model.ConstructionDrawing;
import lombok.Data;

@Data
public class ConstructionDrawingDTO extends AbstractDTO{
    private String name;
    private String imgSource;
    private float price;
    private String thumbnail;
    private String code;
    private String description;
    private int discount;
    private boolean status;
    private String createBy;






    public ConstructionDrawingDTO(ConstructionDrawing constructionDrawing) {
        this.id =  constructionDrawing.getId();
        this.name = constructionDrawing.getName();
        this.imgSource = constructionDrawing.getImgSource();
        this.thumbnail=constructionDrawing.getThumbnail();
        this.price=constructionDrawing.getPrice();
        this.code = constructionDrawing.getCode();
        this.description = constructionDrawing.getDescription();
        this.discount = constructionDrawing.getDiscount() == null ? 0 : constructionDrawing.getDiscount().getAmount();
        this.status = constructionDrawing.isStatus();
        this.createBy = constructionDrawing.getStaffAccount() == null ? "" : constructionDrawing.getStaffAccount().getStaff_name();
    }


}
