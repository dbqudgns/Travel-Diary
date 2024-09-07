package Project.Travel_Diary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//업로드 파일 정보 보관
public class UploadFile {

    private String uploadFileName; //업로드한 파일 실제 이름
    private String storeFileName; //업로드한 파일 저장 이름

}
