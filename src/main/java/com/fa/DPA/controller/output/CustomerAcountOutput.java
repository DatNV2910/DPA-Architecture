package com.fa.DPA.controller.output;

import com.fa.DPA.dto.CustomerAccountDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CustomerAcountOutput {
    private int page;
    private int totalPage;
    private int totalItems;
    private List<CustomerAccountDTO> listResult = new ArrayList<>();


}
