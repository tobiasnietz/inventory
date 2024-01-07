package com.thwildau.inventory;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class InventoryControllerTest {

    @Autowired
    private InventoryController controller;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void allOk() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/inventory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"name\":\"Product 1\",\"location\":\"Berlin\"},{\"id\":2,\"name\":\"Product 2\",\"location\":\"Dresden\"}]"));
    }

    @Test
    void oneOk() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"name\":\"Product 1\",\"location\":\"Berlin\"}"));
    }

    @Test
    void oneNotFound() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/inventory/12"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void locationOk() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/inventory/location/Berlin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"name\":\"Product 1\",\"location\":\"Berlin\"}]"));
    }

    @Test
    void addInventoryIsCreated() throws Exception {
        String addItem = "{\"name\": \"Product 3\", \"location\" : \"Muenchen\"}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/inventory")
                        .content(addItem)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":3,\"name\":\"Product 3\",\"location\":\"Muenchen\"}"));
    }

    @Test
    void addInventoryBadRequest() throws Exception {
        String addItemForBadRequest = "{\"name\": \"\", \"location\" : \"\"}";
        mvc.perform(MockMvcRequestBuilders
                        .post("/inventory")
                        .content(addItemForBadRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void replaceInventoryIsOk() throws Exception{
        String item = "{\"name\": \"Product 2 Updated\", \"location\" : \"Muenchen Updated\"}";
        mvc.perform(MockMvcRequestBuilders
                        .put("/inventory/2")
                        .content(item)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":2,\"name\":\"Product 2 Updated\",\"location\":\"Muenchen Updated\"}"));
    }

    @Test
    void updateInventoryBadRequest() throws Exception{
        String item = "{\"name\": \"\", \"location\" : \"\"}";
        mvc.perform(MockMvcRequestBuilders
                        .put("/inventory/1")
                        .content(item)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateInventoryNotFound() throws Exception {
        String item = "{\"name\": \"Product 13 Updated\", \"location\" : \"Muenchen Updated\"}";
        mvc.perform(MockMvcRequestBuilders
                        .put("/inventory/13")
                        .content(item)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteInventoryOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteInventoryNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/inventory/11"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}