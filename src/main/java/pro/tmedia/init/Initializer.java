package pro.tmedia.init;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import org.apache.log4j.*;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Conventions;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.util.Assert;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
//import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionTrackingMode;
import java.io.OutputStreamWriter;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
public class Initializer  implements WebApplicationInitializer {

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

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
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

        initLogger();
    }

    private void initLogger() {
        ConsoleAppender appender = new ConsoleAppender();
        appender.setWriter(new OutputStreamWriter(System.out));
        appender.setLayout(new PatternLayout("%d{ABSOLUTE} %5p %c{1}:%L - %m%n"));
        appender.setName("consoleLog");
        appender.activateOptions();
        Logger.getRootLogger().addAppender(appender);
        LogManager.getRootLogger().setLevel(Level.TRACE);
        // log4j.rootLogger=INFO, file, stdout
        // log4j.logger.org.hibernate=INFO
        // log4j.logger.org.hibernate.type=ALL
    }
}

