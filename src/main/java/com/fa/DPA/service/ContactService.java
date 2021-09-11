package com.fa.DPA.service;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.Status;
import com.fa.DPA.repos.ContactRepository;
import com.fa.DPA.repos.StatusRepository;
import javassist.NotFoundException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private ContactRepository contactRepository;

    private StatusRepository statusRepository;

    /**
     * @param contactRepository
     */
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    /**
     * @param pageable
     * @return
     */
    public Page<CustomerContact> getAllContactPaging(Pageable pageable) {
        try {
            return contactRepository.findAll(pageable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public CustomerContact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     * @param id
     */
    public void softDelete(Long id) throws EntityNotFoundException {
        CustomerContact customerContact = this.findById(id);
        System.out.println(customerContact.toString());
        if (!customerContact.getStatus().getStatus().equals(Constant.CANCEL)) {
            Status status = new Status();
            status.setId((long) 1);
            customerContact.setStatus(status);
            contactRepository.save(customerContact);
        }

    }
    public CustomerContact saveContact(CustomerContact contact){
        try {
            Status status = new Status();
            status.setId((long) 3);
            contact.setStatus(status);
            contactRepository.save(contact);
            return contact;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }


}
