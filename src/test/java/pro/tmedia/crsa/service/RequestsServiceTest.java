package pro.tmedia.crsa.service;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pro.tmedia.service.RequestsService;
import pro.tmedia.service.RequestsServiceImpl;

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

}
