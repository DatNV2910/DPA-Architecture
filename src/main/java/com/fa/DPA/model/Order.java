package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners({AuditingEntityListener.class})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractModel {


//    @ManyToOne
//    @JoinColumn(name ="customer_id")
//    private CustomerAccount customerAccount;

//    @ManyToMany
//    @JoinTable(name ="cd_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "cd_id"))
//    private List<ConstructionDrawing> constructionDrawings;

    //    @OneToMany(mappedBy = "primaryKey.order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //    private List<CdOrder> cd_orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CustomerAccount owner;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffAccount staffAccount;

    @Column
    private String orderCode;

    @Column
    private String note;


    @Column
    @CreatedDate
    private LocalDate createdDate;

    @Column
    private Date confirmedDate;

    @Column
    private Date finishedDate;

    @Column
    private Float totalPrice;

    @Column(length = 30)
    private String phoneReceive;

    @Column(length = 50)
    private String addressReceive;

    @Column(columnDefinition = "TEXT")
    private String urlFile;

    @Override
    public String toString() {
        return "Order{" +
                "id = " + getId() +
                ", status=" + status +
                ", owner=" + owner +
                ", staffAccount=" + staffAccount +
                ", orderCode='" + orderCode + '\'' +
                ", note='" + note + '\'' +
                ", createdDate=" + createdDate +
                ", confirmedDate=" + confirmedDate +
                ", finishedDate=" + finishedDate +
                ", totalPrice='" + totalPrice + '\'' +
                ", phoneReceive='" + phoneReceive + '\'' +
                ", addressReceive='" + addressReceive + '\'' +
                '}';
    }
}
