package cn.yzk.demo.springbootdemo.dao;

import cn.yzk.demo.springbootdemo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang on 2/16/18.
 * 书籍 JPA接口
 */
@Repository
public interface BookDao extends JpaRepository<Book,Long> {
    /**
     * 根据UUID获取
     * @param uuid
     * @return
     */
    Book findByUuid(String uuid);

    /**
     * 通过UUID删除书籍
     * @param uuid
     * @return
     */
    @Modifying
    @Query(value = "DELETE FROM book WHERE uuid = ?1",nativeQuery = true)
    int deleteByUuid(String uuid);

    /**
     * 分页查询
     * @param name
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM book WHERE INSTR(name,?1)>0 ORDER BY ?#{#pageable}",
            countQuery = "SELECT COUNT(*) FROM BOOK WHERE INSTR(name,?1)>0",
            nativeQuery = true)
    Page<Book> list(String name , Pageable pageable);
}
