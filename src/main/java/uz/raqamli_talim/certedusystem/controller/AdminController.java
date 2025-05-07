//package uz.raqamli_talim.certedusystem.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.PinflAndSerialNumber;
//import uz.raqamli_talim.certedusystem.model.ResponseDto;
//import uz.raqamli_talim.certedusystem.model.request.AdminRequest;
//import uz.raqamli_talim.certedusystem.service.AdminService;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/api/admin")
//@RequiredArgsConstructor
//@Tag(name = "admin-controller", description = "otm-admin ga oid apilar")
//public class AdminController {
//    private final AdminService adminService;
//
//    @PostMapping("getPersonInfo")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "fuqaro passport ma'lumotlarini olish")
//    @PreAuthorize("hasAnyRole('SUPERADMIN')")
//    public ResponseEntity<?> checkUserInfo(@RequestBody PinflAndSerialNumber request) {
//        ResponseDto response = adminService.checkUserInfo(request);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @PostMapping()
////    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
////            summary = "yangi admin yaratish")
////    @PreAuthorize("hasAnyRole('SUPERADMIN')")
//    public ResponseEntity<?> createAdmin(@RequestBody AdminRequest request) {
//        ResponseDto response = adminService.createAdmin(request);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @PutMapping("status")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "Admin holatini o'zgartirish", description = "true - active, false - passiv")
//    @PreAuthorize("hasRole('SUPERADMIN')")
//    public ResponseEntity<?> changeIsActive(Principal principal,
//                                            @RequestParam(value = "id") Integer id,
//                                            @RequestParam(value = "isActive") Boolean isActive) {
//        ResponseDto response = adminService.changeIsActive(principal, id, isActive);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @PutMapping("{id}")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "adminni tahrirlash")
//    @PreAuthorize("hasAnyRole('SUPERADMIN')")
//    public ResponseEntity<?> updateOTMAdmin(@PathVariable("id") Integer id,
//                                            @RequestBody AdminRequest request) {
//        ResponseDto response = adminService.updateAdmin(id, request);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//
//    @GetMapping("{id}")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
//    @PreAuthorize("hasRole('SUPERADMIN')")
//    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
//        ResponseDto response = adminService.getAdminById(id);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @GetMapping("admins")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "adminlarni page shaklida olish")
//    @PreAuthorize("hasRole('SUPERADMIN')")
//    public ResponseEntity<Page<AdminResponse>> getAllAdminsPage(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "search", required = false) String search,
//            @RequestParam(value = "isActive", required = false) Boolean isActive,
//            @RequestParam(value = "roleId", required = false) Integer roleId,
//            @RequestParam(value = "organizationId", required = false) Integer organizationId,
//            @RequestParam(value = "universityId", required = false) Integer universityId) {
//        return ResponseEntity.ok(adminService.getAllAdminsPage(page, size, search, isActive, roleId, organizationId, universityId));
//    }
//
//    @PutMapping("currentRole/{roleId}")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "rolni almashtirish")
//    @PreAuthorize("hasAnyRole('SUPERADMIN','OTMADMIN')")
//    public ResponseEntity<?> updateCurrentRole(@PathVariable(value = "roleId") Integer roleId,
//                                               Principal principal) {
//        ResponseDto response = adminService.updateCurrentRole(principal, roleId);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @GetMapping("info")
//    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
//            summary = "admin ma'lumotlarini olish uchun api")
//    @PreAuthorize("isAuthenticated()") // Faqat autentifikatsiya qilingan foydalanuvchilar uchun
//    public ResponseEntity<?> getAdminInfoResponse(Principal principal) {
//        ResponseDto response = adminService.getAdminInfoResponse(principal);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//}
