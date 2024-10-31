import java.util.*;

public class WordLadderClient {
    public static void main(String[] args) {

        WordLinker wordLinker = new WordLinker();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the Word Ladder program!");
        String userStartWord = mainSetStartWord(wordLinker);
        int userSelection = 0; //basic parameter of the while loop - for now


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

               //You give a target length for a path positive integer you may not reach the target
               //length is the words don't have enough neighbors then input a start words that must
              // be from the collection the output would be a random walk, but not the shortest
                    //make sure to handle target length exceptions
                    case 2:
                        //randompath(wordLinker, userStartWord);
                        break;

                //BFS both words have to be outputs of the collection and the output is the word ladder between those two words.
                // if you use bfs it will be the shortest path. the top level control should be clear and user-friendly,
                    case 3:
                        //BFS(wordLinker, userStartWord);
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