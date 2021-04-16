package com.project.shop.config.security;

import com.project.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
        this.userRepository = userRepository;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Value("${springdoc.api-docs.path}")
    private String restApiDocPath;
    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // All endpoints
            "/api/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(AUTH_WHITELIST)
//                .permitAll().anyRequest().authenticated();
        // proper configuration below
        //enable CORS and disable CSRF
        http.cors().and().csrf().disable();

        //set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        //set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, e) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    e.getMessage()
                            );
                        }
                ).and();

        //set permissions on endpoints
        http.authorizeRequests()
                //swagger endpoints
                .antMatchers("/").permitAll()
                .antMatchers(format("%s/**", restApiDocPath)).permitAll()
                .antMatchers(format("%s/**", swaggerPath)).permitAll()
                //public endpoints
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/product/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/category/search/**").permitAll()
                //private endpoints
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEmail -> userRepository.findByEmail(userEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User: %s, not found", userEmail)
                        )
                ));
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
