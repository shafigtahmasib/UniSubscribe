package com.example.unsubscribe.controllers;

import com.example.unsubscribe.dto.AppUserDTO;
import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.exceptions.UserAlreadyExistException;
import com.example.unsubscribe.exceptions.WrongFormatException;
import com.example.unsubscribe.models.Subscription;
import com.example.unsubscribe.services.UserService;
import com.example.unsubscribe.utils.JwtRequest;
import com.example.unsubscribe.utils.JwtResponse;
import com.example.unsubscribe.utils.JwtTokenUtil;
import com.example.unsubscribe.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<AppUserDTO> registration(@RequestBody AppUserDTO appUserDTO) throws UserAlreadyExistException, WrongFormatException {
        return new ResponseEntity(userService.save(appUserDTO), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}