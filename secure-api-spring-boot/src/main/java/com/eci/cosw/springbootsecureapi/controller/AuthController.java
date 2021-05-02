package com.eci.cosw.springbootsecureapi.controller;

import com.eci.cosw.springbootsecureapi.model.User;
import com.eci.cosw.springbootsecureapi.service.UserService;
import com.eci.cosw.springbootsecureapi.utils.StringUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class AuthController
{

    private final UserService userService;

    @Autowired
    public AuthController( UserService userService )
    {
        this.userService = userService;
    }

    @CrossOrigin( origins = "*" )
    @RequestMapping( value = "/auth", method = RequestMethod.POST )
    public  ResponseEntity<?> login( @RequestBody User login ){
        try{
            String jwtToken;

            if ( login.getEmail() == null || login.getPassword() == null )
            {
                throw new ServletException( "Please fill in email and password" );
            }

            String email = login.getEmail();
            String password = login.getPassword();

            User user = userService.getUser( email );

            if ( user == null )
            {
                throw new ServletException( "Invalid email" );
            }

            String passwordHash = user.getPassword();

            if ( !StringUtils.isPasswordValid( password, passwordHash ) )
            {
                throw new ServletException( "Invalid login. Please check your name and password." );
            }

            jwtToken = Jwts.builder().setSubject( email ).claim( "role", user.getRole().toString() ).setIssuedAt(
                new Date() ).signWith( SignatureAlgorithm.HS256, "secretkey" ).compact();

            return new ResponseEntity<>( new Token( jwtToken ), HttpStatus.ACCEPTED);
        }catch( ServletException e ){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } 
    }

    public class Token
    {

        String access_token;

        Token( String access_token )
        {
            this.access_token = access_token;
        }

        public String getAccessToken()
        {
            return access_token;
        }

        public void setAccessToken( String access_token )
        {
            this.access_token = access_token;
        }
    }

}
