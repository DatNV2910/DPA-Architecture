package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends AbstractModel{
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerAccount customerAccount;

    @ManyToOne
    @JoinColumn(name = "cd_id")
    private ConstructionDrawing constructionDrawing;
}
