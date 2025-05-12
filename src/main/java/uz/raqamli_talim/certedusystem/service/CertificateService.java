package uz.raqamli_talim.certedusystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.CertificateData;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.NationalCertificateResponse;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate.NationalCertificateService;
import uz.raqamli_talim.certedusystem.domain.Certificate;
import uz.raqamli_talim.certedusystem.domain.CertificateType;
import uz.raqamli_talim.certedusystem.enums.ResponseMessage;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.model.projection.CertificateProjection;
import uz.raqamli_talim.certedusystem.model.response.CertificateResponse;
import uz.raqamli_talim.certedusystem.repository.CertificateRepository;
import uz.raqamli_talim.certedusystem.repository.CertificateTypeRepository;

import java.security.Principal;
import java.security.cert.CertificateExpiredException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CertificateService {
    private final NationalCertificateService nationalCertificateService;
    private final CertificateRepository certificateRepository;
    private final CertificateTypeRepository certificateTypeRepository;

    public ResponseDto getNationalCertificate(String pinfl) {
        CertificateType certificateType = certificateTypeRepository.findById(2L).orElse(null);
        if (certificateType == null) {
            return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "CertificateType topilmadi", LocalDateTime.now());
        }

        List<Certificate> certificates = certificateRepository.findAllByPinfl(pinfl);

        // Bazadagi sertifikatlar orasidan muddati o'tmaganlarini filtrlaymiz
        List<Certificate> validCertificates = certificates.stream()
                .filter(cert -> cert.getEndDate().isAfter(LocalDate.now()))
                .toList();

        if (validCertificates.isEmpty()) {
            // Tashqi xizmatdan sertifikatlarni olishga harakat qilamiz
            List<NationalCertificateResponse> externalCertList = nationalCertificateService.getCertificate(pinfl);
            if (externalCertList == null || externalCertList.isEmpty()) {
                return new ResponseDto(HttpStatus.NOT_FOUND.value(), "Sertifikat topilmadi", LocalDateTime.now());
            }

            NationalCertificateResponse externalCert = externalCertList.get(0);
            if (externalCert.getData() == null || externalCert.getData().isEmpty()) {
                return new ResponseDto(HttpStatus.NOT_FOUND.value(), "Sertifikat topilmadi", LocalDateTime.now());
            }

            // Tashqi xizmatdan olingan sertifikatlar orasidan muddati o'tmaganlarini filtrlaymiz
            List<Certificate> newCertificates = externalCert.getData().stream()
                    .filter(data -> {
                        LocalDate endDate = LocalDate.parse(data.getEnd_date());
                        return endDate.isAfter(LocalDate.now());
                    })
                    .map(data -> buildCertificateFromData(pinfl, data, certificateType))
                    .collect(Collectors.toList());

            if (newCertificates.isEmpty()) {
                return new ResponseDto(HttpStatus.NOT_FOUND.value(), "Sertifikatni muddati o'tgan", LocalDateTime.now());
            }

            certificateRepository.saveAll(newCertificates);

            List<CertificateResponse> certificateResponses = newCertificates.stream()
                    .map(CertificateResponse::new)
                    .collect(Collectors.toList());

            return new ResponseDto(HttpStatus.OK.value(), ResponseMessage.SUCCESSFULLY.getMessage(), LocalDateTime.now(), certificateResponses);
        }

        List<CertificateResponse> certificateResponses = validCertificates.stream()
                .map(CertificateResponse::new)
                .collect(Collectors.toList());

        return new ResponseDto(HttpStatus.OK.value(), ResponseMessage.SUCCESSFULLY.getMessage(), LocalDateTime.now(), certificateResponses);
    }


    private Certificate buildCertificateFromData(String pinfl, CertificateData data, CertificateType certificateType) {
        Certificate certificate = new Certificate();
        certificate.setPinfl(pinfl);
        certificate.setFirtName(data.getFname());
        certificate.setCertificateType(certificateType);
        certificate.setLastName(data.getLname());
        certificate.setFatherName(data.getMname());
        certificate.setUrl(data.getHash());
        certificate.setSubject(data.getSubject());
        certificate.setSubject_id(data.getSubject_id());
        certificate.setStartDate(LocalDate.parse(data.getStart_date()));
        certificate.setCertNumber(data.getSer_num());
        certificate.setSerialNumber(data.getPsser() + data.getPsnum());
        certificate.setBall(data.getBall());
        certificate.setLevel(data.getLevel());
        certificate.setEndDate(LocalDate.parse(data.getEnd_date()));
        return certificate;
    }

    public Page<CertificateProjection> getCertificates(Integer page, Integer size, String search) {
        if (page > 0) page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, size);
        return certificateRepository.getCertificates(search, pageRequest);
    }

    public CertificateResponse getCertificate(Integer id) {
        Certificate certificate = certificateRepository.findById(id).get();
        return new CertificateResponse(certificate);
    }


}