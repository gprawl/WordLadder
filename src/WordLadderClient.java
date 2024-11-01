import java.util.*;

public class WordLadderClient {
    public static void main(String[] args) {

        WordLinker wordLinker = new WordLinker();
        Scanner userInput = new Scanner(System.in);
        int userSelection = 0; //basic parameter of the while loop - for now

        while (userSelection != 4) { //work on all errors from wrong input

            userSelection = getUserSelection(userInput);

            String userStartWord = "";
            if (userSelection != 4) {
                userStartWord = mainSetStartWord(wordLinker, userInput);
            }

                switch (userSelection){

              // 1st see the neighbors of a word. If you input any word that is a word in wordlist
              // it will output a word that is only different by one letter
                    case 1:
                        showNeighbors(wordLinker, userStartWord);
                        break;

               //You give a target length for a path positive integer you may not reach the target
               //length is the words don't have enough neighbors then input a start words that must
              // be from the collection the output would be a random walk, but not the shortest
                    case 2:
                        //function_that_makes_randompath(wordLinker, userStartWord, scanner);
                        break;

                    case 3:

                        break;

                    case 4:
                        System.out.println("The Word Ladder program has ended.");
                        return;

                }

            }

    }

    public static int getUserSelection(Scanner userInput) {
        int selection = 0;
        while (true) {
            System.out.println("\nChoose an option for Word Ladders:");
            System.out.println("1.) See neighbors of a word");
            System.out.println("2.) Construct a random walk through the graph");
            System.out.println("3.) Find a path between two words");
            System.out.println("4.) Exit");
            System.out.print("Enter your choice: ");

            try {
                selection = userInput.nextInt();
                userInput.nextLine();

                if (selection >= 1 && selection <= 4) {
                    return selection;
                } else {
                    System.out.println("Try agian. Please pick a number between 1 and 4.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 4.");
                userInput.nextLine();
            }
        }
    }
    public static ArrayList<String> randomPath(String startWord, int pathLength, ArrayList<String> wordList, WordLinker w1){
        Random rand = new Random();
        HashSet<String> wrdsAlreadySeen = new HashSet<>();
        wrdsAlreadySeen.add(startWord);
        ArrayList<String> path = new ArrayList<>();
        path.add(startWord);

        return randomPath(startWord, pathLength, wordList, w1);
    }

    public static ArrayList<String> buildPath(String currentWord, int remainingLength, ArrayList<String> wordList, WordLinker w1, HashSet<String> alreadySeen, ArrayList<String> path, Random rand){
        if(remainingLength == 0 || currentWord == null){
            return path;
        }

        List<String> neighbors = w1.getNeighbors(currentWord);
        ArrayList<String> validNeighbors = new ArrayList<>();

        for(String neighbor : neighbors){
            if(!alreadySeen.contains(neighbor)){
                validNeighbors.add(neighbor);
            }
        }

        if(validNeighbors.isEmpty()){
            return path;
        }
        int randomIndex = rand.nextInt(validNeighbors.size());
        String nextWord = validNeighbors.get(randomIndex);
        alreadySeen.add(nextWord);
        path.add(nextWord);

        return buildPath(currentWord, remainingLength, wordList, w1, alreadySeen, path, rand);
    }


    public static String mainSetStartWord(WordLinker wordLinker, Scanner userInput) {
        //Scanner userInput = new Scanner(System.in);
        String inputWord = "";
        while (true) {
            int numWords = wordLinker.getNumWords();
            System.out.printf("Please input a start word (Total words: %d): ", numWords);
            inputWord = userInput.nextLine();

            // doubele check for the loop
            if (wordLinker.isWord(inputWord)) {
                wordLinker.setStartWord(inputWord);
                return inputWord;
            } else {
                System.out.println("The word is not in the word list. Try again.");
            }
        }
    }

    public static void showNeighbors(WordLinker wordLinker, String userStartWord) {
        List<String> neighbors = wordLinker.getNeighbors(userStartWord);
        System.out.printf("There are %d neighbors of '%s': %s\n", neighbors.size(), userStartWord, neighbors);
    }

}