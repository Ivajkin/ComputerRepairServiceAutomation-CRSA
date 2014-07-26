package pro.tmedia.crsa.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pro.tmedia.controller.RequestsController;
import pro.tmedia.init.WebAppConfig;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Request;
import pro.tmedia.test.IntegrationTestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        this.mockMvc.perform(get("/requests")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].req_num_id").exists())
                .andExpect(jsonPath("$[0].hardware.name").exists())
                .andExpect(jsonPath("$[0].hardware.description").exists())
                .andExpect(jsonPath("$[0].hardware.category").exists())
                .andExpect(jsonPath("$[0].hardware.category.id").exists())
                .andExpect(jsonPath("$[0].hardware.category.name").exists())

                .andExpect(jsonPath("$[0].hardware.manufacturer.id").exists())
                .andExpect(jsonPath("$[0].hardware.manufacturer.name").exists())
                .andExpect(jsonPath("$[0].hardware.model").exists())
                .andExpect(jsonPath("$[0].hardware.serial_number").exists())
                .andExpect(jsonPath("$[0].hardware.fault.id").exists())
                .andExpect(jsonPath("$[0].hardware.fault.name").exists())
                .andExpect(jsonPath("$[0].hardware.appearance.id").exists())
                .andExpect(jsonPath("$[0].hardware.appearance.name").exists())
                .andExpect(jsonPath("$[0].hardware.completeness.id").exists())
                .andExpect(jsonPath("$[0].hardware.completeness.name").exists())
                .andExpect(jsonPath("$[0].hardware.phone").exists())
                .andExpect(jsonPath("$[0].hardware.address").exists())
                .andExpect(jsonPath("$[0].hardware.customer_name").exists())
                .andExpect(jsonPath("$[0].hardware.source.id").exists())
                .andExpect(jsonPath("$[0].hardware.source.name").exists())
                .andExpect(jsonPath("$[0].hardware.date_of_call").exists())
                .andExpect(jsonPath("$[0].hardware.note").exists())
                .andExpect(jsonPath("$[0].hardware.approximate_cost").exists())
                .andExpect(jsonPath("$[0].hardware.prepayment").exists())
                .andExpect(jsonPath("$[0].hardware.acceptor.id").exists())
                .andExpect(jsonPath("$[0].hardware.acceptor.name").exists())
                .andExpect(jsonPath("$[0].hardware.responsible.id").exists())
                .andExpect(jsonPath("$[0].hardware.responsible.name").exists())
                .andExpect(jsonPath("$[0].hardware.date_of_receipt").exists())
                .andExpect(jsonPath("$[0].hardware.date_of_issue").exists())
                .andExpect(jsonPath("$[0].hardware.amount").exists())
                .andExpect(jsonPath("$[0].hardware.method_of_payment").exists())
                .andExpect(jsonPath("$[0].hardware.request_status.id").exists())
                .andExpect(jsonPath("$[0].hardware.request_status.name").exists())
                .andExpect(jsonPath("$[0].hardware.completed_works.id").exists())
                .andExpect(jsonPath("$[0].hardware.completed_works.name").exists())
                .andExpect(jsonPath("$[0].hardware.parts_installed.id").exists())
                .andExpect(jsonPath("$[0].hardware.parts_installed.name").exists());
    }

    @Test
    public void testCreateRequest() throws Exception {

        /*Request request = new Request();
        Hardware hardware = new Hardware();
        hardware.setName("iPad Screen");
        request.setHardware(hardware);                 */
        /*Gson gson = new Gson();

        this.mockMvc.perform(post("/requests")
                .contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
                .body(gson.toJson(hardware))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8));


        this.mockMvc.perform(get("/requests")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].hardware.name").value(hardware.getName()));         */
    }

    /*@Test
    public void testSearchRequest() throws Exception {
        String keyword = "NVIDIA GTX 520";
        this.mockMvc.perform(get("/requests/search")
                .param("q", keyword)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("found"))
                .andExpect(jsonPath("$.requests[0].hardware.name").value(keyword));
    }     */

    /*@Test
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
    }   */

    /*@Test
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
    }       */

    /*@Test
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
    }        */


}