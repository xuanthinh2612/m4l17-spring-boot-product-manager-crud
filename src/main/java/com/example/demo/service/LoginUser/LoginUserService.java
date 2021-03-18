package com.example.demo.service.LoginUser;

import com.example.demo.model.LoginUser;
import com.example.demo.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserService implements ILoginUserService, UserDetailsService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Override
    public LoginUser getLoginUsersByUsername(String name) {
        return loginUserRepository.getLoginUsersByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = this.getLoginUsersByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(loginUser.getUserRole());
        UserDetails userDetails = new User(
                loginUser.getUsername(),
                loginUser.getPassword(),
                authorities
        );
        return userDetails;
    }
}
