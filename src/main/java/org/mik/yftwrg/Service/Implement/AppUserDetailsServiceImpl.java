package org.mik.yftwrg.Service.Implement;


import org.mik.yftwrg.Entity.AppUser;
import org.mik.yftwrg.Repositories.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.mik.yftwrg.ENUM.Role;

import java.util.List;

@Service

public  class AppUserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserDetailsServiceImpl(AppUserRepository appUserRepository,
                                     PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                appUser.getEmail(),
                appUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name()))

        );
    }


    public void RegisterNewUser(String email, String password, String role) throws Exception{

        if(appUserRepository.findByEmail(email).isPresent()){
            throw new Exception("Email already registered");
        }
        // Encode the password (you need PasswordEncoder bean injected)

        String encodedPassword = passwordEncoder.encode(password);

        AppUser newUser = new AppUser();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.valueOf(role.toUpperCase()));;


        appUserRepository.save(newUser);

    }

}
//    public void RegisterNewUser(String email, String password, String role) throws Exception{
//
//        if(appUserRepository.findByEmail(email).isPresent()){
//            throw new Exception("Email already registered");
//        }
//        // Encode the password (you need PasswordEncoder bean injected)
//
//        String encodedPassword = passwordEncoder.encode(password);
//
//        AppUser newUser = new AppUser();
//        newUser.setEmail(email);
//        newUser.setPassword(encodedPassword);
//        newUser.setRole(Role.valueOf(role));;
//
//
//        appUserRepository.save(newUser);
//
//    }