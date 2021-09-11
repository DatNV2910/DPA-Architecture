package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.CheckOutFormDTO;
import com.fa.DPA.dto.OrderDTO;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.*;
import com.fa.DPA.service.*;
import com.fa.DPA.service.CustomerLoginService.CustomerDetailImpl;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;
    private StaffAccountService staffAccountService;
    private CustomerAccountService customerAccountService;
    private CdOrderService cdOrderService;
    private CartItemService cartItemService;
    private ConstructionDrawingService constructionDrawingService;

    @Autowired
    public OrderController(OrderService orderService,
                           StaffAccountService staffAccountService,
                           CustomerAccountService customerAccountService,
                           CdOrderService cdOrderService,
                           CartItemService cartItemService,
                           ConstructionDrawingService constructionDrawingService) {
        this.orderService = orderService;
        this.staffAccountService = staffAccountService;
        this.customerAccountService = customerAccountService;
        this.cdOrderService = cdOrderService;
        this.cartItemService = cartItemService;
        this.constructionDrawingService = constructionDrawingService;
    }

//
//    /**
//     * @return
//     */
//    public StaffAccount getStaff() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        StaffAccount staff = null;
//        if (principal instanceof StaffDetailImpl) {
//            String username = ((StaffDetailImpl) principal).getUsername();
//            try {
//                staff = staffAccountService.findStaffAccountByUsername(username);
//            } catch (EntityNotFoundException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//        return staff;
//    }
//
//    /**
//     * @return
//     */
//    public CustomerAccount getCustomer() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        CustomerAccount customer = null;
//        if (principal instanceof CustomerDetailImpl) {
//            String username = ((CustomerDetailImpl) principal).getUsername();
//            try {
//                customer = customerAccountService.findStaffAccountByUsername(username);
//            } catch (EntityNotFoundException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//        return customer;
//    }


    CustomerAccount customerAccount;
    StaffAccount staffAccount;


    /**
     * @param page
     * @param type
     * @return
     */
    public ResponseEntity<Map<String, Object>> process(int page, int type, long id, String filterName, Long filterStatusId) {
        Map<String, Object> response = new HashMap<>();
        List<Order> orderList;
        List<CdOrder> cdOrderList= null;
        List<OrderDTO> orderDTOS = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Order> orderPage = null;
//            Page<CdOrder> cdOrderPage = null;
            switch (type) {
                case 0:
                    int typeAll = -1;
                    if(filterName.trim().length() != 0 && filterStatusId == null){
                        typeAll = 0;
                    } else if(filterStatusId != null && filterName.trim().length() == 0) {
                        typeAll = 1;
                    }else if(filterName.trim().length() != 0 && filterStatusId != null){
                        typeAll = 2;
                    }

                    if(typeAll == -1){
                        orderPage = orderService.getAll(pageable);
                    }else{
                        orderPage = orderService.getAllByCondition(pageable, typeAll, filterName, filterStatusId);
                    }
                    break;
                case 1:
                    cdOrderList = orderService.getAllOrderProcessPage(id);
                    break;
                case 2:
                    cdOrderList = orderService.getAllOrderHistory(id);
                    break;
                case 3:
                    orderPage = orderService.getAllWaitOrder(pageable, id);
                    break;
            }

            if (cdOrderList  != null) {
//                cdOrderList = cdOrderPage.getContent();
                long idOrder = -1;
                OrderDTO orderDTO = null;
                for (int i = 0; i < cdOrderList.size(); ++i) {
                    long currentId = cdOrderList.get(i).getOrder().getId();
                    if (idOrder != currentId) {
                        idOrder = currentId;
                        orderDTO = new OrderDTO(cdOrderList.get(i).getOrder());
                        orderDTOS.add(orderDTO);
                    }
                    if(idOrder == currentId){
                        orderDTO.setListProduct(cdOrderList.get(i));
                    }
                }

                int totalPage = orderDTOS.size() % Constant.DEFAULT_PAGE_SIZE == 0 ?
                        orderDTOS.size() / Constant.DEFAULT_PAGE_SIZE : (orderDTOS.size() / Constant.DEFAULT_PAGE_SIZE) + 1;

                List<OrderDTO> orderDTOPaging = null;

                if(page < totalPage){
                    int endIndex = page * Constant.DEFAULT_PAGE_SIZE + 10 < orderDTOS.size() - 1 ? page * Constant.DEFAULT_PAGE_SIZE + 10 : orderDTOS.size();
                    System.out.println(endIndex);
                    int startIndex = page * Constant.DEFAULT_PAGE_SIZE;
                    orderDTOPaging = orderDTOS.subList(startIndex, endIndex);
                    System.out.println("EndIndex: " +endIndex);
                    System.out.println("StartIndex: "+startIndex);

                }

                response.put("currentPage", page);
                System.out.println("Page" + page);
                response.put("totalPage", totalPage);
                response.put("totalItem", orderDTOS.size());
                response.put("totalDBItem", cdOrderList.size());
                response.put("orderList", orderDTOPaging);
                response.put("currentItems", orderDTOPaging == null ? 0 : orderDTOPaging.size());
            }


            if (orderPage != null) {

                orderList = orderPage.getContent();

                for (Order o : orderList) {
                    System.out.println(o);
                    OrderDTO orderDTO = new OrderDTO(o);
                    orderDTOS.add(orderDTO);
                }

                response.put("currentPage", orderPage.getNumber());
                response.put("totalPage", orderPage.getTotalPages());
                response.put("totalItem", orderDTOS.size());
                response.put("totalDatabaseItem", orderPage.getTotalElements());
                response.put("orderList", orderDTOS);
            }



        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_CUSTOMERCARE')")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page,
            @RequestParam(name = "staff-name",defaultValue = "") String staffName,
            @RequestParam(name = "status", defaultValue = "") String raw_idStatus)  {
        int page = Constant.checkPage(raw_page);
        Long idStatus ;
        if(raw_idStatus == null || raw_idStatus.trim().length() == 0){
            idStatus = null;
        }else{
            idStatus = Constant.checkId(raw_idStatus);
        }

        if (page == -1 || (idStatus != null && idStatus == -1)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return process(page, 0, -1, staffName, idStatus);
    }


    /**
     * @param raw_page
     * @return
     */
    @GetMapping("/queue")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Map<String, Object>> getOrderQueue(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        customerAccount = Constant.getCustomer(customerAccountService);
        if (customerAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Long idCustomer = customerAccount.getId();
        int q_page = Constant.checkPage(raw_page);
        if (q_page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return process(q_page, 1, idCustomer, null, null);
    }
    /**
     *
     * @param raw_id
     * @param note
     * @return
     */
    @PutMapping("/update-note/{idOrder}")
    public ResponseEntity<Object> updateNote(@PathVariable("idOrder") String raw_id,
                                             @RequestParam("note") String note) {

        long idOrder = Constant.checkId(raw_id);

        if (idOrder == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        staffAccount = Constant.getStaff(staffAccountService);
        if (staffAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }


        Long id = staffAccount.getId();

        Order o = orderService.findOrderById(idOrder);

        if (o.getStaffAccount().getId() != id) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        note = note.concat("<br>");

        if (o.getNote() == null || o.getNote().trim().length() == 0) {
            o.setNote(note);
        } else {
            String oldContent = o.getNote();
            String newContent = oldContent.concat(note);
            o.setNote(newContent);
        }

        Order orderReturn = orderService.save(o);
        if (orderReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Update Note successful", HttpStatus.OK);
    }

    /**
     * @param raw_id
     * @return
     */
    @DeleteMapping("/cancel/{id}")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Object> cancelOrder(@PathVariable("id") String raw_id) {
        if (Constant.getCustomer(customerAccountService) == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Long id = Constant.checkId(raw_id);
        if (id == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            orderService.softDelete(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setLocation(new URI("api/order/history"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     * @param raw_id
     * @return
     */
    @GetMapping("/detail/{id}")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SALE')")
    public ResponseEntity<OrderDTO> getDetail(@PathVariable("id") String raw_id) {
        customerAccount = Constant.getCustomer(customerAccountService);

        staffAccount = Constant.getStaff(staffAccountService);

        if (customerAccount == null && staffAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Long id = Constant.checkId(raw_id);
        if (id == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Order order;
        try {
            order = orderService.findOrderById(id);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(customerAccount != null && order.getOwner().getId() != customerAccount.getId()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        OrderDTO orderDTO = new OrderDTO(order);

        List<CdOrder> cdOrders;

        cdOrders = orderService.getAllByOrderId(id);
        if (cdOrders == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        for (CdOrder cdOrder : cdOrders) {
            orderDTO.setListProduct(cdOrder);
        }


        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/history")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Map<String, Object>> viewHistory(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {

        customerAccount = Constant.getCustomer(customerAccountService);

        if (customerAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        long id = customerAccount.getId();

        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return process(page, 2, id, null, null);
    }


    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * @param raw_id
     * @param idStatus
     * @return
     */
    @PutMapping("/change-status/{id}")
//    @PreAuthorize("hasRole('Role_SALE')")
    public ResponseEntity<Object> changeOrderStatus(@PathVariable("id") String raw_id,
                                                    @RequestParam("status") Long idStatus) {
        Long id = Constant.checkId(raw_id);
        if (id == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        staffAccount = Constant.getStaff(staffAccountService);

        if (staffAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        long idStaff = staffAccount.getId();

        Order order;
        try {
            order = orderService.findOrderById(id);
            if (order.getStaffAccount().getId() != idStaff) {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (EntityNotFoundException | NullPointerException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Status status = new Status();
        status.setId(idStatus);
        order.setStatus(status);
        if(idStatus==1){
            order.setFinishedDate(Date.valueOf(LocalDate.now()));
        }
        Order orderReturn = orderService.save(order);
        if (orderReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/api/order/all"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     * @param page
     * @return
     */
    @GetMapping("/all-wait-confirm")
//    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Map<String, Object>> getAlWaitConfirmOrder(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) int page) {
        if (Constant.getCustomer(customerAccountService) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return process(page, 3, -1, null, null);
    }


    /**
     * @param idOrder
     * @return
     */
    @PostMapping("/confirm")
//    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> confirmOrder(@RequestParam("idOrder") Long idOrder) {

        staffAccount = Constant.getStaff(staffAccountService);

        if (staffAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        long idStaff = staffAccount.getId();

        if (orderService.checkExistByID(idOrder) == 1 && staffAccountService.checkExistByID(idStaff) == 1) {
            Order order = orderService.findOrderById(idOrder);
            int countProcess = orderService.countOrderProcessingWithStaff(idStaff);
            System.out.println("Number of count processing: " + countProcess);
            if (countProcess == -1) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                if (countProcess >= Constant.LIMIT_ORDER_PROCESS) {
                    return new ResponseEntity<>(Constant.LIMIT_ORDER_PROCESS_MESSAGE, HttpStatus.BAD_REQUEST);
                }

                LocalDate confirmDate = LocalDate.now();
                order.setConfirmedDate(Date.valueOf(confirmDate));
                StaffAccount staffAccount = new StaffAccount();
                staffAccount.setId(idStaff);
                order.setStaffAccount(staffAccount);
                Status status = new Status();
                status.setId(Constant.ID_PROCESS);
                order.setStatus(status);

                try {
                    orderService.save(order);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }


                try {
                    httpHeaders.setLocation(new URI("/api/order/all-wait-confirm"));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /**
     * @param raw_id
     * @return
     */
    @PostMapping("/transfer")
//    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> transferOrder(@RequestParam("idOrder") String raw_id) {

        staffAccount = Constant.getStaff(staffAccountService);

        if (staffAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        long idStaff = staffAccount.getId();
        long id = Constant.checkId(raw_id);
        if (id == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Order order;
        try {
            order = orderService.findOrderById(id);
            if (order.getStaffAccount().getId() != idStaff) {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Status status = new Status();
        status.setId(Constant.ID_PENDING);
        order.setStatus(status);
        order.setStaffAccount(null);
        order.setConfirmedDate(null);
        Order returnOrder = orderService.save(order);
        if (returnOrder == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(returnOrder, HttpStatus.OK);
    }

    /**
     *
     * @param checkOutFormDTO
     * @param raw_userid
     * @return
     */
    @PostMapping("/checkout/{userid}")
    //    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> createOrder(@RequestBody CheckOutFormDTO checkOutFormDTO,
//                                             @RequestParam("area_size") String[] area_size,
                                             @PathVariable("userid") String raw_userid) {
        long userid = Constant.checkId(raw_userid);
        if(userid == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        customerAccount = Constant.getCustomer(customerAccountService);
        if(customerAccount==null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        if(checkOutFormDTO.getPhone().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần nhập số điện thoại"),HttpStatus.BAD_REQUEST);
        }
        if(checkOutFormDTO.getAddress().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần nhập địa chỉ"),HttpStatus.BAD_REQUEST);
        }
        CustomerAccount owner = new CustomerAccount();
        owner.setId(userid);
        Status status = new Status();
        status.setId(Constant.ID_PENDING);
        int pos = 0;
        Float totalprice = 0.0f;
        long removeItem[] = new long[checkOutFormDTO.getCartItemIds().size()];

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String code = userid + dtf.format(now);

        try {
            Order newOrder = new Order();
            newOrder.setPhoneReceive(checkOutFormDTO.getPhone());
            newOrder.setAddressReceive(checkOutFormDTO.getAddress());
            newOrder.setOwner(owner);
            newOrder.setStatus(status);
            newOrder.setOrderCode(code);
            Order returnOrder = orderService.save(newOrder);
            if (returnOrder == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            returnOrder = orderService.findOrderByOwnerId(userid);

            for (Long item : checkOutFormDTO.getCartItemIds()) {
                CdOrder cdOrder = new CdOrder();
                cdOrder.setAreaSize(Float.parseFloat(checkOutFormDTO.getArea_size()[pos]));
                ConstructionDrawing cd = constructionDrawingService.findById(cartItemService.findById(item)
                        .getConstructionDrawing().getId());
                cdOrder.setConstructionDrawing(cd);
                cdOrder.setOrder(returnOrder);
                cdOrder.setCurrentPrice(cd.getPrice());
                if(cd.getDiscount() != null) {
                    cdOrder.setCurrentDiscount(cd.getDiscount().getAmount());
                }
                cdOrder.setCurrentNameOfConstructionDrawing(cd.getName());
                CdOrder returnCdOrder = cdOrderService.save(cdOrder);
                if (returnCdOrder == null) {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (cd.getDiscount() == null) {
                    totalprice += Float.parseFloat(checkOutFormDTO.getArea_size()[pos]) * cd.getPrice();
                } else {
                    totalprice += Float.parseFloat(checkOutFormDTO.getArea_size()[pos]) * cd.getPrice() * (100 - cd.getDiscount().getAmount()) / 100;
                }
                removeItem[pos] = item;
                pos++;
            }

            returnOrder.setTotalPrice(totalprice);
            returnOrder = orderService.save(returnOrder);
            cartItemService.removeItem(removeItem);
            if (returnOrder == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
//            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
