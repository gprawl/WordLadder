import java.util.*;

public class WordLadderClient {
    public static void main(String[] args) {

        WordLinker wordLinker = new WordLinker();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the Word Ladder program!");
        String userStartWord = mainSetStartWord(wordLinker);
        int userSelection = 0; //basic parameter of the while loop - for now

        ArrayList<String> wordList = new ArrayList<>();
        WordLinker w1 = new WordLinker();
//        String startWord = "start";
        int pathLength = 5;

//        List<String> randomPath = WordLadderClient.randomPath(startWord, pathLength, wordList, w1);


        while (userSelection != 4) { //work on all errors from wrong input

            userSelection = getUserSelection(userInput);

//            String userStartWord = "";
            if (userSelection == 4) {
                System.out.println("The Word Ladder program has ended.");
                break;
            }

                switch (userSelection){

              // 1st see the neighbors of a word. If you input any word that is a word in wordlist
              // it will output a word that is only different by one letter
                    case 1:
                        showNeighbors(wordLinker, userStartWord);
                        break;

               //You give a target length for a path it must be a positive integer, although you may not reach the target
               //length. if the words don't have enough neighbors then input a start word that must
              // be from the collection, the output would be a random walk make sure to handle target length exceptions
                    case 2:
                        System.out.print("Enter the target length for the random path (positive integer): ");
                        pathLength = userInput.nextInt();
                        userInput.nextLine();

                        if (pathLength <= 0) {
                            System.out.println("Path length must be a positive integer. Exiting the program.");
                            return;
                        }

                        List<String> randomPath = randomPath(userStartWord, pathLength, wordLinker);

                        if (randomPath.size() > 1) {
                            System.out.println("Random Path: " + String.join(" -> ", randomPath));
                        } else {
                            System.out.println("Could not build a path with that length.");
                        }

                        break;

                //BFS both words have to be outputs of the collection and the output is the word ladder between those two words.
                // if you use bfs it will be the shortest path. the top level control should be clear and user-friendly,
                    case 3:

                        break;

                    default:
                        System.out.println("Not valid. Please try again.");

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
                    System.out.println("Try again. Please pick a number between 1 and 4.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 4.");
                userInput.nextLine();
            }
        }
    }
    public static List<String> randomPath(String startWord, int pathLength, WordLinker w1){
        Random rand = new Random();
        HashSet<String> wrdsAlreadySeen = new HashSet<>();
        wrdsAlreadySeen.add(startWord);
        List<String> path = new ArrayList<>();
        path.add(startWord);

        return buildPath(startWord, pathLength - 1, path, w1, wrdsAlreadySeen, rand);
    }

    public static List<String> buildPath(String currentWord, int remainingLength, List<String> path, WordLinker w1, HashSet<String> alreadySeen, Random rand){
        if(remainingLength == 0 || currentWord == null){
            return path;
        }

        List<String> neighbors = w1.getNeighbors(currentWord);
        List<String> validNeighbors = new ArrayList<>();

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

        return buildPath(currentWord, remainingLength - 1 , path , w1, alreadySeen, rand);
    }


    public static String mainSetStartWord(WordLinker wordLinker) {
        Scanner userInput = new Scanner(System.in);
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
                System.out.println("The word is either not a word or not in the word list. Try again.");
            }
        }
    }

    public static void showNeighbors(WordLinker wordLinker, String userStartWord) {
        List<String> neighbors = wordLinker.getNeighbors(userStartWord);

        if (neighbors == null || neighbors.isEmpty()) {
            System.out.printf("No neighbors found for '%s'.\n", userStartWord);
            return;
        } else {
            System.out.printf("There are %d neighbors of '%s': ", neighbors.size(), userStartWord);
        }
    }

}
