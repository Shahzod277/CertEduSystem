//package uz.raqamli_talim.certedusystem.service;
//
//import lombok.RequiredArgsConstructor;
//import org.antlr.v4.runtime.misc.Utils;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;
//
//import java.security.Principal;
//import java.time.LocalDateTime;
//import java.util.*;
//
//
//
//@Service
//@RequiredArgsConstructor
//public class AdminService {
//    private final UserRepository userRepository;
//    private final UniversityRepository universityRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//    private final FileService fileService;
//    private final Utils utils;
////    private final OrganizationRepository organizationRepository;
//
//    @Transactional
//    public ResponseDto createAdmin(AdminRequest request) {
////        try {
//        Optional<User> userOptional = userRepository.findByPinfl(request.getPinfl());
//        if (userOptional.isEmpty()) {
//            if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
//                return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Bu telefon raqami boshqa foydalanuvchiga biriktirilgan", LocalDateTime.now());
//            }
//            User user = new User();
//
//            Optional<Organization> organizationOptional = organizationRepository.findById(request.getOrganizationId());
//            if (organizationOptional.isEmpty()) {
//                return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "OTM topilmadi", LocalDateTime.now());
//            }
//            user.setOrganization(organizationOptional.get());
//
//            user.setPhoto(fileService.decodeToImage(request.getPhoto()));
//            user.setFirstName(request.getFirstName());
//            user.setLastName(request.getLastName());
//            user.setPhoneNumber(request.getPhoneNumber());
//            user.setFatherName(request.getFatherName());
//            user.setPinfl(request.getPinfl());
//            Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoleIds()));
//            Role role = roles.stream().iterator().next();
//            user.setFullName(request.getLastName() + " " + request.getFirstName() + " " + request.getFatherName());
//            user.setCurrentRoleId(role.getId());
//            user.setCurrentRoleName(role.getName());
//            user.setSerialAndNumber(request.getSerialAndNumber());
//            user.setBirthDate(request.getBirthDate());
//            user.setGivenDate(request.getGivenDate());
//            user.setRoles(roles);
//            user.setPassword(passwordEncoder.encode(user.getPinfl() + user.getSerialAndNumber()));
//            userRepository.save(user);
//            return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY_SAVED.getMessage(), LocalDateTime.now());
//        }
//        return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ALREADY_EXISTS.getMessage(), LocalDateTime.now());
////        } catch (Exception e) {
////            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
////            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR_SAVED.getMessage(), LocalDateTime.now());
////        }
//    }
//
//
//    @Transactional
//    public ResponseDto changeIsActive(Principal principal, Integer id, Boolean isActive) {
//        try {
//            UserInfoProjection admin = userRepository.findActiveUserInfoByPinfl(principal.getName()).get();
//            Optional<User> userOptional = userRepository.findById(id);
//            if (userOptional.isPresent()) {
//                if (userOptional.get().getPinfl().equals(admin.getPinfl())) {
//                    return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "O'zingizning statusingizni o'zgartira olmaysiz", LocalDateTime.now());
//                }
//                userOptional.get().setIsActive(isActive);
//                userRepository.save(userOptional.get());
//                return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY.getMessage(), LocalDateTime.now());
//            }
//            return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), LocalDateTime.now());
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR_SAVED.getMessage(), LocalDateTime.now());
//        }
//    }
//
//    @Transactional
//    public ResponseDto updateAdmin(Integer id, AdminRequest request) {
//        try {
//            Optional<User> userOptional = userRepository.findById(id);
//            if (userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (!Objects.equals(user.getPhoneNumber(), request.getPhoneNumber()) &&
//                        userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
//                    return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Bu telefon raqami boshqa foydalanuvchiga tegishli", LocalDateTime.now());
//                } else if (request.getOrganizationId() != null) {
//                    Optional<Organization> organizationOptional = organizationRepository.findById(request.getOrganizationId());
//                    user.setOrganization(organizationOptional.get());
//                } else {
//                    user.setOrganization(null);
//                }
//
//
//                Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoleIds()));
//                Role role = roles.stream().iterator().next();
//                user.setFullName(request.getLastName() + " " + request.getFirstName() + " " + request.getFatherName());
//                user.setCurrentRoleId(role.getId());
//                user.setCurrentRoleName(role.getName());
//                user.setFirstName(request.getFirstName());
//                user.setLastName(request.getLastName());
//                user.setFatherName(request.getFatherName());
//                user.setSerialAndNumber(request.getSerialAndNumber());
//                user.setBirthDate(request.getBirthDate());
//                user.setPhoneNumber(request.getPhoneNumber());
//                user.setFullName(request.getLastName() + " " + request.getFirstName() + " " + request.getFatherName());
//                user.setRoles(roles);
//                user.setIsActive(request.getIsActive());
//                userRepository.save(userOptional.get());
//                return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY_UPDATE.getMessage(), LocalDateTime.now());
//            }
//            return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), LocalDateTime.now());
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR_SAVED.getMessage(), LocalDateTime.now());
//        }
//    }
//
//
//    @Transactional(readOnly = true)
//    public ResponseDto getAdminById(Integer id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (userOptional.isPresent()) {
//            List<Integer> roleIdList = userOptional.get().getRoles().stream().map(Role::getId).toList();
//            AdminResponse response = new AdminResponse(userOptional.get());
//            response.setRoleIds(roleIdList);
//            return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY.getMessage(), LocalDateTime.now(), response);
//        }
//        return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), LocalDateTime.now());
//    }
//
//    @Transactional
//    public ResponseDto updateCurrentRole(Principal principal, Integer roleId) {
//        try {
//            Optional<User> userOptional = userRepository.findActiveUserByPinfl(principal.getName());
//            if (userOptional.isPresent()) {
//                Role role = roleRepository.findById(roleId).get();
//                userOptional.get().setCurrentRoleId(role.getId());
//                userOptional.get().setCurrentRoleName(role.getName());
//                userRepository.save(userOptional.get());
//                return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY_UPDATE.getMessage(), LocalDateTime.now());
//            }
//            return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), LocalDateTime.now());
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR_SAVED.getMessage(), LocalDateTime.now());
//        }
//    }
//
//    public ResponseDto checkUserInfo(PinflAndSerialNumber request) {
//        try {
//            return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY.getMessage(), LocalDateTime.now(), utils.checkIIB(request));
//        } catch (RuntimeException ex) {
//            if (ex.getMessage().equals(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))) {
//                return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Davlat Personallashtirish markazidan ma'lumot olishda muammo bo'lmoqda", LocalDateTime.now());
//            } else if (ex.getMessage().equals(String.valueOf(HttpStatus.NOT_FOUND.value()))) {
//                return new ResponseDto(HttpStatus.NOT_FOUND.value(), "Bu fuqaroning pasport ma'lumotlari topilmadi", LocalDateTime.now());
//            } else
//                return new ResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR.getMessage(), LocalDateTime.now());
//        }
//    }
//    @Transactional(readOnly = true)
//    public Page<AdminResponse> getAllAdminsPage(int page, int size,
//                                                String search, Boolean isActive, Integer roleId,
//                                                Integer organizationId, Integer universityId) {
//        if (page > 0) page = page - 1;
//        PageRequest pageRequest = PageRequest.of(page, size);
//
//        return userRepository.getAllAdmins(organizationId, universityId, isActive, roleId, search, pageRequest)
//                .map(u -> new AdminResponse(
//                        u.getId(),
//                        u.getPinfl(),
//                        u.getPhoneNumber(),
//                        u.getFullName(),
//                        u.getIsActive(),
//                        null,
//                        u.getuniversityName(),
//                        u.getOrganizationName(),
//                        u.getCreateDate()
//                ));
//    }
//
//
//    @Transactional(readOnly = true)
//    public ResponseDto getAdminInfoResponse(Principal principal) {
//        Optional<User> optionalUser = userRepository.findAdminInfoByPinfl(principal.getName());
//        if (optionalUser.isPresent()) {
//            return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY.getMessage(), LocalDateTime.now(),
//                    optionalUser.map(AdminResponse::new));
//        } else
//            return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), LocalDateTime.now());
//    }
//}
