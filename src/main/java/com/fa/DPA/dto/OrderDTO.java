package com.fa.DPA.dto;

import com.fa.DPA.model.CdOrder;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Data
public class OrderDTO extends AbstractDTO{


    @Data
    @AllArgsConstructor
    public class ProductOrderDTO{
        private long id;
        private String name;
        private Float areaSize;
        private String thumbnail;
        private String price;
        private int amountDiscount;

    }

    private String orderCode;
    private List<ProductOrderDTO> productName = new ArrayList<>();
    private String totalPrice;
    private String urlFile;
    private String note;
    private LocalDate createDate;
    private Date finishedDate;
    private Long ownerId;
    private String addressReceive;
    private String phoneReceive;
    private Long idSale;
    private String nameSale;
    private long status;
    private String ownerName;
    private String ownerEmail;




    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderCode = order.getOrderCode();


//        for (int i = 0; i < order.getConstructionDrawings().size(); i++) {
//            productName.put(order.getConstructionDrawings().get(i).getId(),
//                    order.getConstructionDrawings().get(i).getName());
//        }

//        this.totalPrice = order.getTotalPrice();
        this.urlFile = order.getUrlFile();
        this.createDate = order.getCreatedDate();
        this.finishedDate = order.getFinishedDate();
        this.status = order.getStatus().getId();
        this.ownerId = order.getOwner().getId();
        this.ownerName = order.getOwner().getAccount_name();
        this.note = order.getNote();
        this.ownerEmail = order.getOwner().getEmail();
        this.addressReceive = order.getAddressReceive() == null ? order.getOwner().getAddress() : order.getAddressReceive();
        this.phoneReceive = order.getPhoneReceive() == null ? order.getOwner().getPhone() : order.getPhoneReceive();
        this.totalPrice = order.getTotalPrice()==null ? null : BigDecimal.valueOf(order.getTotalPrice()).toPlainString();
        this.idSale = order.getStaffAccount() == null ? null : order.getStaffAccount().getId();
        if(this.idSale != null){
            this.nameSale = order.getStaffAccount().getStaff_name();
        }
    }

    public void setListProduct(CdOrder cdOrders){
        ConstructionDrawing constructionDrawing = cdOrders.getConstructionDrawing();
        ProductOrderDTO productOrderDTO = new ProductOrderDTO(constructionDrawing.getId(), cdOrders.getCurrentNameOfConstructionDrawing(),
                cdOrders.getAreaSize(), constructionDrawing.getThumbnail(),
                BigDecimal.valueOf(cdOrders.getCurrentPrice()).toPlainString(),
                cdOrders.getCurrentDiscount() == null ? 0 : cdOrders.getCurrentDiscount());
        this.productName.add(productOrderDTO);
    }
}
