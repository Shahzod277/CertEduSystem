package uz.raqamli_talim.certedusystem.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.FastApiService;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.PinflAndSerialNumber;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.docrest.PersonData;
import uz.raqamli_talim.certedusystem.api_integration.fast_api.gtcp.docrest.PersonDataResponse;
import uz.raqamli_talim.certedusystem.domain.User;
import uz.raqamli_talim.certedusystem.model.JwtResponse;
import uz.raqamli_talim.certedusystem.model.PersonInfo;
import uz.raqamli_talim.certedusystem.security.JwtTokenProvider;
import uz.raqamli_talim.certedusystem.security.UserDetailsImpl;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
@Service
@Slf4j
public class Utils {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final FastApiService fastApiService;
    @Getter
    @Value("${file.storage.location}")
    private Path fileLocation;

    @Getter
    @Value("${file.iib_storage.iib_location}")
    private Path iibLocation;

    private String getCurrentUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/public/download/")
                .path(fileName).toUriString();
    }
    public PersonInfo checkIIB(PinflAndSerialNumber request) {
        try {
            PersonDataResponse iibUser = fastApiService.getIibUser(request);
            if (iibUser.getData() == null) {
                throw new RuntimeException(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
            PersonData item = iibUser.getData();
            PersonInfo data = new PersonInfo();
            item.getDocuments().forEach(documentsItem -> {
                if (documentsItem.getDocument().equals(item.getCurrentDocument())) {
                    data.setGivenDate(documentsItem.getDatebegin());
                    data.setPassportExpireDate(documentsItem.getDateend());
                }
            });
            data.setGender(String.valueOf(item.getSex()));
            data.setCitizenship(item.getCitizenship());
            data.setNationality(item.getNationality());
            data.setPhoto(item.getPhoto());
            data.setPinfl(item.getCurrentPinpp());
            data.setSerialAndNumber(item.getCurrentDocument());
            data.setFirstName(item.getNamelat());
            data.setLastName(item.getSurnamelat());
            data.setFatherName(item.getPatronymlat());
            data.setBirthDate(item.getBirthDate());
            return data;
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    public JwtResponse getJwtResponse(User user) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getPinfl(), user.getPinfl() + user.getSerialAndNumber()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        String jwtToken = jwtTokenProvider.generateJWTToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return new JwtResponse(
                user.getPinfl(),
                jwtToken,
                roles);
    }
}
