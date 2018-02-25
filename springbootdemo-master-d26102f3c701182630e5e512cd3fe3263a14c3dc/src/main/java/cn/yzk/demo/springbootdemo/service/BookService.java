package cn.yzk.demo.springbootdemo.service;

import cn.yzk.demo.springbootdemo.config.BookConfig;
import cn.yzk.demo.springbootdemo.dao.BookDao;
import cn.yzk.demo.springbootdemo.dto.request.*;
import cn.yzk.demo.springbootdemo.dto.response.BookResp;
import cn.yzk.demo.springbootdemo.entity.Book;
import cn.yzk.demo.springbootdemo.exception.ServiceException;
import cn.yzk.demo.springbootdemo.util.BaseUtil;
import cn.yzk.demo.springbootdemo.vo.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang on 2/16/18.
 * 书籍业务处理
 */
@Service
public class BookService {
    private static Logger logger = LoggerFactory.getLogger(BookService.class.getName());
    int words_num=0;
    String tempstr;
    String filePath;
    String dirPath;


    @Autowired
    private BookDao bookDao;
    @Autowired
    private BookConfig bookConfig;

    /**
     * 上传书籍
     * @return
     */
    public String bookUpload(MultipartFile file,HttpHeaders headers){



        try{
            if (file == null) throw new ServiceException("文件上传发生错误");
            dirPath = bookConfig.getSavePath() + "/" + headers.getUserId();
            File dir = new File(dirPath);
            if (!dir.exists() && !dir.mkdirs()){
                throw new ServiceException("创建文件存储目录发生错误");
            }
            filePath = dirPath + "/" + file.getOriginalFilename();
            File save = new File(filePath);
            file.transferTo(save);
            System.out.println(statistics(filePath));
            //TODO 统计上传文件字数
        }catch (Exception e){
            logger.error("上传书籍时发生错误",e);
            throw new ServiceException("上传书籍时发生错误");
        }
        logger.info("上传书籍成功: " + filePath);
        return filePath;
    }

    /**
     * 业务方法 创建书籍
     * @param dto
     * @return
     */
    @Transactional
    public void create(BookReq dto, HttpHeaders headers){
        try {
            Book book = new Book();
            book.setUuid(BaseUtil.uuid());
            book.setBookName(dto.getName());
            book.setBookDesc(dto.getDesc());
            book.setBookPath(dto.getPath());
            book.setUserId(headers.getUserId());
            book.setWordNum(0);
            Book save = bookDao.save(book);
            if (save.getId() == null) throw new ServiceException();
        }catch (Exception e){
            logger.error("新建书籍时发生错误",e);
            throw new ServiceException("新建书籍时发生错误");
        }
        logger.info("新建书籍成功");
    }

    /**
     * 业务方法 更新书籍
     * @param dto
     * @return
     */
    @Transactional
    public void update(BookReq dto,HttpHeaders headers){
        try {
            Book find = bookDao.findByUuid(dto.getUuid());
            if (find == null) {
                throw new ServiceException("书籍不存在");
            }
            if (dto.getName() != null){
                find.setBookName(dto.getName());
            }
            if (dto.getDesc() != null){
                find.setBookDesc(dto.getDesc());
            }
            find.setUpdateTime(new Date());
            bookDao.save(find);
        }catch (Exception e)
        {
            logger.error("更新书籍时发生错误",e);
            throw new ServiceException("更新书籍时发生错误");
        }
        logger.info("更新书籍成功");
    }

    /**
     * 删除书籍
     * @param dto
     * @return
     */
    @Transactional
    public void delete(BookReq dto,HttpHeaders headers){
        try {
            Book find = bookDao.findByUuid(dto.getUuid());
            if (find == null) {
                throw new ServiceException("书籍不存在");
            }
            bookDao.deleteByUuid(dto.getUuid());
        }catch (Exception e){
            logger.error("删除书籍时发生错误",e);
            throw new ServiceException("删除书籍时发生错误");
        }
        logger.info("删除书籍成功: " + dto.getUuid());
    }

    /**
     * 查询书籍列表
     * @param dto
     * @return
     */
    public List<BookResp> list(BookReq dto,HttpHeaders headers){
        List<BookResp> result = new ArrayList<BookResp>();
        PageRequest pageRequest = new PageRequest(dto.getPage(), dto.getLimit());
        try {
            List<Book> books = new ArrayList<Book>();
            Page<Book> list;

            if (dto.getName() != null){
                list = bookDao.list(dto.getName(), pageRequest);
            }else{
                list = bookDao.findAll(pageRequest);
            }

            if (list != null){
                books = list.getContent();
            }

            books.forEach(book ->{
                BookResp resp = new BookResp();
                resp.setUuid(book.getUuid());
                resp.setName(book.getBookName());
                resp.setDesc(book.getBookDesc());
                resp.setWordNum(book.getWordNum());
                result.add(resp);
            });
        }catch (Exception e){
            logger.error("查询书籍列表时发生错误",e);
            throw new ServiceException("查询书籍列表时发生错误");
        }
        logger.info("查询书籍列表成功，返回行数： " + result.size());
        return result;
    }

    /**
     * 查看书籍详情
     * @param dto
     * @return
     */
    public BookResp info(BookReq dto,HttpHeaders headers)
    {
        BookResp resp;
        try {
            Book find = bookDao.findByUuid(dto.getUuid());
            resp = new BookResp();
            resp.setUuid(find.getUuid());
            resp.setName(find.getBookName());
            resp.setDesc(find.getBookDesc());
            resp.setWordNum(find.getWordNum());
        }catch (Exception e){
            logger.error("查询书籍详情时发生错误",e);
            throw new ServiceException("查询书籍详情时发生错误");
        }
        logger.info("查询书籍详情成功: " + dto.getUuid());
        return resp;
    }
    /**
     *统计字数方法传入文件路径
     * 返回文件总字数
     */
    public String statistics(String filePath)
    {
   try {
    BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
    Pattern pattern = Pattern.compile("([\u4e00-\u9fa5,，.。、/<>?？;；'‘’:\"【】{}^(A-Za-z)]{1})"); //定义匹配模式：汉字或标点符号
    while((tempstr = br.readLine()) != null && tempstr != ""){

        //汉字匹配，统计字数
        Matcher matcher = pattern.matcher(tempstr);
        while(matcher.find()) words_num++;
        tempstr = "";
    }
    br.close();
    }catch (Exception E)
       {
       logger.error("统计字数时出现错误");
       }
        logger.info("统计字数成功，总字数为："+words_num);
        return ("**********************************总字数"+words_num);
    }
}
