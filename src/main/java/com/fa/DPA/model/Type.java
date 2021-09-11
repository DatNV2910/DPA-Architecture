package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
@Data
public class Type extends AbstractModel{
    @Column
    private String type_name;

//    @OneToMany(mappedBy = "type")
//    private List<Category> categories = new ArrayList<>();
}
