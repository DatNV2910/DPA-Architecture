package com.fa.DPA.service;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CdOrder;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.model.Status;
import com.fa.DPA.repos.CdOrderRepository;
import com.fa.DPA.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CdOrderRepository cdOrderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CdOrderRepository cdOrderRepository) {
        this.orderRepository = orderRepository;
        this.cdOrderRepository = cdOrderRepository;
    }


    /**
     *
     * @param pageable
     * @return
     */
    public Page<CdOrder> getAllOrderProcessPage(Pageable pageable, long id){
        try {
            long[] l = new long[]{(long)3, (long)4};
            return cdOrderRepository.findAllCustomize(pageable,id ,l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param pageable
     * @return
     */
    public Page<CdOrder> getAllOrderHistory(Pageable pageable, long id){
        try {
            long[] l = new long[]{(long)1, (long)2};
            return cdOrderRepository.findAllCustomize(pageable, id, l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param id
     * @return
     */
    public Order findOrderById(Long id){
        return orderRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Order with this id: " + id + " is not found"));
    }


    /**
     *
     * @param id
     * @throws EntityNotFoundException
     */
    public void softDelete(Long id) throws EntityNotFoundException{
        Order order = this.findOrderById(id);
        System.out.println(order.toString());
        if (order.getStatus().getStatus().equals(Constant.PENDING)) {
            Status status = new Status();
            status.setId((long) 2);
            order.setStatus(status);
            orderRepository.save(order);
        }
    }


    public Page<Order> getAllByCondition(Pageable pageable, int type,
                                         String staffAccountName, Long statusId){
        try{
            switch(type){
                case 0:
                    return orderRepository.findAllWithStaffName(staffAccountName, pageable);
                case 1:
                    Status s = new Status();
                    s.setId(statusId);
                    return orderRepository.findAllByStatus(s, pageable);
                case 2:
                    return orderRepository.findAllWithStaffNameAndStatus(staffAccountName, statusId, pageable);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAll(Pageable pageable) {
        try {
            return orderRepository.findAllPage(pageable);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param order
     * @return
     */
    public Order save(Order order){
        try{
           return orderRepository.save(order);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Order> getAllWaitOrder(Pageable pageable, long id) {
        try{
            return orderRepository.findAllWait(pageable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param staffId
     * @return
     */
    public int countOrderProcessingWithStaff(Long staffId){
        StaffAccount staffAccount = new StaffAccount();
        staffAccount.setId(staffId);
        Status status = new Status();
        status.setId(Constant.ID_PROCESS);
        try{
            return orderRepository.countOrderByStaffAccountAndStatus(staffAccount, status);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     *
     * @param id
     * @return
     */
    public int checkExistByID(Long id){

        try{
            return orderRepository.existsOrderById(id) == true ? 1 : 0;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return -1;
    }


    /**
     *
     * @param cdOrder
     * @return
     */
    public CdOrder save(CdOrder cdOrder){
        try{
            return cdOrderRepository.save(cdOrder);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param id
     * @return
     */
    public List<CdOrder> getAllByOrderId(long id){
        try{
            return cdOrderRepository.findAllByOrder_Id(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Order findOrderByOwnerId(long owner_id){
        try{
            return orderRepository.findByOwnerId(owner_id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @param idStaff
     * @return
     */
    public long findNumberOfFinishingOrder(long idStaff) {
        try{
            return orderRepository.countNumberFinishOrder(idStaff);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<CdOrder> getAllOrderHistory(long id){
        try {
            long[] l = new long[]{(long)1, (long)2};
            return cdOrderRepository.findAllWithStatus(id ,l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<CdOrder> getAllOrderProcessPage(long id){
        try {
            long[] l = new long[]{(long)3, (long)4};
            return cdOrderRepository.findAllWithStatus(id ,l);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
