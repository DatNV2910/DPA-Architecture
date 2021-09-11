package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "construction_drawing")
public class ConstructionDrawing extends AbstractModel {
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private int numberFloor;
//    @Column
//    private float areaSize;
    @Column
    private String description;
    @Column
    private String imgSource;
    @Column
    private float price;
    @Column
    private String thumbnail;
    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private StaffAccount staffAccount;

    @OneToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;


//    @ManyToMany(
//            mappedBy = "constructionDrawings"
//    )
//    private List<Order> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "primaryKey.constructionDrawing",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<CdOrder> cd_orders = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name ="subCategory")
    private SubCategory subCategory;


    @Override
    public String toString() {
        return "ConstructionDrawing{" +
                "id=" + getId() +
                ",name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", numberFloor=" + numberFloor +
                ", description='" + description + '\'' +
                ", imgSource='" + imgSource + '\'' +
                ", price=" + price +
                ", thumbnail='" + thumbnail + '\'' +
                ", status=" + status +
                ", discount=" + discount +
                ", subCategory=" + subCategory +
                '}';
    }
}
