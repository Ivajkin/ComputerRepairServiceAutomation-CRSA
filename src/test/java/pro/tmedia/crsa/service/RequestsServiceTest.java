package pro.tmedia.crsa.service;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pro.tmedia.model.Request;
import pro.tmedia.service.RequestsService;
import pro.tmedia.service.RequestsServiceImpl;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RequestsServiceTest extends TestCase {

    @Configuration
    static class ContextConfiguration {
        @Bean
        public RequestsService productService() {
            return new RequestsServiceImpl();
        }
    }

    @Autowired
    private RequestsService productService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindByNameContainsGTX520FoundOne() {

        String hardware_name = "NVIDIA GTX 520";
        String hardware_description = "Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.";
        String phone = "+7-924-123-45-67";

        List<Request> matchedProducts = productService.findByNameContains(hardware_name);

        assertNotNull(matchedProducts);
        assertTrue(matchedProducts.size() == 1);
        assertEquals(hardware_name, matchedProducts.get(0).getHardware().getName());
        assertEquals(hardware_description, matchedProducts.get(0).getHardware().getDescription());
        assertEquals(phone, matchedProducts.get(0).getPhone());

    }
}
