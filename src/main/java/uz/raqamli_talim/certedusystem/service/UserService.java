//package uz.raqamli_talim.certedusystem.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//import uz.raqamli_talim.certedusystem.model.JwtResponse;
//import uz.raqamli_talim.certedusystem.repository.RoleRepository;
//import uz.raqamli_talim.certedusystem.repository.UserRepository;
//import uz.raqamli_talim.certedusystem.security.JwtTokenProvider;
//import uz.raqamli_talim.certedusystem.utils.Utils;
//
//import java.net.URI;
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final Utils utils;
//    private final FileService fileService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenProvider jwtTokenProvider;
//
//
//    }
//}
