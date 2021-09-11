package com.fa.DPA.ConstructDrawingService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Discount;
import com.fa.DPA.model.SubCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ConstructDrawingInput {
    private Pageable getAllPage_UTC01;
    private Pageable getAllPage_UTC02;
    private ConstructionDrawing save_UTC01;
    private ConstructionDrawing save_UTC02;
    private ConstructionDrawing save_UTC03;
    private ConstructionDrawing save_UTC04;
    private ConstructionDrawing save_UTC05;
    private ConstructionDrawing save_UTC06;
    private ConstructionDrawing save_UTC07;
    private ConstructionDrawing save_UTC08;
    private ConstructionDrawing save_UTC09;
    private ConstructionDrawing save_UTC10;
    private ConstructionDrawing save_UTC11;
    private ConstructionDrawing save_UTC12;
    private ConstructionDrawing save_UTC13;

    public ConstructDrawingInput() {
        getAllPage_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);

        getAllPage_UTC02 = null;

        save_UTC01 = new ConstructionDrawing();
        save_UTC01.setStatus(true);
        save_UTC01.setCode("120210801");
        save_UTC01.setDescription("description");
        save_UTC01.setDiscount(new Discount());
        save_UTC01.setImgSource("source");
        save_UTC01.setName("Biet thu 4 tang");
        save_UTC01.setNumberFloor(4);
        save_UTC01.setPrice(250000);
        save_UTC01.setSubCategory(new SubCategory());
        save_UTC01.setThumbnail("thumbnail");

        save_UTC02 = new ConstructionDrawing();
        save_UTC02.setStatus(false);
        save_UTC02.setCode("120210801");
        save_UTC02.setDescription("description");
        save_UTC02.setDiscount(new Discount());
        save_UTC02.setImgSource("source");
        save_UTC02.setName("Biet thu 4 tang");
        save_UTC02.setNumberFloor(4);
        save_UTC02.setPrice(250000);
        save_UTC02.setSubCategory(new SubCategory());
        save_UTC02.setThumbnail("thumbnail");

        save_UTC03 = new ConstructionDrawing();
        save_UTC03.setStatus(true);
        save_UTC03.setDescription("description");
        save_UTC03.setDiscount(new Discount());
        save_UTC03.setImgSource("source");
        save_UTC03.setName("Biet thu 4 tang");
        save_UTC03.setNumberFloor(4);
        save_UTC03.setPrice(250000);
        save_UTC03.setSubCategory(new SubCategory());
        save_UTC03.setThumbnail("thumbnail");

        save_UTC04 = new ConstructionDrawing();
        save_UTC04.setStatus(true);
        save_UTC04.setCode("120210801");
        save_UTC04.setDiscount(new Discount());
        save_UTC04.setImgSource("source");
        save_UTC04.setName("Biet thu 4 tang");
        save_UTC04.setNumberFloor(4);
        save_UTC04.setPrice(250000);
        save_UTC04.setSubCategory(new SubCategory());
        save_UTC04.setThumbnail("thumbnail");

        save_UTC05 = new ConstructionDrawing();
        save_UTC05.setStatus(true);
        save_UTC05.setCode("120210801");
        save_UTC05.setDescription("description");
        save_UTC05.setImgSource("source");
        save_UTC05.setName("Biet thu 4 tang");
        save_UTC05.setNumberFloor(4);
        save_UTC05.setPrice(250000);
        save_UTC05.setSubCategory(new SubCategory());
        save_UTC05.setThumbnail("thumbnail");

        save_UTC06 = new ConstructionDrawing();
        save_UTC06.setStatus(true);
        save_UTC06.setCode("120210801");
        save_UTC06.setDescription("description");
        save_UTC06.setDiscount(new Discount());
        save_UTC06.setName("Biet thu 4 tang");
        save_UTC06.setNumberFloor(4);
        save_UTC06.setPrice(250000);
        save_UTC06.setSubCategory(new SubCategory());
        save_UTC06.setThumbnail("thumbnail");

        save_UTC07 = new ConstructionDrawing();
        save_UTC07.setStatus(true);
        save_UTC07.setCode("120210801");
        save_UTC07.setDescription("description");
        save_UTC07.setDiscount(new Discount());
        save_UTC07.setImgSource("source");
        save_UTC07.setNumberFloor(4);
        save_UTC07.setPrice(250000);
        save_UTC07.setSubCategory(new SubCategory());
        save_UTC07.setThumbnail("thumbnail");

        save_UTC08 = new ConstructionDrawing();
        save_UTC08.setStatus(true);
        save_UTC08.setCode("120210801");
        save_UTC08.setDescription("description");
        save_UTC08.setDiscount(new Discount());
        save_UTC08.setImgSource("source");
        save_UTC08.setName("Biet thu 4 tang");
        save_UTC08.setPrice(250000);
        save_UTC08.setSubCategory(new SubCategory());
        save_UTC08.setThumbnail("thumbnail");

        save_UTC09 = new ConstructionDrawing();
        save_UTC09.setStatus(true);
        save_UTC09.setCode("120210801");
        save_UTC09.setDescription("description");
        save_UTC09.setDiscount(new Discount());
        save_UTC09.setImgSource("source");
        save_UTC09.setName("Biet thu 4 tang");
        save_UTC09.setNumberFloor(4);
        save_UTC09.setSubCategory(new SubCategory());
        save_UTC09.setThumbnail("thumbnail");

        save_UTC10 = new ConstructionDrawing();
        save_UTC10.setStatus(true);
        save_UTC10.setCode("120210801");
        save_UTC10.setDescription("description");
        save_UTC10.setDiscount(new Discount());
        save_UTC10.setImgSource("source");
        save_UTC10.setName("Biet thu 4 tang");
        save_UTC10.setNumberFloor(4);
        save_UTC10.setPrice(250000);
        save_UTC10.setThumbnail("thumbnail");

        save_UTC11 = new ConstructionDrawing();
        save_UTC11.setStatus(true);
        save_UTC11.setCode("120210801");
        save_UTC11.setDescription("description");
        save_UTC11.setDiscount(new Discount());
        save_UTC11.setImgSource("source");
        save_UTC11.setName("Biet thu 4 tang");
        save_UTC11.setNumberFloor(4);
        save_UTC11.setPrice(250000);
        save_UTC11.setSubCategory(new SubCategory());

        save_UTC12 = new ConstructionDrawing();
        save_UTC12.setCode("120210801");
        save_UTC12.setDescription("description");
        save_UTC12.setDiscount(new Discount());
        save_UTC12.setImgSource("source");
        save_UTC12.setName("Biet thu 4 tang");
        save_UTC12.setNumberFloor(4);
        save_UTC12.setPrice(250000);
        save_UTC12.setSubCategory(new SubCategory());
        save_UTC12.setThumbnail("thumbnail");

        save_UTC13 = null;
    }

    public Pageable getGetAllPage_UTC01() {
        return getAllPage_UTC01;
    }

    public Pageable getGetAllPage_UTC02() {
        return getAllPage_UTC02;
    }

    public ConstructionDrawing getSave_UTC01() {
        return save_UTC01;
    }

    public ConstructionDrawing getSave_UTC02() {
        return save_UTC02;
    }

    public ConstructionDrawing getSave_UTC03() {
        return save_UTC03;
    }

    public ConstructionDrawing getSave_UTC04() {
        return save_UTC04;
    }

    public ConstructionDrawing getSave_UTC05() {
        return save_UTC05;
    }

    public ConstructionDrawing getSave_UTC06() {
        return save_UTC06;
    }

    public ConstructionDrawing getSave_UTC07() {
        return save_UTC07;
    }

    public ConstructionDrawing getSave_UTC08() {
        return save_UTC08;
    }

    public ConstructionDrawing getSave_UTC09() {
        return save_UTC09;
    }

    public ConstructionDrawing getSave_UTC10() {
        return save_UTC10;
    }

    public ConstructionDrawing getSave_UTC11() {
        return save_UTC11;
    }

    public ConstructionDrawing getSave_UTC12() {
        return save_UTC12;
    }

    public ConstructionDrawing getSave_UTC13() {
        return save_UTC13;
    }
}
