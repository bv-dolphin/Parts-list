package com.example.part_list;

import com.example.parts_list.controller.PartController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PartController.class)
@ComponentScan("com.example") //to scan packages mentioned
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql (value = {"/parts-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql (value = {"/parts-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PartControllerTest {
    @Autowired
    private PartController partController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        assertThat(partController).isNotNull();
    }
    @Test
    public void test1() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("Hello, die!")));
    }

    @Test
    public void partListTest() throws Exception {
        this.mockMvc.perform(get("/part_list"))
                .andDo(print())
                .andExpect(xpath("//*[@id='tableParts']").nodeCount(5));
    }
    @Test
    public void searchTest() throws Exception {
        this.mockMvc.perform(get("/part_list").param("search", "HDD"))
                .andDo(print()).andExpect(xpath("//*[@id='tableParts']").nodeCount(1));
    }
}
