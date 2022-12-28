package com.prueba.AmigoInvisible.service.implement;

import com.prueba.AmigoInvisible.constants.Messages;
import com.prueba.AmigoInvisible.entity.MyUserDetails;
import com.prueba.AmigoInvisible.entity.User;
import com.prueba.AmigoInvisible.repository.UserRepository;
import com.prueba.AmigoInvisible.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(Messages.USER_NOT_FOUND));
        return new MyUserDetails(user);
    }
}
