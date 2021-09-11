package com.fa.DPA.ConstructDrawingService;

import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Discount;
import com.fa.DPA.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class ConstructDrawingOutput {
    private Page<ConstructionDrawing> getAllPage_UTC01;
    private Page<ConstructionDrawing> getAllPage_UTC02;
    private ConstructionDrawing save_UTC01;
    private ConstructionDrawing save_UTC02;
    private ConstructionDrawing save_UTC03;

    public ConstructDrawingOutput() {
        List<ConstructionDrawing> constructionDrawingList = new ArrayList<>();
        getAllPage_UTC01 = new PageImpl<>(constructionDrawingList);

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

        save_UTC03 = null;
    }

    public Page<ConstructionDrawing> getGetAllPage_UTC01() {
        return getAllPage_UTC01;
    }

    public Page<ConstructionDrawing> getGetAllPage_UTC02() {
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
}
