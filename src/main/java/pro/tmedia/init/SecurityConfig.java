package pro.tmedia.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * User: hairymax
 * Date: 05.05.14 20:06
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
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