package com.fa.DPA.service;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.Status;
import com.fa.DPA.repos.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;
    private ContactService service;

    @BeforeEach
    void setUp(){
        service = new ContactService(contactRepository);
    }

    @Test
    void canGetAllContactPaging() {
        Pageable pageable = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        service.getAllContactPaging(pageable);
        verify(contactRepository).findAll(pageable);
    }

    @Test
    void findById() {
        service.findById((long) 1);
        verify(contactRepository).findById((long) 1);
    }

    @Test
    @Disabled
    void softDelete() {


    }

    @Test
    void saveContact() {
        //give
        CustomerContact contact = new CustomerContact("Nguyễn Văn Đạt", "01212121", "12",
                "Phong cách châu âu", "Hà Nội", new Status("Pending"));
        //when
        service.saveContact(contact);

        //then
        ArgumentCaptor<CustomerContact> customerContactArgumentCaptor =
                ArgumentCaptor.forClass(CustomerContact.class);
        verify(contactRepository).save(customerContactArgumentCaptor.capture());

        CustomerContact captureContact = customerContactArgumentCaptor.getValue();
        assertThat(captureContact).isEqualTo(contact);

    }
}