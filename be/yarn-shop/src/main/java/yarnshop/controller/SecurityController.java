package com.example.demo.controller.security;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.payload.reponse.JwtRespone;
import com.example.demo.payload.reponse.MessageRespone;
import com.example.demo.payload.request.LoginForm;
import com.example.demo.payload.request.SignUpForm;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.user_detail.MyUserDetail;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class SecurityController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRoleService userRoleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginForm loginForm,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.NOT_ACCEPTABLE);
        }

        User userValidate = userService.findByUsername(loginForm.getUsername()).get();

        if (userValidate == null) {
            return new ResponseEntity<>(new MessageRespone("username not found"), HttpStatus.NOT_FOUND);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);

        return new ResponseEntity<>(new JwtRespone(token, myUserDetail.getAuthorities(), myUserDetail.getUser()), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Validated @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new MessageRespone("The username existed! Please try again"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new MessageRespone("The email existed! Please try again"), HttpStatus.OK);
        }

        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(signUpForm, user);
        userService.save(user);
        userRoleService.save(user.getId(), 2);
        return new ResponseEntity<>(new MessageRespone("Create user success!"), HttpStatus.OK);
    }
}
