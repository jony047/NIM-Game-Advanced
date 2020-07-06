/**
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */


import java.util.StringTokenizer;

public class AdvancedGame {

    public void advancedGame(boolean[] available, NimPlayer player1, NimPlayer player2) {

        System.out.println("\nInitial stone count: " + available.length);


        System.out.print("Stones display:");                                                                            //display stones
        for (int i = 0; i < available.length; i++) {
            if (available[i])
                System.out.print(" <" + (i + 1) + ",*>");
            else
                System.out.print(" <" + (i + 1) + ",x>");
        }
        System.out.println();

        System.out.println("Player 1: " + player1.getGivenName() + " " + player1.getFamilyName());
        System.out.println("Player 2: " + player2.getGivenName() + " " + player2.getFamilyName());
        System.out.println();

        player1.setGamePlayed(player1.getGamePlayed() + 1);
        player2.setGamePlayed(player2.getGamePlayed() + 1);


        NimHumanPlayer tempHuman;                                                                                       //two temp object creation of Human and AI player
        NimAIPlayer tempAI;

        NimPlayer currentPlayer, otherPlayer;                                                                           //two object creation of NimPlayer

        currentPlayer = player1;
        otherPlayer = player2;


        String lastMove="";

        while(true){

                stoneDisplay(available);

                if (currentPlayer instanceof NimHumanPlayer){
                    tempHuman = (NimHumanPlayer) currentPlayer;                                                         //down casting tempHuman
                    String newMove = tempHuman.advancedMove(available,lastMove);
                    if (winnerCheck(available,tempHuman))
                        break;
                    lastMove = newMove;
                }

                else{
                    tempAI = (NimAIPlayer) currentPlayer;                                                               //down casting tempAI
                    String newMove = tempAI.advancedMove(available,lastMove);
                    if (winnerCheck(available,tempAI))
                        break;
                }

                if (currentPlayer == player1){
                    currentPlayer = player2;
                    otherPlayer = player1;
                }
                else {
                    currentPlayer = player1;
                    otherPlayer = player2;
                }
        }
    }


    public static void stoneDisplay(boolean[] available){
        int stoneLeft = 0;
        for (int n=0; n<available.length; n++){
            if(available[n] == true)
                stoneLeft++;
        }

        System.out.print(stoneLeft + " stones left:");
        for (int i = 0; i < available.length; i++) {
            if (available[i] == true)
                System.out.print(" <" + (i+1) + ",*>");
            else
                System.out.print(" <" + (i+1) + ",x>");
        }
        System.out.println("");
    }

    private boolean winnerCheck(boolean[] availableStone, NimPlayer winner){
        int count = availableStone.length;                                                                              //Check whether all stones have been removed
        for (int i=0 ; i < availableStone.length; i++){
            if (!availableStone[i])
                count--;
        }

        if (count == 0){
            System.out.println("Game Over\n"+winner.getGivenName()+" "+winner.getFamilyName()+ " wins!");               //declare winner if count = 0 & return "finish"
            winner.setGameWon(winner.getGameWon()+1);
           return true;
        }
        return false;
    }

}