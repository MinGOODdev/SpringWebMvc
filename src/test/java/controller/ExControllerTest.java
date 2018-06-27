package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/**/*.xml"})
public class ExControllerTest {

  private static final Logger logger = LoggerFactory.getLogger(ExControllerTest.class);

  @Inject
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void init() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    logger.info("init ...");
  }

  @Test
  public void testDoA() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doA"))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testDoB() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doB"))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testDoC() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doC?msg=LikeLion"))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void testDoD() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doD"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("productDto"));
  }

  @Test
  public void testDoE() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doE"))
            .andDo(print())
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attributeExists("msg"))
            .andExpect(redirectedUrl("/doF?msg=msg+with+redirect"));
  }

  @Test
  public void testDoJson() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doJson"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
  }

}
