package com.example.shop.management.bean.DTO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.example.shop.management.bean.Image;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

@Data
public class ImageDTO extends Image {

    private String memberName;
    /**
     * 性别  ：男：A  女：B
     */
    private String memberGender;
    /**
     * 年龄
     */

    private Integer memberAge;
    /**
     * 帐号
     */
    private String account;

}
