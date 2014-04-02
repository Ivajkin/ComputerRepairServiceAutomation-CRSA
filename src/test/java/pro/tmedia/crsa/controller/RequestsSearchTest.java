package pro.tmedia.crsa.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.tmedia.controller.RequestsController;
import pro.tmedia.init.WebAppConfig;
import pro.tmedia.test.IntegrationTestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
public class RequestsSearchTest extends WebAppConfig {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new RequestsController()).build();
    }

    @Test
    public void testRequestList() throws Exception {
        this.mockMvc.perform(post("/requests")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].hardware.name").exists());
    }

    @Test
    public void testSearchRequest() throws Exception {
        String keyword = "NVIDIA GTX 520";
        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("found"))
                .andExpect(jsonPath("$.requests[0].hardware.name").value(keyword));
    }

    @Test
    public void testSearchRequestByNameGTXFoundOne() throws Exception {
        String keyword = "NVIDIA GTX 550";
        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("found"))
                .andExpect(jsonPath("$.requests[0].hardware.name").value(keyword))
                .andExpect(jsonPath("$.requests[1]").doesNotExist());
    }

    @Test
    public void testSearchRequestByNameGTXFoundTwo() throws Exception {
        String keyword = "GTX";

        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("found"))
                .andExpect(jsonPath("$.requests[0].hardware.name").value("NVIDIA GTX 520"))
                .andExpect(jsonPath("$.requests[1].hardware.name").value("NVIDIA GTX 550"));
    }

    @Test
    public void testSearchRequestByNameNotFound() throws Exception {
        String keyword = "Intel i7 3254";
        String text = String.format("Нет возможности найти запрос, содержащий '%s'", keyword);

        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("not found"))
                .andExpect(jsonPath("$.text").value(text));
    }


}