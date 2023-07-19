package com.projet.banque.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.projet.banque.entities.Employe;
import com.projet.banque.repositories.EmployeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserEmployeDetailsService implements UserDetailsService {
    @Autowired
    private EmployeRepository employeRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserEmployeDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
//        Employe user = employeRepository.findByMatricule(matricule);
//
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(user.getMatricule(),
//                    user.getPassword(),
//                    mapRolesToAuthorities(user.getRole()));
//        }else{
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }

        Optional<Employe> employe = employeRepository.findByMatricule(matricule);
        return employe.map(EmployeDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + matricule));

//        logger.debug("Loading Employe by username: {}", matricule);
//        Employe employe =  employeRepository.findByMatricule(matricule);
//        if (employe == null){
//            logger.warn("Employe not found: {}", matricule);
//            throw new UsernameNotFoundException("Employe Not Found");
//        }
//        logger.debug("Employe found: {}", matricule);
//
//        return  new EmployeDetails(employe);

    }

//    public Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
//        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getId()))
//                .collect(Collectors.toList());
//        return mapRoles;
//    }
}
