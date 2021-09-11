package com.fa.DPA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory extends AbstractModel {

    @Column
    private String name;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ManyToMany(mappedBy = "subCategories", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<InteriorDesign> interiorDesigns = new ArrayList<>();

   /* @OneToMany(

            mappedBy = "subCategory"
    )
    private List<ConstructionDrawing> constructionDrawings = new ArrayList<>();*/

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", category=" + category +
                '}';
    }

//    @ManyToMany(mappedBy = "subCategories")
//    private List<InteriorDesign> interiorDesigns = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "subCategory"
//    )
//    private List<ConstructionDrawing> constructionDrawings = new ArrayList<>();
}
