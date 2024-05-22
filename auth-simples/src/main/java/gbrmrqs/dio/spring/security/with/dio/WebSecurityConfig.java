package gbrmrqs.dio.spring.security.with.dio;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/managers").hasAnyRole("MANAGERS")
                .antMatchers("/users").hasAnyRole("USERS","MANAGERS")
                .anyRequest().authenticated().and().formLogin();
    }

    @Override
    //    vai criar uma cadeia de usuarios em memoria
    protected void configure
            (AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("gabriela")
                .password("{noop}password")
                .roles("USERS")
                .and()
                .withUser("admin")
                .password("{noop}password")
//                perfil específico de acesso
                .roles("MANAGERS");
    }
}
