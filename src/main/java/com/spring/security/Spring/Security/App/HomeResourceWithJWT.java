package com.spring.security.Spring.Security.App;

import com.spring.security.Spring.Security.App.models.AuthenticationRequest;
import com.spring.security.Spring.Security.App.models.AuthenticationResponse;
import com.spring.security.Spring.Security.App.service.MyUserDetailsService;
import com.spring.security.Spring.Security.App.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResourceWithJWT {

    //This class authenticates the username and password which we get it from AuthenticationRequest class.
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String home() {
        return ("<h>Welcome Home!</h>");
    }


    //this end point will take username and password as an input argument and it's gonna return JWT.
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        //this is how we are authenticating the username and password.
        //UsernamePasswordAuthenticationToken this is a standard token which spring MVC uses for username and password.
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        //In this step, the Authentication successful and now we need to get the userDetails from UserDetails Service.
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        //so now I have USerDetails
        //Then need to create JWT so, i can use JWT util class to get the JWT out of user details
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        //now i have the token and now i need to create a authentication response instance and pass it back.
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

