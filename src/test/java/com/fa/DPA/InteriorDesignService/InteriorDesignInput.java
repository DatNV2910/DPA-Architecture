package com.fa.DPA.InteriorDesignService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class InteriorDesignInput {
    private InteriorDesign save_UTC01;
    private InteriorDesign save_UTC02;
    private InteriorDesign save_UTC03;
    private InteriorDesign save_UTC04;
    private InteriorDesign save_UTC05;
    private InteriorDesign save_UTC06;
    private InteriorDesign save_UTC07;
    private InteriorDesign save_UTC08;
    private InteriorDesign save_UTC09;
    private Pageable findAll_UTC01;
    private Pageable findAll_UTC02;
    private Long getInteriorDesignById_UTC01;
    private Long getInteriorDesignById_UTC02;
    private Long getInteriorDesignById_UTC03;

    public InteriorDesignInput() {
        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(new SubCategory());
        save_UTC01 = new InteriorDesign();
        save_UTC01.setId((long) 1);
        save_UTC01.setCreateBy("Creator");
        save_UTC01.setContent("Content of this interior design");
        save_UTC01.setDescription("Description of this interior design");
        save_UTC01.setImageSource("ImageSource");
        save_UTC01.setSubCategories(subCategoryList);
        save_UTC01.setThumbnail("Thumbnail");
        save_UTC01.setTitle("Title of this interior design");

        save_UTC02 = new InteriorDesign();
        save_UTC02.setCreateBy("Creator");
        save_UTC02.setContent("Content of this interior design");
        save_UTC02.setDescription("Description of this interior design");
        save_UTC02.setImageSource("ImageSource");
        save_UTC02.setSubCategories(subCategoryList);
        save_UTC02.setThumbnail("Thumbnail");
        save_UTC02.setTitle("Title of this interior design");

        save_UTC03 = new InteriorDesign();
        save_UTC03.setCreateBy("Creator");
        save_UTC03.setDescription("Description of this interior design");
        save_UTC03.setImageSource("ImageSource");
        save_UTC03.setSubCategories(subCategoryList);
        save_UTC03.setThumbnail("Thumbnail");
        save_UTC03.setTitle("Title of this interior design");

        save_UTC04 = new InteriorDesign();
        save_UTC04.setCreateBy("Creator");
        save_UTC04.setContent("Content of this interior design");
        save_UTC04.setImageSource("ImageSource");
        save_UTC04.setSubCategories(subCategoryList);
        save_UTC04.setThumbnail("Thumbnail");
        save_UTC04.setTitle("Title of this interior design");

        save_UTC05 = new InteriorDesign();
        save_UTC05.setCreateBy("Creator");
        save_UTC05.setContent("Content of this interior design");
        save_UTC05.setDescription("Description of this interior design");
        save_UTC05.setSubCategories(subCategoryList);
        save_UTC05.setThumbnail("Thumbnail");
        save_UTC05.setTitle("Title of this interior design");

        save_UTC06 = new InteriorDesign();
        save_UTC06.setCreateBy("Creator");
        save_UTC06.setContent("Content of this interior design");
        save_UTC06.setDescription("Description of this interior design");
        save_UTC06.setImageSource("ImageSource");
        save_UTC06.setThumbnail("Thumbnail");
        save_UTC06.setTitle("Title of this interior design");

        save_UTC07 = new InteriorDesign();
        save_UTC07.setCreateBy("Creator");
        save_UTC07.setContent("Content of this interior design");
        save_UTC07.setDescription("Description of this interior design");
        save_UTC07.setImageSource("ImageSource");
        save_UTC07.setSubCategories(subCategoryList);
        save_UTC07.setTitle("Title of this interior design");

        save_UTC08 = new InteriorDesign();
        save_UTC08.setCreateBy("Creator");
        save_UTC08.setContent("Content of this interior design");
        save_UTC08.setDescription("Description of this interior design");
        save_UTC08.setImageSource("ImageSource");
        save_UTC08.setSubCategories(subCategoryList);
        save_UTC08.setThumbnail("Thumbnail");

        save_UTC09 = null;

        findAll_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        findAll_UTC02 = null;

        getInteriorDesignById_UTC01 = (long) 1;
        getInteriorDesignById_UTC02 = (long) -1;
        getInteriorDesignById_UTC03 = null;
    }

    public InteriorDesign getSave_UTC01() {
        return save_UTC01;
    }

    public InteriorDesign getSave_UTC02() {
        return save_UTC02;
    }

    public InteriorDesign getSave_UTC03() {
        return save_UTC03;
    }

    public InteriorDesign getSave_UTC04() {
        return save_UTC04;
    }

    public InteriorDesign getSave_UTC05() {
        return save_UTC05;
    }

    public InteriorDesign getSave_UTC06() {
        return save_UTC06;
    }

    public InteriorDesign getSave_UTC07() {
        return save_UTC07;
    }

    public InteriorDesign getSave_UTC08() {
        return save_UTC08;
    }

    public InteriorDesign getSave_UTC09() {
        return save_UTC09;
    }

    public Pageable getFindAll_UTC01() {
        return findAll_UTC01;
    }

    public Pageable getFindAll_UTC02() {
        return findAll_UTC02;
    }

    public Long getGetInteriorDesignById_UTC01() {
        return getInteriorDesignById_UTC01;
    }

    public Long getGetInteriorDesignById_UTC02() {
        return getInteriorDesignById_UTC02;
    }

    public Long getGetInteriorDesignById_UTC03() {
        return getInteriorDesignById_UTC03;
    }
}
