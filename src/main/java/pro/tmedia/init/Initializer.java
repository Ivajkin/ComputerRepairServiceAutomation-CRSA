package pro.tmedia.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
public class Initializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        FilterRegistration charEncodingfilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
        charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");

        // FilterRegistration.Dynamic mockSecurityFilter       = servletContext.addFilter ("mockSecurityFilter", "org.ghc.security.MockSecurityFilter");
        // mockSecurityFilter.addMappingForUrlPatterns         (EnumSet.of (REQUEST), true, "/*");

        // FilterRegistration.Dynamic siteMinderSecurityFilter = servletContext.addFilter ("siteMinderSecurityFilter", "org.ghc.security.SiteMinderSecurityFilter");
        // siteMinderSecurityFilter.addMappingForUrlPatterns   (EnumSet.of (REQUEST), true, "/*");

        // FilterRegistration.Dynamic userDetailsStoreFilter   = servletContext.addFilter ("userDetailsStoreFilter", "org.ghc.security.UserDetailsStoreFilter");
        // userDetailsStoreFilter.addMappingForUrlPatterns     (EnumSet.of (REQUEST), true, "/*");


        // Static resource handling using "default" servlet
        servletContext.getServletRegistration ("default").addMapping ("*.js", "*.css", "*.jpg", "*.gif", "*.png");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(servletContext);
        ctx.refresh();

        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.addMapping("*.css");
        servlet.setLoadOnStartup(1);

        //servletContext.getServletRegistration ("default").addMapping (".js", ".css", ".jpg", ".gif", "*.png");
    }

}

