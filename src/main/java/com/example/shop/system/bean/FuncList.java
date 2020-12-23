package com.example.shop.system.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@TableName("func_list")
public class FuncList extends Model<FuncList> {

    private static final long serialVersionUID = 1L;

    @TableId("func_id")
    private Integer funcId;
    @TableField("parent_id")
    private Integer parentId;
    @TableField("menu_title")
    private String menuTitle;
    @TableField("menu_desc")
    private String menuDesc;
    @TableField("menu_icon")
    private String menuIcon;
    @TableField("menu_url")
    private String menuUrl;
    @TableField("menu_level")
    private Integer menuLevel;
    @TableField("list_order")
    private Integer listOrder;
    @TableField("right_code")
    private String rightCode;
    private String remark;
    private String sts;


    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getListOrder() {
        return listOrder;
    }

    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    @Override
    protected Serializable pkVal() {
        return this.funcId;
    }

    @Override
    public String toString() {
        return "FuncList{" +
        ", funcId=" + funcId +
        ", parentId=" + parentId +
        ", menuTitle=" + menuTitle +
        ", menuDesc=" + menuDesc +
        ", menuIcon=" + menuIcon +
        ", menuUrl=" + menuUrl +
        ", menuLevel=" + menuLevel +
        ", listOrder=" + listOrder +
        ", rightCode=" + rightCode +
        ", remark=" + remark +
        ", sts=" + sts +
        "}";
    }
}
