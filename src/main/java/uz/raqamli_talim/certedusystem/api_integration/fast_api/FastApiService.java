package uz.raqamli_talim.certedusystem.api_integration.fast_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.PersonDataRequest;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.PinflAndSerialNumber;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.address.MIP2AddressResponse;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.docrest.PersonDataResponse;

@Service
@RequiredArgsConstructor
public class FastApiService {
    private final WebClient webClient;
    private final FastApiTokenService fastApiTokenService;

    public PersonDataResponse getIibUser(PinflAndSerialNumber dataRequest) {
        PersonDataRequest request = new PersonDataRequest(dataRequest);
        String token = fastApiTokenService.getToken();
        PersonDataResponse response = webClient.post()
                .uri("http://172.18.9.171/person/pinpp-and-document/")
                .headers(httpHeader -> httpHeader.setBearerAuth(token))
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatus.NO_CONTENT::equals, clientResponse -> Mono.empty())
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .onStatus(HttpStatus.BAD_REQUEST::equals, clientResponse -> Mono.empty())
                .bodyToMono(PersonDataResponse.class).block();
        if (response == null || response.getData() == null) {
            String day = request.getPinfl().substring(1, 3);
            String month = request.getPinfl().substring(3, 5);
            String year = request.getPinfl().substring(5, 7);
            if (Integer.parseInt(year) > 20) {
                year = 19 + year;
            } else {
                year = 20 + year;
            }
            request.setBirthDate(year + "-" + month + "-" + day);
            response = webClient.post()
                    .uri("http://172.18.9.171/person/pinpp-and-birth-date/")
                    .headers(httpHeader -> httpHeader.setBearerAuth(token))
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(HttpStatus.NO_CONTENT::equals, clientResponse -> Mono.error(new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND.value()))))
                    .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.error(new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND.value()))))
                    .onStatus(HttpStatus.BAD_REQUEST::equals, clientResponse -> Mono.error(new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND.value()))))
                    .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse -> Mono.error(new RuntimeException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))))
                    .bodyToMono(PersonDataResponse.class).block();
        }
        return response;
    }

    public MIP2AddressResponse getAddressByPinfl(String pinfl) {
        return this.webClient.post()
                .uri("http://172.18.9.171/person/person-address/")
                .headers(httpHeader -> httpHeader.setBearerAuth(fastApiTokenService.getToken()))
                .bodyValue(new PersonDataRequest(pinfl))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus.NO_CONTENT::equals, clientResponse -> Mono.empty())
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
                .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals, clientResponse -> Mono.empty())
                .bodyToMono(MIP2AddressResponse.class).block();
    }
}
