package Project.Travel_Diary.domain;

import Project.Travel_Diary.domain.Enum.Continent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

@Data
//게시글
public class Board {

    private Long id; //게시글 고유 번호

    @NotBlank //null, "", " " 을 허용하지 않는다.
    private String title; //로그인 한 사용자가 입력해야 할 게시글 제목

    @NotBlank
    private Continent continent; //로그인 한 사용자가 설정해야 할 나라의 대륙

    @NotBlank
    private String nation; //로그인 한 사용자가 입력해야 할 나라

    @NotBlank
    @DateTimeFormat(pattern = "yyyy/MM/dd") //입력 폼에서 yyyy/MM/dd 형식으로 값을 보낼 시 LocalDate 객체를 생성 (특정 형식[yyyy/MM/dd]이 적용되는 것이 아님)
    private LocalDate startTime; //로그인 한 사용자가 설정해야 할 출발 날짜 지정

    @NotBlank
    @DateTimeFormat(pattern = "yyyy/MM/dd") //입력 폼에서 yyyy/MM/dd 형식으로 값을 보낼 시 LocalDate 객체를 생성 (특정 형식[yyyy/MM/dd]이 적용되는 것이 아님)
    private LocalDate endTime; //로그인 한 사용자가 설정해야 할 종료 날짜 지정

    @NotBlank
    private int expense; //로그인 한 사용자가 입력해야 할 총 경비

    @NotBlank
    private String content; //로그인 한 사용자가 입력해야 할 게시글 내용

    private List<UploadFile> fileList; //로그인 한 사용자가 업로드 한 이미지를 저장

}
