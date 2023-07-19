package com.projet.banque.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Autowired
    private CustomUserEmployeDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/accueil").permitAll()
                        .requestMatchers("/dashboard").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/clients").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/compte").hasAnyAuthority("ChefAgence", "ChefGuichet")

                        .requestMatchers("/updateClient/**").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/deleteClient/**").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/deleteCompte/**").hasAnyAuthority("ChefAgence", "ChefGuichet")
                        .requestMatchers("/updateCompte/**").hasAnyAuthority("ChefAgence", "ChefGuichet")
                        .requestMatchers("/updateEmployee/**").hasAnyAuthority("ChefAgence")
                        .requestMatchers("/deleteEmployee/**").hasAnyAuthority("ChefAgence")

                        .requestMatchers("/operations").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/virement").hasAnyAuthority("ChefAgence", "ChefGuichet", "Caissier")
                        .requestMatchers("/employes").hasAnyAuthority("ChefAgence", "ChefGuichet")

                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/dashboard")
                                .failureUrl("/login-error")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/")
                                .permitAll()
                );
        return http.build();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        PasswordEncoder noOpPasswordEncoder = NoOpPasswordEncoder.getInstance();
        return noOpPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setUserDetailsPasswordService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider
//                = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//
//        return provider;
//    }

}
