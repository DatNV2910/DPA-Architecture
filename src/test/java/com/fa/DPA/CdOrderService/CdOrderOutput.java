package com.fa.DPA.CdOrderService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.*;

public class CdOrderOutput {
    private CdOrder save_UTC01;
    private CdOrder save_UTC02;
    private CdOrder save_UTC03;
    private CdOrder save_UTC04;
    private CdOrder save_UTC05;
    private CdOrder save_UTC06;
    private CdOrder save_UTC07;
    private CdOrder save_UTC08;

    public CdOrderOutput() {
        ConstructionDrawing test_cd = new ConstructionDrawing();
        test_cd.setId((long) 1);
        test_cd.setStatus(true);
        test_cd.setCode("code");
        test_cd.setDescription("description of this cd");
        test_cd.setDiscount(new Discount());
        test_cd.setImgSource("img-source");
        test_cd.setName("test cd");
        test_cd.setNumberFloor(3);
        test_cd.setPrice(250000);
        test_cd.setSubCategory(new SubCategory());
        test_cd.setThumbnail("thumbnail");

        Order test_order = new Order();
        test_order.setOrderCode("test code");
        test_order.setAddressReceive("Street XX, city YY, Vietnam");
        test_order.setPhoneReceive("0123456789");
        CustomerAccount test_acc = new CustomerAccount();
        test_acc.setId((long) 1);
        test_order.setOwner(test_acc);
        Status status = new Status();
        status.setId(Constant.ID_PENDING);
        test_order.setStatus(status);

        save_UTC01 = new CdOrder();
        save_UTC01.setConstructionDrawing(test_cd);
        save_UTC01.setOrder(test_order);
        save_UTC01.setCurrentNameOfConstructionDrawing("Current construct drawing name");
        save_UTC01.setCurrentDiscount(20);
        save_UTC01.setCurrentPrice(300000);
        save_UTC01.setAreaSize((float) 100);

        save_UTC02 = null;
        save_UTC03 = null;
        save_UTC04 = null;
        save_UTC05 = null;
        save_UTC06 = null;
        save_UTC07 = null;
        save_UTC08 = null;
    }

    public CdOrder getSave_UTC01() {
        return save_UTC01;
    }

    public CdOrder getSave_UTC02() {
        return save_UTC02;
    }

    public CdOrder getSave_UTC03() {
        return save_UTC03;
    }

    public CdOrder getSave_UTC04() {
        return save_UTC04;
    }

    public CdOrder getSave_UTC05() {
        return save_UTC05;
    }

    public CdOrder getSave_UTC06() {
        return save_UTC06;
    }

    public CdOrder getSave_UTC07() {
        return save_UTC07;
    }

    public CdOrder getSave_UTC08() {
        return save_UTC08;
    }
}
