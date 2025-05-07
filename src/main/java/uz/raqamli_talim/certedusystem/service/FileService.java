package uz.raqamli_talim.certedusystem.service;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.raqamli_talim.certedusystem.model.ResponseDto;
import uz.raqamli_talim.certedusystem.model.response.FileResponse;
import uz.raqamli_talim.certedusystem.utils.Utils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static uz.raqamli_talim.certedusystem.enums.ResponseMessage.SUCCESSFULLY;


@Service
public class FileService {

    private final Utils utils;

    public FileService(Utils utils) {
        this.utils = utils;
    }

    public void init() {
        try {
            Files.createFile(utils.getFileLocation());
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public FileResponse upload(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String random = RandomStringUtils.random(5, true, true);
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                int name = ThreadLocalRandom.current().nextInt(99999999, 1000000000);
                String s = FilenameUtils.getExtension(fileName);
                String fullFileName = random + name + "." + s;
                String currentUrl = getCurrentUrl(fullFileName);
                Files.copy(file.getInputStream(), this.utils.getFileLocation().resolve(Objects.requireNonNull(fullFileName)), StandardCopyOption.REPLACE_EXISTING);
                return new FileResponse(currentUrl, fullFileName);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public String decodeToImage(String a) {
        try {
            byte[] bytes = DatatypeConverter.parseBase64Binary(a);
            String random = RandomStringUtils.random(7, true, true);
//            String path = "C:\\project\\new-projects\\register_office\\src\\main\\java\\uz\\raqamli_talim\\register_office\\utils" + random + ".png";
            String path = "/home/software/invest_edu/fileStorageIIB/" + random + ".png";
            File file = new File(path);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(bytes);
            return getCurrentUrlPhoto(random + ".png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource download(String filename, Path path) {
        try {
            Path resolve = path.resolve(filename);
            Resource resource = new UrlResource(resolve.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public ResponseDto deleteFile(String filename) {
        try {
            File file = new File(utils.getFileLocation() + "/" + filename);
            return new ResponseDto(HttpStatus.OK.value(), SUCCESSFULLY.getMessage(), LocalDateTime.now(), file.delete());
        } catch (Exception e) {
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Error deleted:", LocalDateTime.now() );
        }
    }


    public void deleteFiles(List<String> filenames) {
        try {
            filenames.forEach(s -> {
                File file = new File(utils.getFileLocation() + "/" + s);
                file.delete();
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("Error deleted: " + e.getMessage());
        }
    }

    private String getCurrentUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/public/download/")
                .path(fileName).toUriString();
    }

    private String getCurrentUrlPhoto(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/public/downloadIIBPhoto/")
                .path(fileName).toUriString();
    }

}
