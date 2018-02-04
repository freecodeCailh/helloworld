package mapper;


import com.spring.springdemo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper
{
    @Select("SELECT * FROM T_USER WHERE ID = #{id}")
    User findUserByID(@Param("id") int ID);

    @Insert("INSERT INTO T_USER(NAME, PASSWORD, ID) VALUES(#{name}, #{password}, #{id})")
    int insertByName(String name);

    @Insert("INSERT INTO T_USER(NAME,PASSWORD,ID) VALUES(#{name},#{password},#{id})")
    int insert(@Param("name") String name, @Param("password") String password,@Param("id") int id);

    @Update("UPDATE T_USER(NAME,PASSWORD,ID) SET_NAME = #{name}, PASSWORD = #{password} ,ID=#{id}")
    void update(User user);
    @Delete("DELETE FROM T_USER WHERE ID = #{id}")
    void delete(Integer id);
}
