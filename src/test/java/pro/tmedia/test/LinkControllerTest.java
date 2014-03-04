package pro.tmedia.test;

import junit.framework.TestCase;
import pro.tmedia.controller.LinkController;

/**
 * User: Ivaykin Timofey
 * Date: 2/19/14
 */
public class LinkControllerTest extends TestCase {
    public void testMainPage() throws Exception {
        LinkController controller = new LinkController();
        assertNotNull(controller.mainPage());
    }

    public void testIndexPage() throws Exception {
        LinkController controller = new LinkController();
        assertNotNull(controller.indexPage());
    }
}
