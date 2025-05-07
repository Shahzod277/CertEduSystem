package uz.raqamli_talim.certedusystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.NationalCertificateResponse;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.NationalCertificateService;
import uz.raqamli_talim.certedusystem.domain.Certificate;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.model.response.CertificateResponse;
import uz.raqamli_talim.certedusystem.repository.CertificateRepository;

import java.security.cert.CertificateExpiredException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static uz.raqamli_talim.certedusystem.enums.ResponseMessage.*;

@RequiredArgsConstructor
public class CertificateService {
    private final NationalCertificateService nationalCertificateService;
    private final CertificateRepository certificateRepository;

//    public ResponseDto getNationalCertificate(String pinfl) {
//        // Fetch the certificates based on the given pinfl
//        List<Certificate> certificates = certificateRepository.findAllByPinfl(pinfl);
//        if (certificates.isEmpty()) {
//         }
//        // Get the current date
//        LocalDate currentDate = LocalDate.now();
//
//        // Check if all certificates are expired
//        boolean allExpired = certificates.stream()
//                .allMatch(cert -> cert.getEndDate().isBefore(currentDate) || cert.getEndDate().isEqual(currentDate));
//
//        if (allExpired) {
//            return new ResponseDto(HttpStatus.NOT_FOUND.value(), NOT_FOUND.getMessage(), now());
//        }
//
//        // Map the certificates to CertificateResponse objects
//        List<CertificateResponse> certificateResponses = certificates.stream()
//                .map(CertificateResponse::new)
//                .collect(Collectors.toList());
//
//        // Return the ResponseDto with status code, message, timestamp, and list of certificates
//        return new ResponseDto(HttpStatus.FOUND.value(), SUCCESSFULLY.getMessage(), now(),certificateResponses);
//
//    }
}