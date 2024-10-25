import java.util.*;

public class WordLadderClient {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("What file do you want to read?");
            System.exit(0);
        }

        String filename = args [0];
        WordLinker wordLinker = new WordLinker();
        Scanner scanner = new Scanner(System.in);

        //basic parameter of the while loop - for now
        int userSelection = 0;

        while (true && userSelection != 4) {


            System.out.println("You have 3 choices for using Word Ladders!\n1.) See neightbors of a word\n2.) Contstruct a random walk through the graph using a given start word\n3.) Find a path between two words\n4.) Exit\n Enter a number for your selection..");
            userSelection = scanner.nextInt();
            scanner.nextLine();

            if (userSelection < 1 || userSelection > 4) {
                System.out.println("Try again. Please enter 1, 2, 3, or 4 to continue.");
                continue;
            }

            System.out.printf("Please input a start word to find its neighbors...\nThere are %d total words to choose from: \n", wordLinker.getNumWords());
            String userStartWord = scanner.nextLine();
            wordLinker.setStartWord(userStartWord);


            // 1st see the neighbors of a word. If you input any word that is a word in wordlist
            // it will output a word that is only different by one letter
            if (userSelection == 1 && wordLinker.isWord(userStartWord)) {
                System.out.printf("There are %d neighbors of '%s' are %s: ", wordLinker.getNumCandidates(),  userStartWord, wordLinker.getNeighbors(userStartWord));
                System.out.println();
            }


            //You give a target length for a path positive integer you may not reach the target
            //length is the words don't have enough neighbors then input a start words that must be from the collection the output would be a random walk, but not the shortest
            if (userSelection == 2 && wordLinker.isWord(userStartWord)) {
                System.out.print("Enter the target length for the path (positive integer): ");
//                    int targetLength = scanner.nextInt();
//                    scanner.nextLine();
                System.out.println();
            }


            if (userSelection == 3 && wordLinker.isWord(userStartWord)) {
                System.out.println();
            }

            if (userSelection == 4) {
                System.out.println("Exiting the program.");
            }

        }


    }
}
