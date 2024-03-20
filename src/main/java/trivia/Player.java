package trivia;

public class Player {
    private final String name;
    private int place = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;
    private boolean isGettingOutOfPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public void roll(int roll) {
        System.out.println(name + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(name + " is getting out of the penalty box");
                move(roll);
            } else {
                System.out.println(name + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            move(roll);
        }
    }

    private void move(int roll) {
        place = (place + roll) % GameBetter.MAX_PLACES;
        System.out.println(name + "'s new location is " + place);
        System.out.println("The category is " + Category.getCategoryForPlace(place).getQuestionPrefix());
    }

    public boolean correctAnswer() {
        if (inPenaltyBox && !isGettingOutOfPenaltyBox) {
            return true;
        }

        System.out.println("Answer was corrent!!!!");
        purse++;
        System.out.println(name + " now has " + purse + " Gold Coins.");
        return purse != GameBetter.MAX_PURSES;
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    public int getPlace() {
        return place;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}