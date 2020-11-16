package com.bs23.taco.service;

import com.bs23.taco.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserRepoUserDetailsService implements UserDetailsService {

  private UserRepository userRepo;

  @Autowired
  UserRepoUserDetailsService(UserRepository userRepo){
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return Optional
        .ofNullable(userRepo.findByUsername(username))
        .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
  }
}
