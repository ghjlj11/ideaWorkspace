package com.ghj.mongodbstudy.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/5/15 0:06
 */
@Data
@Document(collection = "comment")
public class Comment implements Serializable {

    /**
     * //主键
     */
    @Id
    private String id;
    /**
     * //该属性对应mongodb的字段的名字，如果一致，则无需该注解
     */
    @Field("content")
    private String content;
    private Date publishTime;
    /**
     * 发布人ID
     */
    @Indexed
    private String userid;
    /**
     * //昵称
     */
    private String nickname;
    /**
     * //评论的日期时间
     */
    @Field("createdatetime")
    private LocalDateTime createDatetime;
    /**
     * //点赞数
     */
    @Field("likenum")
    private Integer likeNum;
    /**
     * //回复数
     */
    private Integer replyNum;
    /**
     * //状态
     */
    private String state;
    /**
     * //上级ID
     */
    private String parentId;
    @Field("articleid")
    private String articleId;
}
