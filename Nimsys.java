/**
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Nimsys {                                                                                                   //Nimsys class and Scanner & ArrayList
    public static Scanner keyboard = new Scanner(System.in);
    private static ArrayList<NimPlayer> myPlayerlist;


    public static void main(String[] args) {
        Nimsys nim1 = new Nimsys();
        myPlayerlist = new ArrayList<NimPlayer>();


        System.out.println("Welcome to Nim");

        nim1.readObjectFile();                                                                                          //reading from file to read previous game status

        while (true) {
            System.out.print("\n$");
            String inputLine = keyboard.nextLine();
            String command = "";
            String restInput = "";

            StringTokenizer updatedInput = new StringTokenizer(inputLine, " ", false);               //String tokenizer to separate inputs via delimiter space
            command = updatedInput.nextToken();

            if (updatedInput.hasMoreTokens()) {
                restInput = updatedInput.nextToken();                                                                   //rest of the input after command
            }

            try{
                switch (command) {
                    case "addplayer":
                        nim1.addHumanPlayer(restInput);
                        break;
                    case "addaiplayer":
                        nim1.addAiPlayer(restInput);
                        break;
                    case "displayplayer":
                        nim1.displayPlayer(restInput);
                        break;
                    case "removeplayer":
                        nim1.removePlayer(restInput);
                        break;
                    case "editplayer":
                        nim1.editPlayer(restInput);
                        break;
                    case "resetstats":
                        nim1.resetPlayerStats(restInput);
                        break;
                    case "startgame":
                        nim1.startGame(restInput);
                        break;
                    case "rankings":
                        nim1.rankingPlayer(restInput);
                        break;
                    case "startadvancedgame":
                        nim1.advancedGame(restInput);
                        break;
                    case "exit":
                        nim1.copyObjectFile();
                        System.out.println();
                        System.exit(0);
                    default:
                        throw new Exception("'" + command + "'" + " is not a valid command.");                          //if command does not match with one of the specified command throw exception
                }
            }catch (Exception e){
                System.out.println(e.getMessage());                                                                     //catch exception and print msg
            }
        }
    }

    private void copyObjectFile(){                                                                                      //copying game status to a file before exiting
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("players.dat"));
                outputStream.writeObject(myPlayerlist);
            outputStream.close();
        }catch (IOException e){
        }
    }



    private void readObjectFile(){                                                                                      //read previous game file and load it to program
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("players.dat"));
            myPlayerlist = (ArrayList<NimPlayer>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e){

        }
    }


    private void addHumanPlayer(String restInput){                                                                      //Add Human player method
        try{
            StringTokenizer restInputs = new StringTokenizer(restInput,",",false);
            if(restInputs.countTokens() < 3)
                throw new InvalidInputException();

            String userName = restInputs.nextToken();
            String familyName = restInputs.nextToken();
            String givenName= restInputs.nextToken();

            int index = findPlayer(userName);
            if (index >=0 ){
                System.out.println("The player already exists.");
                return;
            }

            NimPlayer newNimHumanPlayer = new NimHumanPlayer(userName,familyName,givenName,0,0);
            myPlayerlist.add(newNimHumanPlayer);

        }catch (InvalidInputException e){
            System.out.println(e.getMessage());                                   //if no of argument supplier less than 3, catch the exception
        }
    }


    private void addAiPlayer(String restInput){                                                                         //Add AIPlayer method
        try{
            StringTokenizer restInputs = new StringTokenizer(restInput,",",false);

            if(restInputs.countTokens() < 3)
                throw new InvalidInputException();

            String userName = restInputs.nextToken();
            String familyName = restInputs.nextToken();
            String givenName= restInputs.nextToken();



            int index = findPlayer(userName);
            if (index >= 0 ){
                System.out.println("The player already exists.");
                return;
            }

            NimPlayer newNimAIPlayer = new NimAIPlayer(userName,familyName,givenName,0,0);
            myPlayerlist.add(newNimAIPlayer);

        }catch(InvalidInputException e){
            System.out.println(e.getMessage());                                                                          //if no of argument supplier less than 3, catch the exception
        }
    }


    private void displayPlayer(String restInput){                                                                       //Method for display player


        if (restInput.isEmpty()){                                                                                       //Displaying all player

            userNameSort();                                                                                             //default player sorted by userName

            for (NimPlayer nimPlayer : myPlayerlist) {
                System.out.println(nimPlayer.toString());                                                               //display all player details using to String method
            }
            return;
        }

        StringTokenizer input = new StringTokenizer(restInput,",",false);
        String userName = input.nextToken();
        int index = findPlayer(userName);                                                                               //find the specific player exists in list & it exist print the player details
        if (index >=0 ){
            System.out.println(myPlayerlist.get(index).toString());
            return;
        }
        System.out.println("The player does not exist.");
    }





    private void startGame(String restInput){                                                                           //start game method to start game play between two players
        NimGame newGame = new NimGame();                                                                                //Object creation of NimGame class

       try{
           StringTokenizer command = new StringTokenizer(restInput,",",false);
           if(command.countTokens() < 4)
               throw new InvalidInputException();

           int initialStone = Integer.parseInt(command.nextToken());
           int upperBound = Integer.parseInt(command.nextToken());
           String player1UserName = command.nextToken();
           String player2UserName = command.nextToken();

           int pl1Index = findPlayer(player1UserName);
           int pl2Index = findPlayer(player2UserName);


           if (pl1Index < 0 || pl2Index < 0){                                                                           //if one of the player userName not exists return msg
               System.out.println("One of the players does not exist.");
               return;
           }
           NimPlayer player1 =  myPlayerlist.get(pl1Index);                                                             //find object of two player who will play the game
           NimPlayer player2 =  myPlayerlist.get(pl2Index);

           newGame.gamePlay(player1,player2,initialStone,upperBound);                                                   //passing both object and other game parameter to play game

       }catch(NumberFormatException e){
           System.out.println("Invalid input format");
       }catch(InvalidInputException e){
           System.out.println(e.getMessage());
       }
    }



    private void advancedGame(String restInput){                                                                        // Advanced game method
        AdvancedGame newGame = new AdvancedGame();                                                                      //Advanced game class object

        try{
            StringTokenizer command = new StringTokenizer(restInput,",",false);
            if(command.countTokens() < 3)
                throw new InvalidInputException();

            int initialStone = Integer.parseInt(command.nextToken());
            String player1UserName = command.nextToken();
            String player2UserName = command.nextToken();

            int pl1Index = findPlayer(player1UserName);
            int pl2Index = findPlayer(player2UserName);

            if (pl1Index < 0 || pl2Index < 0){                                                                          //if one of the player userName not exists return msg
                System.out.println("One of the players does not exist.");
                return;
            }

            boolean[] available = new boolean[initialStone];                                                            //boolean array creation of total stones and set all true
            for (int i=0; i<available.length; i++)
                available[i] = true;


            NimPlayer player1 = myPlayerlist.get(findPlayer(player1UserName));
            NimPlayer player2 = myPlayerlist.get(findPlayer(player2UserName));

            newGame.advancedGame(available,player1,player2);                                                            //advanced game method invocation

        }catch(InvalidInputException e){
            System.out.println(e.getMessage());                                                                         //if less argument supplied catch & print
        }

    }


    private void editPlayer(String restInput){                                                                          //Edit player method to edit a player

        try{
            StringTokenizer otherInput = new StringTokenizer(restInput,",",false);

            if(otherInput.countTokens() < 3)
                throw new InvalidInputException();


            String userName = otherInput.nextToken();
            String familyName = otherInput.nextToken();
            String givenName = otherInput.nextToken();

            if (findPlayer(userName) < 0 ){
                System.out.println("The player does not exist.");
                return;
            }
            int i = findPlayer(userName);
            myPlayerlist.get(i).setUserName(userName);
            myPlayerlist.get(i).setFamilyName(familyName);
            myPlayerlist.get(i).setGivenName(givenName);
        }catch(InvalidInputException e){
            System.out.println(e.getMessage());                                                                          //if less argument supplied catch & print
        }
    }


    private void resetPlayerStats(String restInput){                                                                    //reset statistics method when input is restStats
        if (restInput.isEmpty()){                                                                                       //reset all player statistics
            System.out.println("Are you sure you want to reset all player statistics? (y/n)");
            String command = keyboard.nextLine();
            char c = command.toLowerCase().charAt(0);
            if (c == 'y'){
                for (NimPlayer nimPlayer : myPlayerlist) {
                    nimPlayer.setGamePlayed(0);
                    nimPlayer.setGameWon(0);
                }
            }
            return;
        }
            StringTokenizer input = new StringTokenizer(restInput,",",false);                        //reset a single player statistics
            String userName = input.nextToken();
            int playerIndex = findPlayer(userName);
            if (playerIndex < 0){
                System.out.println("The player does not exist.");
                return;
            }
            myPlayerlist.get(playerIndex).setGamePlayed(0);
            myPlayerlist.get(playerIndex).setGameWon(0);
    }



    private void removePlayer(String restInput){                                                                        //Method for remove player from game
        if(restInput.isEmpty()){                                                                                        //if command is "removeplayer" remove all player
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String command = keyboard.nextLine();
            char c = command.toLowerCase().charAt(0);
            if (c =='y'){
                myPlayerlist.clear();
            }
            return;
        }
        StringTokenizer input = new StringTokenizer(restInput,",",false);                            //remove a specific player from the game
        String userName = input.nextToken();
        if (findPlayer(userName) < 0){
            System.out.println("The player does not exist.");
            return;
        }
        myPlayerlist.remove(findPlayer(userName));
    }




    private void rankingPlayer(String restInput){                                                                       //Method for rankings command
        userNameSort();                                                                                                 //calling Sort method by userName to settle the tie win Ratio

        try {
            if (restInput.isEmpty() || restInput.equalsIgnoreCase("desc")) {                               //if input is desc, sorted by winRatioSort Descending method
                winRatioSortDesc();
            } else if (restInput.equalsIgnoreCase("asc")) {                                                //if input is asc, sorted by winRatioSort Ascending method
                winRatioSortAsc();
            }
            else
                throw new InvalidInputException("Invalid argument supplied.");
        }catch (InvalidInputException e){
            System.out.println(e.getMessage());
            return;
        }

        for (NimPlayer nimPlayer : myPlayerlist) {                                                                      //Ranking player printout
            long winRatio = Math.round(nimPlayer.getWinRatio() * 100);
            String winPerc = winRatio + "%";
            int gamePlayed = nimPlayer.getGamePlayed();
            System.out.printf("%-4s | ", winPerc);
            System.out.printf("%02d games | ", gamePlayed);
            System.out.println(nimPlayer.getGivenName() + " " +
                    nimPlayer.getFamilyName());
        }
    }



    private int findPlayer(String userName){                                                                            //find a Nimplayer in arraylist and return its index, if not found return -1
        if (myPlayerlist.size() > 0 ){
            for (int i = 0; i< myPlayerlist.size(); i++){
                if (myPlayerlist.get(i).getUserName().equals(userName)){
                    return i;
                }
            }
        }
        return -1;
    }



    private void userNameSort(){                                                                                        //Sort by userName method using bubble sort algorithm
        boolean sorted = false;

        while(!sorted){
            sorted = true;
            for (int i = 0; i< myPlayerlist.size()-1; i++){
                String user1 = myPlayerlist.get(i).getUserName();
                String user2 = myPlayerlist.get(i+1).getUserName();
                NimPlayer temp;
                if (user1.compareToIgnoreCase(user2) > 0 ){
                    temp = myPlayerlist.get(i);
                    myPlayerlist.set(i,myPlayerlist.get(i+1));
                    myPlayerlist.set(i+1,temp);
                    sorted = false;
                }
            }
        }
    }


    private void winRatioSortDesc(){
        NimPlayer temp;
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for (int i = 0; i< myPlayerlist.size()-1; i++){
                if (myPlayerlist.get(i).getWinRatio() < myPlayerlist.get(i+1).getWinRatio()) {
                    temp = myPlayerlist.get(i);
                    myPlayerlist.set(i,myPlayerlist.get(i+1));
                    myPlayerlist.set(i+1,temp);
                    sorted = false;
                }
            }
        }
    }


    private void winRatioSortAsc(){
        NimPlayer temp;
        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for (int i = 0; i< myPlayerlist.size()-1; i++){
                if (myPlayerlist.get(i).getWinRatio() > myPlayerlist.get(i+1).getWinRatio()) {
                    temp = myPlayerlist.get(i);
                    myPlayerlist.set(i,myPlayerlist.get(i+1));
                    myPlayerlist.set(i+1,temp);
                    sorted = false;
                }
            }
        }
    }
}
