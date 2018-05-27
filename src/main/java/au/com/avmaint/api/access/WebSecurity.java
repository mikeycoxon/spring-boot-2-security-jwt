package au.com.avmaint.api.access;

import au.com.avmaint.api.access.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static au.com.avmaint.api.access.SecurityConstants.LOGIN_URL;
import static au.com.avmaint.api.access.SecurityConstants.SIGN_UP_URL;

/**
 * Annotated this class with @EnableWebSecurity and made it extend WebSecurityConfigurerAdapter
 * to take advantage of the default web security configuration provided by Spring Security.
 * This allows us to fine-tune the framework to our needs.
 * <p>
 *     Set at Order 1 to beat spring's own ResourceServerConfiguration (set at 3)
 * </p>
 */
@EnableWebSecurity
@Order(-200000)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * define which resources are public and which are secured. In our case, we set the SIGN_UP_URL endpoint
     * as being public and everything else as being secured. We also configure CORS (Cross-Origin Resource Sharing)
     * support through http.cors() and we add a custom security filter in the Spring Security filter chain.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(SIGN_UP_URL).permitAll()
                .antMatchers(LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthNFilter(authenticationManager()))
                .addFilter(new AuthZFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * defined a custom implementation of UserDetailsService to load user-specific data in the security framework.
     * We have also used this method to set the encrypt method used by our application (BCryptPasswordEncoder).
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * allow/restrict our CORS support. In our case we left it wide open by permitting requests from any source (/**).
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * Overriding it here
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
              return super.authenticationManagerBean();
    }
}