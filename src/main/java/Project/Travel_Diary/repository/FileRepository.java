package Project.Travel_Diary.repository;

import Project.Travel_Diary.domain.Board;
import Project.Travel_Diary.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;



@Slf4j
@Repository
public class FileRepository {

    //이미지 파일이 저장될 저장소 이름
    @Value("${file.dir}")
    private String fileDir;

    //저장소 이름 + 파일 이름 = 이미지 전체 경로명
    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    //사용자가 게시글을 등록할 때 업로드한 이미지를 저장하기 위한 메소드
    public List<UploadFile> save(Board board, List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFile> userfiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            if (!multipartFile.isEmpty()) {
                userfiles.add(toUploadFile(multipartFile));
            }

        }

        board.setFileList(userfiles);

        return userfiles;

    }

    //사용자가 업로드한 파일들을 반환
    public List<UploadFile> findFiles(Board board) {

        return board.getFileList();

    }

    //사용자가 업로드한 파일들을 각각 처리하기 위한 메소드 : 실제 파일 이름과 메모리에 저장할 파일 이름 생성
    public UploadFile toUploadFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = make_storeFilename(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(originalFilename, storeFilename);

    }

    //메모리에 저장할 파일 이름 생성 메소드
    public String make_storeFilename(String originalFilename) {

        String uuid = UUID.randomUUID().toString();
        String extension = findExtension(originalFilename);

        return uuid + "." + extension;

    }

    //확장자 찾기 위한 메소드 (ex : png)
    public String findExtension(String originalFilename) {

        int index = originalFilename.lastIndexOf(".");

        return originalFilename.substring(index+1);

    }

}
