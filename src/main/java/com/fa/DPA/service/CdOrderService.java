package com.fa.DPA.service;

import com.fa.DPA.model.CdOrder;
import com.fa.DPA.repos.CdOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CdOrderService {

    private CdOrderRepository cdOrderRepository;

    @Autowired
    public CdOrderService(CdOrderRepository cdOrderRepository) {
        this.cdOrderRepository = cdOrderRepository;
    }

    public CdOrder save(CdOrder cdOrder) {
        try {
            return cdOrderRepository.save(cdOrder);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
