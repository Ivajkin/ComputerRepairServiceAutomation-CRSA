package pro.tmedia;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class IntegrationTestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

}