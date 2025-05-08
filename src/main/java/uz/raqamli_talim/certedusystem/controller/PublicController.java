package uz.raqamli_talim.certedusystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.raqamli_talim.certedusystem.api_integration.LoginRequest;
import uz.raqamli_talim.certedusystem.enums.ResponseMessage;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.service.FileService;
import uz.raqamli_talim.certedusystem.service.UserService;
import uz.raqamli_talim.certedusystem.utils.Utils;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/public")
@Tag(name = "public-controller", description = "ochiq apilar")

@RequiredArgsConstructor
public class PublicController {

//    private final OneIdServiceApiUser oneIdServiceApiUser;
    private final UserService userService;
    private final FileService fileService;
    private final Utils utils;
//    private final ClassificatorService classificatorService;

//    @GetMapping("oneIdAdmin")
//    public ResponseEntity<?> getOneIdAdmin() {
//        URI uri = userService.redirectOneIdUrlAdmin();
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .location(uri)
//                .build();
//    }
//
////    @GetMapping("oneIdUser")
////    public ResponseEntity<?> getOneIdUser() {
////        URI uri = userService.redirectOneIdUrlUser();
////        return ResponseEntity.status(HttpStatus.FOUND)
////                .location(uri)
////                .build();
////    }
//
//    @GetMapping("auth/**") // OneId orqali kirish va token olish
//    public ResponseEntity<?> oneIdSignIn(@RequestParam(value = "code") String code) {
//        OneIdTokenResponse accessAndRefreshToken = oneIdServiceApiUser.getAccessAndRefreshToken(code);
//        if (accessAndRefreshToken == null) {
//            return ResponseEntity.status(404).body("OneId Token" + ResponseMessage.NOT_FOUND.getMessage());
//        }
//        OneIdResponseUserInfo userInfo = oneIdServiceApiUser.getUserInfo(accessAndRefreshToken.getAccess_token());
//        if (userInfo == null) {
//            return ResponseEntity.status(404).body("OneId User" + ResponseMessage.NOT_FOUND.getMessage());
//        }
//        JwtResponse jwtResponse = userService.oneIdUser(userInfo);
//        if (jwtResponse == null) {
//            return ResponseEntity.status(400).body(ResponseMessage.ERROR.getMessage());
//        }
//        String redirectUrl = "https://testDomain/auth/oneId?token=" + jwtResponse.getJwtToken() + "&firstName="
//                + jwtResponse.getFirstName() + "&lastName=" + jwtResponse.getLastName();
//        URI uri = URI.create(redirectUrl);
//        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
//    }
//
//    @GetMapping("authAdmin/**")
//    public ResponseEntity<?> oneIdAdminSignIn(@RequestParam(value = "code") String code) {
//        JwtResponse jwtResponse = userService.oneIdAdminSignIn(code);
//        if (jwtResponse.getJwtToken() == null) {
//            URI uri = URI.create("https://testDomain/" + "notFound");
//            return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
//        }
//        String redirectUrl = "https://testDomain/auth/oneId?token=" + jwtResponse.getJwtToken();
//        URI uri = URI.create(redirectUrl);
//        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
//    }
//
    @PostMapping("signIn")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
        ResponseDto response = userService.signIn(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
//
//    @PostMapping(value = "uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//        return ResponseEntity.ok(fileService.upload(file));
//    }
//
//    @DeleteMapping(value = "deleteFile")
//    public ResponseEntity<?> deleteFile(@RequestParam("fileName") String fileName) {
//        ResponseDto response = fileService.deleteFile(fileName);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
//    @GetMapping(value = "organizations")
//    public ResponseEntity<?> getOrganization() {
//        List<Organization> organization = classificatorService.getOrganization();
//        return ResponseEntity.ok(organization);
//    }
//
//    @GetMapping(value = "universities")
//    public ResponseEntity<?> getUniversities() {
//        List<Organization> organization = classificatorService.getOrganization();
//        return ResponseEntity.ok(organization);
//    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        try {
            Resource resource = fileService.download(fileName, utils.getFileLocation());
            HttpHeaders headers = new HttpHeaders();
            if (Objects.requireNonNull(resource.getFilename()).endsWith("pdf") || Objects.requireNonNull(resource.getFilename()).endsWith("PDF")) {
                headers.setContentType(MediaType.parseMediaType("application/pdf"));
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                headers.add("content-disposition", "inline; filename=" + resource.getFilename());
                return ResponseEntity.status(resource.exists() ? 200 : 404)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers)
                        .body(resource);
            }
            if (Objects.requireNonNull(resource.getFilename()).endsWith("png") || Objects.requireNonNull(resource.getFilename()).endsWith("jpg")) {
                headers.add("content-disposition", "inline; filename=" + resource.getFilename());
                int i = Objects.requireNonNull(resource.getFilename()).lastIndexOf(".");
                String substring = resource.getFilename().substring(i + 1);
                return ResponseEntity
                        .status(resource.exists() ? 200 : 404)
                        .contentType(MediaType.parseMediaType("image/" + substring))
                        .headers(headers).body(resource);
            }
            headers.add("content-disposition", "inline; filename=" + resource.getFilename());
            return ResponseEntity.status(resource.exists() ? 200 : 404).contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST.value(), ResponseMessage.ERROR.getMessage(), LocalDateTime.now()));
        }
    }

    @GetMapping("downloadIIBPhoto/{fileName}")
    public ResponseEntity<?> downloadIIBPhoto(@PathVariable String fileName) {
        Resource resource = fileService.download(fileName, utils.getIibLocation());
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline; filename=" + resource.getFilename());
        return ResponseEntity.status(resource.exists() ? 200 : 404).contentType(MediaType.IMAGE_PNG)
                .headers(headers)
                .body(resource);
    }
}
