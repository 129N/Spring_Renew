package org.mik.yftwrg.Controller.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Service.Implement.AppUserDetailsServiceImpl;
import org.mik.yftwrg.Service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    private final AppUserDetailsServiceImpl appUserDetailsService;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpServletResponse response,
                        Model model){
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String jwt = jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());

//            // Optional: set JWT as cookie or header
//            response.setHeader("Authorization", "Bearer " + jwt);

            Cookie jwtCookie = new Cookie("jwt", jwt);
                //jwtCookie.setHttpOnly(true); // enable in production with HTTPS
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(24 * 60 * 60); // 1 day expiration
                response.addCookie(jwtCookie);


            // Check role and redirect
            boolean isAdmin = userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin) {
                return "redirect:/base";
            } else {
                return "redirect:/participant/list";
            }

        } catch (Exception e) {
            model.addAttribute("error", "Invalid login credentials.");
            return "index";
        }
    }


    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model){
        try{
            appUserDetailsService.RegisterNewUser(email, password, role);

            // Put the just‐registered credentials into the model
            model.addAttribute("registeredEmail", email);
            model.addAttribute("registeredPassword", password);

            // Stay on the same page and show the registration info
            return "index";
        } catch (Exception e){
            model.addAttribute("errorREG", "Registration failed: " + e.getMessage());
            return "index";  // return back to the form with error
        }

}


        //logout method


    @GetMapping("/signout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0); // delete cookie
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/index";  // Redirect to login page
    }

}

