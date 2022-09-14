package com.atcs.product.controller;

import com.atcs.product.config.JwtUtil;
import com.atcs.product.exception.ApiRequestException;
import com.atcs.product.exception.UserNotFoundException;
import com.atcs.product.model.JwtRequest;
import com.atcs.product.model.JwtResponce;
import com.atcs.product.model.User;
import com.atcs.product.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?>generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try
        {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }
        catch (UserNotFoundException e)
        {
            e.printStackTrace();
            throw new UserNotFoundException("User Not Found");
        }

        UserDetails user = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtils.generateToken(user);

        return ResponseEntity.ok(new JwtResponce(token));

    }



    private void authenticate(String username , String Password) throws Exception {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, Password));
        } catch (DisabledException e) {
            // TODO: handle exception
            throw new ApiRequestException("USER Disabled ");
        }
        catch(BadCredentialsException e) {
            throw new ApiRequestException("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    // principal gives the current logged in user details
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User)this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
