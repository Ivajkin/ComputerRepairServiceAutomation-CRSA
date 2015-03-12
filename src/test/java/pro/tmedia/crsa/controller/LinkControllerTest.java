package pro.tmedia.crsa.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.tmedia.controller.LinkController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: Ivaykin Timofey
 * Date: 2/19/14
 */
public class LinkControllerTest {
    private MockMvc mockMvc;
/*
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LinkController()).build();
    }
*/
    /*@Test
    public void testMainPage() throws Exception {
        LinkController controller = new LinkController();
        assertNotNull(controller.mainPage());
    }

    @Test
    public void testIndexPage() throws Exception {
        LinkController controller = new LinkController();
        assertNotNull(controller.indexPage());
    }    */

/*
    @Test
    public void testIndexGet1() throws Exception {
        this.mockMvc.perform(get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
    @Test
    public void testIndexGet2() throws Exception {
        this.mockMvc.perform(get("/index.html")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
*/
}
