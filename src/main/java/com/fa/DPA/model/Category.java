package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractModel {

    @Column
    private String name;
    @Column
    private boolean status;

    @ManyToOne/*(cascade = CascadeType.ALL, fetch = FetchType.LAZY)*/
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;


    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<SubCategory> subcategories = new ArrayList<>();*/

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }



//    @OneToMany(mappedBy = "category")
//    private List<SubCategory> subcategories = new ArrayList<>();

}
