package uz.raqamli_talim.certedusystem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.raqamli_talim.certedusystem.domain.User;
import uz.raqamli_talim.certedusystem.enums.ResponseMessage;
import uz.raqamli_talim.certedusystem.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByPinflForSignIn(username);
        return user.map(UserDetailsImpl::build).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.NOT_FOUND.getMessage()));

    }
}
