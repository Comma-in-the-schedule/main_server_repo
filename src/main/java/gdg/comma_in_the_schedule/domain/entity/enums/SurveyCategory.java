package gdg.comma_in_the_schedule.domain.entity.enums;

public enum SurveyCategory {
    POPUP_STORE(1, "팝업스토어"),
    EXHIBITION(2, "전시회"),
    MOVIE(3, "영화"),
    WORKSHOP(4, "공방");

    private final int code;
    private final String description;

    SurveyCategory(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 숫자 -> 카테고리 변환
    public static SurveyCategory fromCode(int code) {
        for (SurveyCategory category : SurveyCategory.values()) {
            if (category.getCode() == code) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category code: " + code);
    }

    // 카테고리 -> 숫자 변환
    public static int toCode(SurveyCategory category) {
        return category.getCode();
    }
}
