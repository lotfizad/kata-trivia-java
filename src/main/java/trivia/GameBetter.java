package trivia;

import java.util.*;

public class GameBetter implements IGame {
   public static final int MAX_PLACES = 12;
   public static final int MAX_PURSES = 6;
   private static final int NUMBER_OF_QUESTIONS = 50;

   private final List<Player> players = new ArrayList<>();
   private final Map<Category, LinkedList<String>> questions = new HashMap<>();

   private int currentPlayer = 0;

   public GameBetter() {
      for (Category category : Category.values()) {
         LinkedList<String> categoryQuestions = new LinkedList<>();
         for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            categoryQuestions.addLast(category.getQuestionPrefix() + " Question " + i);
         }
         questions.put(category, categoryQuestions);
      }
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      Player player = players.get(currentPlayer);
      player.roll(roll);
      if (player.isGettingOutOfPenaltyBox()) {
         askQuestion();
      }
   }

   private void askQuestion() {
      Player player = players.get(currentPlayer);
      Category category = Category.getCategoryForPlace(player.getPlace());
      System.out.println(questions.get(category).removeFirst());
   }

   public boolean wasCorrectlyAnswered() {
      Player player = players.get(currentPlayer);
      boolean winner = player.correctAnswer();
      currentPlayer = (currentPlayer + 1) % players.size();
      return winner;
   }

   public boolean wrongAnswer() {
      Player player = players.get(currentPlayer);
      player.wrongAnswer();
      currentPlayer = (currentPlayer + 1) % players.size();
      return true;
   }
}