package com.chen.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenChen
 * @since 2021-10-04
 */
@ApiModel(value = "Ms_article对象", description = "")
public class Article implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论数量")
    private Integer comment_counts;

    @ApiModelProperty(value = "创建时间")
    private Long create_date;

    @ApiModelProperty(value = "简介")
    private String summary;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "浏览数量")
    private Integer view_counts;

    @ApiModelProperty(value = "是否置顶")
    private Integer weight;

    @ApiModelProperty(value = "作者id")
    private Long author_id;

    @ApiModelProperty(value = "内容id")
    private Long body_id;

    @ApiModelProperty(value = "类别id")
    private Integer category_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getComment_counts() {
        return comment_counts;
    }

    public void setComment_counts(Integer comment_counts) {
        this.comment_counts = comment_counts;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getView_counts() {
        return view_counts;
    }

    public void setView_counts(Integer view_counts) {
        this.view_counts = view_counts;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getBody_id() {
        return body_id;
    }

    public void setBody_id(Long body_id) {
        this.body_id = body_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Ms_article{" +
        "id=" + id +
        ", comment_counts=" + comment_counts +
        ", create_date=" + create_date +
        ", summary=" + summary +
        ", title=" + title +
        ", view_counts=" + view_counts +
        ", weight=" + weight +
        ", author_id=" + author_id +
        ", body_id=" + body_id +
        ", category_id=" + category_id +
        "}";
    }
}
