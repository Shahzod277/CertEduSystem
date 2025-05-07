package uz.raqamli_talim.certedusystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse implements Serializable {

    private Integer id;
    private String url;
    private Boolean isMain = Boolean.FALSE;
    private String fileName;

    public FileResponse(String url , String fileName) {
        this.url = url;
        this.fileName = fileName;
    }
}
