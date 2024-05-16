package com.ghj.mongodbstudy.dao;

import com.ghj.mongodbstudy.entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * <p>
 * Description: 评论的持久层接口
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/5/15 23:03
 */
public interface CommentRepository extends MongoRepository<Comment,String> {

    /**
     * ObjectId 查询
     * @param id
     * @return
     */
    Optional<Comment> findById(ObjectId id);

    /**
     * deleteById
     * @param id
     */
    void deleteById(ObjectId id);

    /**
     * findByParentId
     * @param parentId
     * @param pageable
     * @return
     */
    Page<Comment> findByParentId(String parentId, Pageable pageable);
}
