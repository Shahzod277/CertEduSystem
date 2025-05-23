package uz.raqamli_talim.certedusystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.model.projection.CertificateProjection;
import uz.raqamli_talim.certedusystem.model.response.CertificateResponse;
import uz.raqamli_talim.certedusystem.service.CertificateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Certificate-controller", description = "ochiq apilar")
public class CertificateController {

    private final CertificateService certificateService;


    @GetMapping("national-certificate/{pinfl}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
            summary = "milliy sertifikatni olish")
    @PreAuthorize("hasAnyRole('INTEGRATION','ADMIN')")
    public ResponseEntity<?> getNationalCertificate(@PathVariable("pinfl") String pinfl) {
        ResponseDto response = certificateService.getNationalCertificate(pinfl);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("national-certificaties")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
            summary = "milliy sertifikatni olish")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<CertificateProjection> getCertificates(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                       @RequestParam(value = "search",required = false) String search) {
        ;
        return certificateService.getCertificates(page, size, search);
    }

    @GetMapping("nationalCertificate/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")},
            summary = "milliy sertifikatni olish")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CertificateResponse getCertificate(@PathVariable(value = "id") Integer id) {
        return certificateService.getCertificate(id);
    }


}
