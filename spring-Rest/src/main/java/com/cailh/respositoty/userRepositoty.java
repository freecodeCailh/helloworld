package com.cailh.respositoty;

import com.cailh.config.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public  interface  userRepositoty extends CrudRepository<User,Integer>
{

    List<User> findByUserNames(@Param("name") String name);

    @Query("select t from User t where t.name = :name")
        User findByUserName(@Param("name") String name);

    @Query("select t from User t where t.id = :id")
    User findByUserId(@Param("id")int id);


}

