package com.majianwei.plat.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author wbtest
 * @since 2019-03-30
 */
@TableName("t_product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long createTime;
    private Long updateTime;

    private String name;//商品名称

    private String subName;//副名称

    private String code;//商品编码

    private Long productTypeId;//商品类型ID

    private Long onSaleTime;//上架时间

    private String medias;

    //createTime这个字段是long类型的时间字段，不是Date类型，所以要将它转化为String类型的数据来展示
    @TableField(exist = false)//表示表中没有这个字段
    private String stringOnSaleTime;

    //createTime这个字段是long类型的时间字段，不是Date类型，所以要将它转化为String类型的数据来展示
    @TableField(exist = false)//表示表中没有这个字段
    private String stringOffSaleTime;

    public String getStringOffSaleTime() {
        Long offSaleTime = getOffSaleTime();
        if (offSaleTime!=null && offSaleTime!=0){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(offSaleTime);
            return simpleDateFormat.format(date);
        }
        return null;
    }

    public void setStringOffSaleTime(String stringOffSaleTime) {
        this.stringOffSaleTime = stringOffSaleTime;
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias;
    }

    public String getStringOnSaleTime() {
        Long onSaleTime = getOnSaleTime();
        if (onSaleTime!=null && onSaleTime!=0){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(onSaleTime);
            return simpleDateFormat.format(date);
        }
        return null;
    }

    public void setStringOnSaleTime(String stringOnSaleTime) {
        this.stringOnSaleTime = stringOnSaleTime;
    }

    private Long offSaleTime;//下架时间
    private Long brandId;

    private Integer state;//状态

    private Integer maxPrice;

    private Integer minPrice;

    private Integer saleCount;

    private Integer viewCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 评分
     */
    private Integer commentScore;
    /**
     * 显示属性摘要
     */
    private String viewProperties;
    private Integer goodCommentCount;
    private Integer commonCommentCount;
    private Integer badCommentCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Long onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Long getOffSaleTime() {
        return offSaleTime;
    }

    public void setOffSaleTime(Long offSaleTime) {
        this.offSaleTime = offSaleTime;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public Integer getGoodCommentCount() {
        return goodCommentCount;
    }

    public void setGoodCommentCount(Integer goodCommentCount) {
        this.goodCommentCount = goodCommentCount;
    }

    public Integer getCommonCommentCount() {
        return commonCommentCount;
    }

    public void setCommonCommentCount(Integer commonCommentCount) {
        this.commonCommentCount = commonCommentCount;
    }

    public Integer getBadCommentCount() {
        return badCommentCount;
    }

    public void setBadCommentCount(Integer badCommentCount) {
        this.badCommentCount = badCommentCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", code='" + code + '\'' +
                ", productTypeId=" + productTypeId +
                ", onSaleTime=" + onSaleTime +
                ", medias='" + medias + '\'' +
                ", stringOnSaleTime='" + stringOnSaleTime + '\'' +
                ", stringOffSaleTime='" + stringOffSaleTime + '\'' +
                ", offSaleTime=" + offSaleTime +
                ", brandId=" + brandId +
                ", state=" + state +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", saleCount=" + saleCount +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                ", commentScore=" + commentScore +
                ", viewProperties='" + viewProperties + '\'' +
                ", goodCommentCount=" + goodCommentCount +
                ", commonCommentCount=" + commonCommentCount +
                ", badCommentCount=" + badCommentCount +
                '}';
    }
}
