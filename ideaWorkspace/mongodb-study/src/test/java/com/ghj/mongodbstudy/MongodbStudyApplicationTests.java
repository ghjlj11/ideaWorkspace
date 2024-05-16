package com.ghj.mongodbstudy;

import com.ghj.mongodbstudy.entity.Comment;
import com.ghj.mongodbstudy.service.CommentService;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MongodbStudyApplicationTests {

    @Resource
    CommentService commentService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    public void save () {
        Comment comment = new Comment();
        comment.setArticleId("100000");
        comment.setContent("测试添加的数据");
        comment.setCreateDatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("凯撒大帝");
        comment.setState("1");
        comment.setLikeNum(0);
        comment.setReplyNum(0);
        comment.setParentId("2");
        commentService.saveComment(comment);
    }

    @Test
    public void testFindById(){
        ObjectId objectId = new ObjectId("6644d35c45d8fe2ad6b28da4");
        Comment comment = commentService.findCommentById(objectId);
        System.out.println(comment);
    }

    @Test
    public void testFindAll(){
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    public void del () {
        ObjectId objectId = new ObjectId("6644def78829ad060feb00bb");
        commentService.deleteCommentById(objectId);
    }

    @Test
    public void find () {
        Page<Comment> commentPage = commentService.findByParentId("2", 1, 3);
        System.out.println(commentPage.getTotalElements());
        System.out.println(commentPage.getContent());
    }

    @Test
    public void testTemplate () {
        ObjectId objectId = new ObjectId("664617f9d77f5a4e3afae14b");
        //查询对象
        Query query = Query.query(Criteria.where("userid").is("1003"));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        System.out.println(comments);
        //更新对象
        Update update = new Update();
        //局部更新，相当于$set
        // update.set(key,value)
        //递增$inc
        // update.inc("likenum",1);
        update.inc("likenum");
        update.set("state", "8");
        //参数1：查询对象
        //参数2：更新对象
        //参数3：集合的名字或实体类的类型Comment.class
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "comment");
        System.out.println(updateResult.getUpsertedId());

    }


}
