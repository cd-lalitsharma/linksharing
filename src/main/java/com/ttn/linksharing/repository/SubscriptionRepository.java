package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Subscriptions;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscriptions,Integer> {

    List<Subscriptions> findSubscriptionsByUserId(Integer userId);
    
    Subscriptions findSubscriptionsByUserAndTopic(User user, Topics topic);
    
    Boolean deleteSubscriptionsByUserAndTopic(User user,Topics topic);
//    @Query("select p.posts from Subscriptions s JOIN Posts p where s.topic=p.topic and s.user=:user and s.user not in (select ReadPosts.user from ReadPosts.user=s.user) ");
//    @Query("select s from Subscriptions s join Posts p on  s.topic =p.topic and ")
//    List<Posts> getUnreadPostsOfUser(@Param("user") User user);
//
//
    
}
