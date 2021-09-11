package com.fa.DPA.InteriorDesignService;

import com.fa.DPA.converter.InteriorDesignConverter;
import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class InteriorDesignOutput {
    private InteriorDesignConverter converter = new InteriorDesignConverter();
    private InteriorDesign save_UTC01;
    private InteriorDesign save_UTC02;
    private InteriorDesign save_UTC03;
    private List<InteriorDesignDTO> findALl_UTC01;
    private List<InteriorDesignDTO> findALl_UTC02;
    private InteriorDesignDTO getInteriorDesignById_UTC01;
    private InteriorDesignDTO getInteriorDesignById_UTC02;

    public InteriorDesignOutput() {
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

        save_UTC03 = null;

        InteriorDesignDTO interiorDesignDTO = converter.toDto(save_UTC01);
        findALl_UTC01 = new ArrayList<>();
        findALl_UTC01.add(interiorDesignDTO);

        findALl_UTC02 = null;

        getInteriorDesignById_UTC01 = converter.toDto(save_UTC01);
        getInteriorDesignById_UTC02 = null;
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

    public List<InteriorDesignDTO> getFindALl_UTC01() {
        return findALl_UTC01;
    }

    public List<InteriorDesignDTO> getFindALl_UTC02() {
        return findALl_UTC02;
    }

    public InteriorDesignDTO getGetInteriorDesignById_UTC01() {
        return getInteriorDesignById_UTC01;
    }

    public InteriorDesignDTO getGetInteriorDesignById_UTC02() {
        return getInteriorDesignById_UTC02;
    }
}
