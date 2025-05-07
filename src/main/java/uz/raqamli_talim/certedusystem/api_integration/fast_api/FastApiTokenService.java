package uz.raqamli_talim.certedusystem.api_integration.fast_api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.raqamli_talim.certedusystem.domain.TokenEntity;
import uz.raqamli_talim.certedusystem.repository.TokenEntityRepository;

import java.util.Optional;

import static uz.raqamli_talim.certedusystem.enums.ApiConstant.FAST_API_PASSWORD;
import static uz.raqamli_talim.certedusystem.enums.ApiConstant.FAST_API_USERNAME;

@Service
@RequiredArgsConstructor
public class FastApiTokenService {

    private final WebClient webClient;
    private final TokenEntityRepository tokenEntityRepository;


    public FastApiTokenResponse getTokenGTCP() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", FAST_API_USERNAME.getValue());
        formData.add("password", FAST_API_PASSWORD.getValue());
        return webClient.post()
                .uri("http://172.18.9.171/auth/token")
                .body(BodyInserters.fromFormData(formData))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(FastApiTokenResponse.class)
                .block();
    }


    public String getToken() {
        Optional<TokenEntity> tokenEntityOptional = tokenEntityRepository.findByOrgName("fast-api");
        if (tokenEntityOptional.isPresent()) {
            TokenEntity tokenEntity = tokenEntityOptional.get();
            if (tokenEntity.getEndTime() < System.currentTimeMillis()) {
                FastApiTokenResponse token = getTokenGTCP();
                assert token.getAccessToken() != null;
                tokenEntity.setToken(token.getAccessToken());
                tokenEntity.setEndTime(System.currentTimeMillis() + token.getAccessTokenExpires() * 1000L);
                tokenEntityRepository.save(tokenEntity);
                return token.getAccessToken();
            } else {
                return tokenEntityOptional.get().getToken();
            }
        }
        FastApiTokenResponse token = getTokenGTCP();
        assert token.getAccessToken() != null;
        TokenEntity tokenEntityNew = new TokenEntity(System.currentTimeMillis() + token.getAccessTokenExpires() * 1000L, "fast-api", token.getAccessToken());
        TokenEntity saveTokenEntity = tokenEntityRepository.save(tokenEntityNew);
        return saveTokenEntity.getToken();
    }

}
