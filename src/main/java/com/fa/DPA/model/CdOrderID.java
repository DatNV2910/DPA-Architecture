package com.fa.DPA.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@Setter
public class CdOrderID implements Serializable {
    private Order order;
    private ConstructionDrawing constructionDrawing;

    @ManyToOne(cascade = CascadeType.ALL)
    public Order getOrder() {
        return order;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public ConstructionDrawing getConstructionDrawing() {
        return constructionDrawing;
    }
}
