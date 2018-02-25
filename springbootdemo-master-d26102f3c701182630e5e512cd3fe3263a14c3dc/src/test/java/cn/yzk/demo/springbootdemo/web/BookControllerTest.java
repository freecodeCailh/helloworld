package cn.yzk.demo.springbootdemo.web;

import cn.yzk.demo.springbootdemo.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

/**
 * Created by yang on 2/16/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class BookControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mock;
    private Integer random;

    @Before
    public void setup(){
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        random = new Random().nextInt(9999);
    }

    /**
     * 测试新建书籍
     */
    @Test
    public void createTest() throws Exception {
        String userId = "userId"+random;
        String name = "book" + random;
        String path = "path" + random;
        String desc = "desc" + random;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/book/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC")
                .content("{\"path\":\"" + path + "\",\"name\":\"" + name + "\",\"desc\":\"" + desc + "\"}");

        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"));
    }

    /**
     * 测试获取书籍详情
     * @throws Exception
     */
    @Test
    @Sql("/sql/test/book_controller_info_test.sql")
    public void infoTest() throws Exception {
        String userId = "userId"+random;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/book/info")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC")
                .param("uuid","testUuid");


        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.name").isString());

    }

    /**
     * 测试书籍上传
     * @throws Exception
     */
    @Test
    public void uploadTest() throws Exception {
        String userId = "userId"+random;
        InputStream fileInput = this.getClass().getResourceAsStream("/bookuploadtest.txt");
        MockMultipartFile file = new MockMultipartFile("book","bookuploadtest.txt","application/octet-stream",fileInput);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/book/upload")
                .file(file)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC");

        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data").isString())
        ;
    }
    @Test
    @Sql("/sql/test/book_controller_info_test.sql")
    public void listTest() throws Exception
    {
        String userId = "userId"+random;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/book/list")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC")
                .param("uuid","testUuid");


        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"));
    }
    @Test
    @Sql("/sql/test/book_controller_delete_test.sql")
    public void deleteTest() throws Exception {
        String userId = "userId"+random;
        String name = "book" + random;
        String path = "path" + random;
        String desc = "desc" + random;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/book/delete")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC")
                .content("{\"uuid\":\"testUuid\"}");
        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"));

    }
    @Test
    @Sql("/sql/test/book_controller_update_test.sql")
    public void updateTest() throws Exception
    {
        String userId = "userId"+random;
        String name = "book" + random;
        String path = "path" + random;
        String desc = "desc" + random;
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/book/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("userId", userId)
                .header("app", "BOOK")
                .header("platform", "PC")
                .content("{\"uuid\":\"testUuid\"}" );
        mock.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1"));
    }
    //TODO 实现其他几个测试用例方法


}
