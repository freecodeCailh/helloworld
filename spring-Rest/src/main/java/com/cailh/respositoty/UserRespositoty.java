package com.cailh.respositoty;

import com.cailh.config.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRespositoty extends JpaRepository<User,Long>
{
    @Query("select t from User t where t.name = :name")
    User findByUserName(@Param("name") String name);

    @Query("select t from User t where t.id = :id")
    User findBuUserId(@Param("id")int id);
}

