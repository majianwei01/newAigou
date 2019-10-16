package com.majianwei.plat.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author wbtest
 * @since 2019-04-07
 */
@TableName("t_specification")
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 规格名称
     */
    private String specName;
    private Integer type;
    @TableField("product_type_id")
    private Long productTypeId;

    @TableField(exist = false)//这个字段用于展示前台扩展属性的值
    private String value;

    @TableField(exist = false)
    private List<String> skuValue=new ArrayList<>();//sku的值：每一个sku可能有多个选项值

    public List<String> getSkuValue() {
        return skuValue;
    }

    public void setSkuValue(List<String> skuValue) {
        this.skuValue = skuValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                ", type=" + type +
                ", productTypeId=" + productTypeId +
                ", value='" + value + '\'' +
                ", skuValues=" + skuValue +
                '}';
    }
}
