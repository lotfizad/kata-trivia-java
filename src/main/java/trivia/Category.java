package trivia;

public enum Category {
    POP(0, "Pop"),
    SCIENCE(1, "Science"),
    SPORTS(2, "Sports"),
    ROCK(3, "Rock");

    private final int place;
    private final String questionPrefix;

    Category(int place, String questionPrefix) {
        this.place = place;
        this.questionPrefix = questionPrefix;
    }

    public static Category getCategoryForPlace(int place) {
        return values()[place % values().length];
    }

    public String getQuestionPrefix() {
        return questionPrefix;
    }
}
