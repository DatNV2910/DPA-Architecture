package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository underTest;

    @Test
    void findAll() {
        CustomerContact contact = new CustomerContact("Nguyễn Văn Đạt", "01212121", "12",
                "Phong cách châu âu", "Hà Nội", new Status("Pending"));

        underTest.save(contact);



    }
}