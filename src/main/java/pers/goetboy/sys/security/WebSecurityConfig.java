package pers.goetboy.sys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pers.goetboy.sys.security.handler.EntryPointUnauthorizedHandler;
import pers.goetboy.sys.security.handler.RestAccessDeniedHandler;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author goetb
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(@Qualifier("jwtUserDetail") UserDetailsService userDetailsService, JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter, EntryPointUnauthorizedHandler entryPointUnauthorizedHandler, RestAccessDeniedHandler restAccessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //跨域支持
        httpSecurity.cors();
        //关闭csrf
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/druid/**").permitAll()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends  FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(mySecurityMetadataSource());
                        object.setAccessDecisionManager(myAccessDecisionManager());
                        return object;
                    }


                })
                .and().headers().cacheControl();
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler)
                .accessDeniedHandler(restAccessDeniedHandler);

  /*      //headers设置
        httpSecurity.headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(new Header("Access-control-Allow-Origin", "*"),
                //使ajax请求能够取到header中的jwt token信息
                new Header("Access-Control-Expose-Headers", "Authorization")

        )));*/

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTION"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("Authorization");
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsFilter(corsConfigurationSource);
    }

    @Bean
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        return new MyFilterInvocationSecurityMetadataSource();

    }

    @Bean
    public MyAccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();

    }
}
