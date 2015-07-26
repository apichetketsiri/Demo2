package controller;

import main.model.controller.FormController;
import main.model.model.Person;
import main.model.service.ServiceExample;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Admin on 7/24/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class FormControllerTest {
    @InjectMocks
    FormController formController;
    @Mock
    ServiceExample serviceExampleMock;

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
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
    public void showForm_showFormPage_FormViewWasReturn()throws Exception{
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("Form"));

    }
    @Test
    public void checkPersonInfo_usernameAndAgeValid_DonePageWasReturn()throws Exception{
        mockMvc.perform(post("/submit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Boss")
                .param("age", "19")
                .sessionAttr("person", new Person()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("Done"));
    }

    @Test
    public void checkPersonInfo_usernameAndAgeInValid_FormPageWasReturn()throws Exception{
        mockMvc.perform(post("/submit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "boss")
                .param("age", "ff")
                .sessionAttr("person", new Person()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("Form"));
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

    @Test(expected = NullPointerException.class)
    public void getException_throwExceptionFromMethod_exceptionWasThrown()throws Exception{
       formController.getException();
    }
}
