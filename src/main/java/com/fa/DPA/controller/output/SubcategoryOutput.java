package com.fa.DPA.controller.output;


import com.fa.DPA.dto.SubcategoryDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SubcategoryOutput {
    private int page;
    private int totalPage;
    private int totalItems;
    private List<SubcategoryDTO> listResut = new ArrayList<>();

}
