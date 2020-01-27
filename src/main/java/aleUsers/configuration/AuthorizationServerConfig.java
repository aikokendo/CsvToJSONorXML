package aleUsers.configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        clients.inMemory()
                .withClient("gateway")
                .secret(encoder.encode("secret"))
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("WRITE","READ")
                .authorities("WRITE","READ")
                .autoApprove(true) ;
    }


    //Oauth grant_type "password" is disabled by default. In order to activate it we need to inject a AuthManager
    // the request factory checks that the scopes requested are allowed to the client.
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientService);
        defaultOAuth2RequestFactory.setCheckUserScopes(true);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .requestFactory(defaultOAuth2RequestFactory);
    }

    }
