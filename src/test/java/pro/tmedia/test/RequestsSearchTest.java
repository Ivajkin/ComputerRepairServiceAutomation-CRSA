package pro.tmedia.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pro.tmedia.controller.RequestsController;
import pro.tmedia.init.WebAppConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void testSearchProduct() throws Exception {
        String keyword = "NVIDIA GTX 520";
        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hardware.name").value(keyword));
    }

}