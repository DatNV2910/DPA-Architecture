package com.fa.DPA.dto;

import com.fa.DPA.converter.Revenue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
    private Integer month;
    private String revenue;

    public RevenueDTO(Revenue revenue) {
        this.month = revenue.getMonth_finish();
        this.revenue = new BigDecimal(revenue.getRevenue()).toPlainString();
    }
}
