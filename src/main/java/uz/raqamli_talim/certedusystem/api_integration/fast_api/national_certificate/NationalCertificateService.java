package uz.raqamli_talim.certedusystem.api_integration.fast_api.national_certificate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.FastApiTokenResponse;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.FastApiTokenService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NationalCertificateService {

    private final WebClient webClient;
    private final FastApiTokenService fastApiTokenService;

    public List<NationalCertificateResponse> getCertificate(String pinfl) {
        FastApiTokenResponse token = fastApiTokenService.getTokenGTCP();
        return webClient.get()
                .uri("http://172.18.9.171/dtm/certificate-info?pinfl=" + pinfl)
                .headers(h -> h.setBearerAuth(token.getAccessToken()))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToFlux(NationalCertificateResponse.class)
                .collectList()
                .block();
    }
}
