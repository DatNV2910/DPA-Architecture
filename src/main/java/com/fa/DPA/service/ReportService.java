package com.fa.DPA.service;

import com.fa.DPA.converter.OrderOverview;
import com.fa.DPA.converter.Revenue;
import com.fa.DPA.converter.StaffFreq;
import com.fa.DPA.repos.OrderRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
    private OrderRepository orderRepository;
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    public ReportService(OrderRepository orderRepository, StaffAccountRepository staffAccountRepository) {
        this.orderRepository = orderRepository;
        this.staffAccountRepository = staffAccountRepository;
    }

    public List<Revenue> getSumRevenueByYear(int year){
        try{
            return orderRepository.getSumRevenueForEachYear(year);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;

    }
    /**
     *
     * @param currentYear
     * @param currentMonth
     * @return
     */
    public BigDecimal getSumRevenueByCurrentMonth(int currentYear, int currentMonth) {
        try{
            return orderRepository.countRevenueByCurrentMonth(currentYear, currentMonth);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param currentYear
     * @param currentMonth
     * @return
     */
    public Long countTotalOrderInMonth(int currentYear, int currentMonth) {
        try{
            return orderRepository.countOrderInCurrentMonth(currentYear, currentMonth);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param year
     * @param month
     * @return
     */
    public List<StaffFreq> getStaffFreq(int year, int month){
        try{
            if(month == 0){
                return staffAccountRepository.findStaffFreqOnlyYear(year);
            }else if(month > 0){
                return staffAccountRepository.findStaffFreqYearAndMonth(year, month);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param month
     * @return
     */
    public int getNumberOfCancelOrderByMonth(int month){
        try{
            return orderRepository.countNumberCancelOrder(LocalDate.now().getYear(), month);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return -1;
    }
    /**
     *
     * @param currentYear
     * @return
     */
    public OrderOverview getOrderOverview(int currentYear){
        try{
            return orderRepository.getOrderOverviewByYear(currentYear);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
    /**
     *
     * @param currentYear
     * @return
     */
    public Long getALlOrderByYear(int currentYear){
        try{
            return orderRepository.countAllByYear(currentYear);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Revenue> getSumRevenueByYearWithStaff(int year, long id){
        try{
            return orderRepository.getSumRevenueForEachYearByStaff(year, id);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
