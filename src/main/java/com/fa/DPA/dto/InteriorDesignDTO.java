package com.fa.DPA.dto;

import com.fa.DPA.model.SubCategory;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class InteriorDesignDTO extends AbstractDTO {

    private String title;

    private String description;

    private String content;

    private String imageSource;

    private String subCategoryName;

    private Date createDate;

    private String createBy;

    private String thumbnail;

}
