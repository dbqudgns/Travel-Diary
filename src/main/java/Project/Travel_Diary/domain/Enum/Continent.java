package Project.Travel_Diary.domain.Enum;

//대륙 : 사용자가 여행한 나라의 대륙을 선택하기 위해 열거형으로 설정
public enum Continent {

    NORTH_AMERICA("북아메리카"),
    SOUTH_AMERICA("남아메리카"),
    ASIA("아시아"),
    AFRICA("아프리카"),
    EUROPE("유럽"),
    AUSTRALIA("오세아니아");

    private final String description;

    Continent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
