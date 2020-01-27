package aleUsers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //to allow pre/post authorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${role.admin}")
    private String adminRole;

    @Value("${role.user}")
    private String userRole;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //we need an encoder for spring security 5
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("admin1")).roles(userRole, adminRole)
                .and().withUser("simpleUser").password(encoder.encode("pass")).roles(userRole)
                .and().withUser("nakis").password(encoder.encode("fer")).roles(adminRole);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //first lets allow rest requests to login with basic auth.
        // if we do not do this, REST requests get the login page sent back.
        http.httpBasic();

        // then we are disabling csrf, because I'm using basic auth for now.
        // this is not ideal outside of demos, as it makes the app unsecured.
        http.csrf().disable();

        //simple login and access pages provided by spring. can be accessed at /login and /logout.
        // I'm exposing them since it helps with debugging.
        http.formLogin().permitAll().and().logout().permitAll();

        // if we want to limit access based on role, we can do it here.
        // for example here we are limiting /users/* to only users with role "ADMIN"
        // but only for POST methods, other requests have normal access.

        //allowing the OAuth2 token endpoint to be accessed with no authentication
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users/**").hasRole(adminRole)
                .anyRequest().authenticated();
    }


    //Oauth grant_type "password" is disabled by default. In order to activate it we need to inject a AuthManager
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}