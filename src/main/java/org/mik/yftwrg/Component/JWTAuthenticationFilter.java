package org.mik.yftwrg.Component;



import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mik.yftwrg.Service.JWTService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;


//@Component
//@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(
            @Lazy
            JWTService jwtService,
            UserDetailsService userDetailsService)
    {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String JWT_COOKIE_NAME = "jwt";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {

        final String token = extractTokenFromRequest(request);

        if(token != null && jwtService.validateToken(token)){
            String email = jwtService.extractEmail(token);
            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(token);


                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities); //  userDetails.getAuthorities()

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }

        //check cookies
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(JWT_COOKIE_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    }

//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//
//       final String token = extractTokenFromRequest(request);
//        if (token == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String username = jwtService.extractUsername(token);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.isTokenValid(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//
//        }
//        filterChain.doFilter(request, response);
//    }
//
//        private String extractTokenFromRequest (HttpServletRequest request){
//            // Check Authorization header first
//            String authHeader = request.getHeader("Authorization");
//            if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
//                return authHeader.substring(7);
//            }
//
//            // Fallback to cookie
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("jwt")) {
//                        String jwt = cookie.getValue();
//                            if(!StringUtils.hasLength(jwt))
//                                return null;
//                        return jwtService.isTokenExpired(jwt) ? null : jwt;
//                    }
//                }
//            }
//
//            return null;
//        }