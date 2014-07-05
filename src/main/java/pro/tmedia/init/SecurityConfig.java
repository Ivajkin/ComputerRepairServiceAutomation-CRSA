package pro.tmedia.init;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pro.tmedia.model.Employee;
import pro.tmedia.service.UserService;

import javax.sql.DataSource;
import java.util.List;

/**
 * User: hairymax
 * Date: 05.05.14 20:06
 */

@Configuration
@ComponentScan("pro.tmedia")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<Employee> userList = userService.list();
        for (Employee anUserList : userList) {
            auth.inMemoryAuthentication().withUser(anUserList.getLogin()).password(anUserList.getPassword_hash()).roles("USER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
             .csrf()
                 .disable()
             .formLogin()
                 .loginProcessingUrl("/login")
                 .loginPage("/login")
                 .failureUrl("/login?error")
                 .permitAll()
                 .and()
             .authorizeRequests()
                 .antMatchers("/").hasRole("USER");
    }


}