package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "cd_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@AssociationOverrides({
//        @AssociationOverride(name = "primaryKey.order", joinColumns = @JoinColumn(name = "order_id")),
//        @AssociationOverride(name = "primaryKey.constructionDrawing", joinColumns = @JoinColumn(name = "cd_id"))}
//)
public class CdOrder extends AbstractModel {
    private Float areaSize;

//    private CdOrderID primaryKey = new CdOrderID();
//
//    @EmbeddedId
//    public CdOrderID getPrimaryKey() {
//        return primaryKey;
//    }
//
//    @Transient
//    public ConstructionDrawing getConstructionDrawing(){
//        return primaryKey.getConstructionDrawing();
//    }
//
//
//    @Transient
//    public Order getOrder(){
//        return primaryKey.getOrder();
//    }


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cd_id")
    private ConstructionDrawing constructionDrawing;

    @Column(nullable = false)
    private String currentNameOfConstructionDrawing;

    @Column(nullable = false)
    private float currentPrice;

    @Column
    private Integer currentDiscount;

    @Override
    public String toString() {
        return "CdOrder{id=" + getId() +
                "areaSize=" + areaSize +
                ", order=" + order +
                ", constructionDrawing=" + constructionDrawing +
                '}';
    }
}
