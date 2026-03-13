package com.rsm.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsm.users.User;
import com.rsm.users.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                );
        authManager.authenticate(authToken);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        User domainUser =
                userRepository.findByUsername(request.getUsername()).orElseThrow();

        Map<String, Object> claims = Map.of("role", domainUser.getRole());

        String jwt = jwtService.generateToken(userDetails, claims);

        return new AuthResponse(jwt, domainUser.getUsername(), domainUser.getRole());
    }
}
