package com.fa.DPA.controller.output;

import com.fa.DPA.dto.InteriorDesignDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InteriorOutput {
    private int page;
    private int totalPage;
    private int totalItems;
    private List<InteriorDesignDTO> listResut = new ArrayList<>();

}
