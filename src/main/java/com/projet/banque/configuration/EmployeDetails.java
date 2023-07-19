package com.projet.banque.configuration;

import com.projet.banque.entities.Employe;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeDetails implements UserDetails {

    private Employe employe;

    private String matricule;
    private String nomEmp;
    private String password;
    private List<GrantedAuthority> authorities;

    public EmployeDetails(Employe employe) {
        matricule = employe.getMatricule();
        nomEmp= employe.getNom() + ' ' + employe.getPrenom();
        password= employe.getPassword();
        authorities = Arrays.stream(employe.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
//        super();
//        this.employe = employe;
    }

    public String getMatricule() {
        return this.matricule;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String role = employe.getRole();
        return authorities;
//        return Collections.singleton(new SimpleGrantedAuthority(role));
    }


    @Override
    public String getPassword() {
//        return employe.getPassword();
        return password;
    }

    @Override
    public String getUsername() {
//        return employe.getNom();
        return nomEmp;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
