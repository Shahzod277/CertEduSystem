package uz.raqamli_talim.certedusystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import uz.raqamli_talim.certedusystem.api_integration.LoginRequest;
import uz.raqamli_talim.certedusystem.domain.Role;
import uz.raqamli_talim.certedusystem.domain.User;
import uz.raqamli_talim.certedusystem.enums.ResponseMessage;
import uz.raqamli_talim.certedusystem.model.JwtResponse;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.repository.RoleRepository;
import uz.raqamli_talim.certedusystem.repository.UserRepository;
import uz.raqamli_talim.certedusystem.security.JwtTokenProvider;
import uz.raqamli_talim.certedusystem.security.UserDetailsImpl;
import uz.raqamli_talim.certedusystem.utils.Utils;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final Utils utils;
    private final FileService fileService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    private Role getUserRole(String inputRole) {
        Optional<Role> role = roleRepository.findByName(inputRole);
        return role.orElseGet(() -> roleRepository.save(new Role(inputRole)));
    }

    @Transactional
    public ResponseDto signIn(LoginRequest request) {
        try {
            Optional<User> userByPinfl = userRepository.findActiveUserByPinfl(request.getUsername());
            if (userByPinfl.isEmpty()) {
                return new ResponseDto(HttpStatus.NOT_FOUND.value(), ResponseMessage.NOT_FOUND.getMessage(), LocalDateTime.now());
            }
            User user = userByPinfl.get();
            boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
            if (!matches) {
                return new ResponseDto(HttpStatus.UNAUTHORIZED.value(), "Noto'g'ri parol", LocalDateTime.now());
            }
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
            String jwtToken = jwtTokenProvider.generateJWTToken(userDetails);

            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setJwtToken(jwtToken);
            jwtResponse.setUsername(user.getPinfl());
            jwtResponse.setRoles(user.getRoles().stream().map(Role::getName).toList());

            return new ResponseDto(HttpStatus.OK.value(), ResponseMessage.SUCCESSFULLY.getMessage(), LocalDateTime.now(), jwtResponse);
        } catch (Exception ex) {
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ResponseMessage.ERROR.getMessage(), LocalDateTime.now());
        }
    }
}
