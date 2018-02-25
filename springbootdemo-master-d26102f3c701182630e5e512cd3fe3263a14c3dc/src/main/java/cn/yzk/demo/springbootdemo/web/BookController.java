package cn.yzk.demo.springbootdemo.web;

import cn.yzk.demo.springbootdemo.dto.request.*;
import cn.yzk.demo.springbootdemo.dto.response.BookResp;
import cn.yzk.demo.springbootdemo.dto.response.JsonResult;
import cn.yzk.demo.springbootdemo.service.BookService;
import cn.yzk.demo.springbootdemo.util.HeaderUtil;
import cn.yzk.demo.springbootdemo.vo.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yang on 2/16/18.
 * 书籍接口
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 上传书籍
     */
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public JsonResult upload(MultipartFile book, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, true);
        String path = bookService.bookUpload(book,headers);
        return JsonResult.success("上传成功",path);

    }

    /**
     * 新建书籍
     */
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public JsonResult create(@Validated(BookReq.create.class) @RequestBody BookReq dto, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, false);
        bookService.create(dto,headers);
        return JsonResult.success("创建成功");
    }

    /**
     * 更新书籍
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult update(@Validated(BookReq.update.class) @RequestBody BookReq dto, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, false);
        bookService.update(dto,headers);
        return JsonResult.success("更新成功");
    }

    /**
     * 删除书籍
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public JsonResult delete(@Validated(BookReq.delete.class) @RequestBody BookReq dto, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, false);
        bookService.delete(dto,headers);
        return JsonResult.success("删除成功");
    }

    /**
     * 查询书籍列表
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult list(@Validated(BookReq.list.class) BookReq dto, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, false);
        List<BookResp> list = bookService.list(dto, headers);
        return JsonResult.success("查询成功",list);
    }

    /**
     * 查询书籍详情
     */
    @ResponseBody
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public JsonResult info(@Validated(BookReq.info.class) BookReq dto, HttpServletRequest request){
        HttpHeaders headers = HeaderUtil.getHeaders(request, false);
        BookResp info = bookService.info(dto, headers);
        return JsonResult.success("查询成功",info);
    }
}
