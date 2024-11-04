import java.sql.SQLOutput;
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
        String startWord = "start";
        int pathLength = 5;

        Set<String> emptyList = new HashSet<>();

        Set<String> randomPath = WordLadderClient.randomPath(userStartWord, pathLength, wordList, w1, emptyList);
        System.out.println(randomPath);


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
                        System.out.println("How long do you want the tab: ");
                        int length = userInput.nextInt();
                        Set<String> random_walk = WordLadderClient.randomPath(userStartWord, length, wordList, w1, emptyList);
                        System.out.println(random_walk);
                        break;

                //BFS both words have to be outputs of the collection and the output is the word ladder between those two words.
                // if you use bfs it will be the shortest path. the top level control should be clear and user-friendly,
                    case 3:
                        System.out.println("Enter the target word: ");
                        String userTargetWord = userInput.nextLine();

                        if(!wordLinker.isWord(userTargetWord)){
                            System.out.println("The target word is not found.");
                        } else {
                            //List<String> ladder = BFS(wordLinker, startWord, userTargetWord);
//                            if(ladder.isEmpty()){
//                                System.out.println("No word ladder found.");
//                            } else {
//                                System.out.println("Word Ladder: ");
//                            }
                        }

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
//    public static List<String> BFS(WordLinker wl, String userStartWord, String userTargetWord) {
//        Queue<List<String>> queue = new LinkedList<>();
//
//        List<String> initialPath = new ArrayList<>();
//        initialPath.add(userStartWord);
//        queue.add(initialPath);
//
//        Set<String> visited = new HashSet<>();
//        visited.add(userStartWord);
//
//        while(!queue.isEmpty()){
//            List<String> path = queue.poll();
//            String currentWord = path.get(path.size() - 1);
//
//            if(currentWord.equals(userTargetWord)){
//                return path;
//            }
//            for(String neighbor : wl.getNeighbors(currentWord)){
//                if(!visited.contains(neighbor)){
//                    visited.add(neighbor);
//
//                    List<String> newPath = new ArrayList<>(path);
//                    newPath.add(neighbor);
//                    queue.add(newPath);
//                }
//            }
//
//        }
//
//        return Collections.emptyList();
//    }
    public static Set<String> randomPath(String startWord, int pathLength, ArrayList<String> wordList, WordLinker w1, Set<String> list){
        Random rand = new Random();
        HashSet<String> wrdsAlreadySeen = new HashSet<>();
        wrdsAlreadySeen.add(startWord);
        //ArrayList<String> path = new ArrayList<>();
        list.add(startWord);

        //return buildPath(startWord, pathLength - 1, wordList,w1, wrdsAlreadySeen, path, rand);
        if(pathLength == 0 || startWord == null){
            return list;
        }

        List<String> neighbors = w1.getNeighbors(startWord);
        if(neighbors.size()<= 0){
            return list;
        }
        //ArrayList<String> validNeighbors = new ArrayList<>();
        int randomIndex = rand.nextInt(neighbors.size());
        startWord = neighbors.get(randomIndex);


        //for(String neighbor : neighbors){
            if(!wrdsAlreadySeen.contains(startWord)){
                //validNeighbors.add(startWord);
                wrdsAlreadySeen.add(startWord);
                list.add(startWord);
            }

        //}

//        if(validNeighbors.isEmpty()){
//            return path;
//        }

//        int randomIndex = rand.nextInt(validNeighbors.size());
//        String nextWord = validNeighbors.get(randomIndex);
//        alreadySeen.add(nextWord);
//        path.add(nextWord);

        return randomPath(startWord, pathLength-1, wordList, w1, list);
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
            System.out.printf("There are %d neighbors of '%s'\n: ", neighbors.size(), userStartWord);
            System.out.println(neighbors);
        }
    }




}