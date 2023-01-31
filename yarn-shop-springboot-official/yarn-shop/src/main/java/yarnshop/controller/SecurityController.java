package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import yarnshop.model.account.Account;
import yarnshop.payload.request.SignInForm;
import yarnshop.payload.response.JwtRespone;
import yarnshop.payload.response.MessageRespone;
import yarnshop.security.jwt.JwtProvider;
import yarnshop.security.user_detail.MyUserDetail;
import yarnshop.service.account.IAccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInForm signInForm) {

        Account accountValidate = accountService.findAccountByUserName(signInForm.getUsername());

        if (accountValidate == null) {
            return new ResponseEntity<>(new MessageRespone("Username not found"), HttpStatus.NOT_FOUND);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);

        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtRespone(token,
                myUserDetail.getAccount(),
                myUserDetail.getAuthorities()));
    }


}

