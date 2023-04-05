package com.tienda_k;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    
   /*@Bean
   public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("juan")
                .password("{noop}123")
                .roles("USER","VENDEDOR", "ADMIN")
                .build();
        UserDetails sales = User.builder()
                .username("rebeca")
                .password("{noop}456")
                .roles("VENDEDOR","USER")
                .build();
        UserDetails user = User.builder()
                .username("pedro")
                .password("{noop}789")
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(user,sales, admin);
    }*/
   @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/",
                                "/index",
                                "/errores/**",
                                "/webjars/**").permitAll()
                        .requestMatchers(
                                "/articulo/nuevo",
                                "/articulo/guardar",
                                "/articulo/modificar/**",
                                "/articulo/eliminar/**",
                                "/categoria/nuevo",
                                "/categoria/guardar",
                                "/categoria/modificar/**",
                                "/categoria/eliminar/**",
                                "/cliente/nuevo",
                                "/cliente/guardar",
                                "/cliente/modificar/**",
                                "/cliente/eliminar/**"
                                ).hasRole("ADMIN")
                        .requestMatchers(
                                "/articulo/listado",
                                "/categoria/listado",
                                "/cliente/listado"
                        ).hasAnyRole("ADMIN","VENDEDOR")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll())
                .exceptionHandling()
                .accessDeniedPage("/errores/403");
        return http.build();
    }
    
}
 