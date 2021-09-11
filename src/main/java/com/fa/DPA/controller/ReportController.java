package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.converter.Revenue;
import com.fa.DPA.dto.RevenueDTO;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fa.DPA.converter.OrderOverview;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/report")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    public List<RevenueDTO> convertRevenue(List<Revenue> result){
        List<RevenueDTO> revenueDTOS = new ArrayList<>();
        for (int i = 1; i < 13; ++i) {
            revenueDTOS.add(new RevenueDTO(i, "0"));
            for (int j = 0; j < result.size(); ++j) {
                if (i == result.get(j).getMonth_finish()) {
                    revenueDTOS.set(i - 1, new RevenueDTO(result.get(j)));
                    break;
                }
            }

        }

        return revenueDTOS;
    }


    /**
     *
     * @param raw_year
     * @return
     */
    @GetMapping("/revenue-year/{year}")
    public ResponseEntity<Object> getSumOfRevenue(@PathVariable(value = "year") String raw_year){
        int year = 0;

        if (raw_year == null || raw_year.trim().length() == 0) {
            year = LocalDate.now().getYear();
        } else {
            year = Constant.checkYear(raw_year);
            if (year == -1) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }


        List<Revenue> result;
        result = reportService.getSumRevenueByYear(year);



        if (result == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<RevenueDTO> revenueDTOS = convertRevenue(result);
        return new ResponseEntity<>(revenueDTOS, HttpStatus.OK);
    }

    int currentYear = LocalDate.now().getYear();
    int currentMonth = LocalDate.now().getMonth().getValue();



    /**
     *
     * @return
     */
    @GetMapping("/revenue-month")
    public ResponseEntity<Map<String, String>> getSumOfRevenueByCurrentMonth() {

        BigDecimal raw_result = reportService.getSumRevenueByCurrentMonth(currentYear, currentMonth);
        if (raw_result == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String res = raw_result.toPlainString();
        Map<String, String> resMap = new HashMap<>();
        resMap.put("revenue", res);
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }




    /**
     *
     * @param raw_year
     * @param raw_month
     * @return
     */
    @GetMapping("/staff-freq")
    public ResponseEntity<Object> getStaffFreq(@RequestParam("year") String raw_year,
                                               @RequestParam(value = "month", required = false)  String raw_month) {
        int year = Constant.checkYear(raw_year);
        int month = Constant.checkMonth(raw_month);

        if (year == -1 || month == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List result;
        result = reportService.getStaffFreq(year, month);

        if (result == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     *
     * @param
     * @return
     */
    @GetMapping("/number-order/all-status")
    public ResponseEntity<Object> getNumberOfCancelOrderInMonth() {
        OrderOverview overview = reportService.getOrderOverview(currentYear);
        if (overview == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(overview, HttpStatus.OK);

    }

    /**
     *
     * @return
     */
    @GetMapping("/number-order/view-all")
    public ResponseEntity<Map<String, Long>> countAlLOrderInCurrentYear(){
        Long numberOfOrderInYear = reportService.getALlOrderByYear(currentYear);
        if(numberOfOrderInYear == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, Long> resMap = new HashMap<>();
        resMap.put("numberOfOrder", numberOfOrderInYear);
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }


    /**
     *
     * @return
     */
    @GetMapping("/number-order/all-month")
    public ResponseEntity<Map<String, Long>> countAllNumberOfOrderInCurrentMonth() {
        Long res = reportService.countTotalOrderInMonth(currentYear, currentMonth);
        if (res == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, Long> resMap = new HashMap<>();
        resMap.put("numberOfOrder", res);
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }


    @GetMapping("/number-product/construction-drawing")
    public ResponseEntity<ConstructionDrawing> getNumberOfConstructionDrawing(){
        return null;
    }

    /**
     *
     * @param raw_year
     * @param raw_id
     * @return
     */
    @GetMapping("/staff/revenue-month")
    public ResponseEntity<Object> getRevenueByStaff(@RequestParam(value = "year", required = false) String raw_year,
                                                    @RequestParam("sale_id") String raw_id) {

        int year = 0;

        if (raw_year == null || raw_year.trim().length() == 0) {
            year = LocalDate.now().getYear();
        } else {
            year = Constant.checkYear(raw_year);
            if (year == -1) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        long id = Constant.checkId(raw_id);
        if(id == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<Revenue> result;

        result = reportService.getSumRevenueByYearWithStaff(year, id);

        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<RevenueDTO> revenueDTOS = convertRevenue(result);
        return new ResponseEntity<>(revenueDTOS, HttpStatus.OK);

    }

}
