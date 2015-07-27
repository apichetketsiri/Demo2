package controller;

import main.model.controller.FormController;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Admin on 7/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class RestFulController {

    @InjectMocks
    FormController formController;
    @Autowired
    private WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Before class");

    }
    @AfterClass
    public static void afterClass(){
        System.out.println("After class");

    }
    @Before
    public void setup(){
        System.out.println("set up");
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @After
    public void teardown(){
        System.out.println("teardown");
    }

    @Test
    public void getPerson_getPerson_PersonDataWasReturn()throws Exception{
        mockMvc.perform(get("/api/person/{name}/{id}","Boss","20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("name").value("Boss"))
                .andExpect(jsonPath("age").value(20));
    }
    @Test
    public void getPerson_containString_PersonDataWasReturn()throws Exception{
        mockMvc.perform(get("/api/person/{name}/{id}", "Boss","20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("age").value(20))
                .andExpect(content().string(containsString("Bos")));

    }

}
