/**
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */

public class NimGame {

    public void gamePlay(NimPlayer player1, NimPlayer player2,int initialStone, int upperBound) {                       //Gameplay method to play games between two instance of NimPlayer

        System.out.println("\nInitial stone count: "+ initialStone);
        System.out.println("Maximum stone removal: "+ upperBound);
        System.out.println("Player 1: "+player1.getGivenName()+" "+player1.getFamilyName());
        System.out.println("Player 2: "+player2.getGivenName()+" "+player2.getFamilyName());
        System.out.println();

        player1.setGamePlayed(player1.getGamePlayed()+1);
        player2.setGamePlayed(player2.getGamePlayed()+1);

        NimPlayer currentPlayer, otherPlayer;                                                                           //Create two instance of NimPlayer to help with gamePlay
        currentPlayer = player1;
        otherPlayer = player2;

        while(initialStone > 0){

            printStone(initialStone);
            int remove = currentPlayer.removeStone(initialStone,upperBound);
            initialStone = initialStone - remove;

            if (winnerCheck(initialStone,otherPlayer))                                                                  //checking winner
                break;

            if (currentPlayer == player1){                                                                              //swap player after one round
                currentPlayer = player2;
                otherPlayer = player1;
            }
            else {
                currentPlayer = player1;
                otherPlayer = player2;
            }
        }
    }


    public static void printStone(int stoneLeft) {                                                                      //printing stone method
        System.out.print(stoneLeft + " stones left:");
        for(int i=0; i<stoneLeft; i++) {
            System.out.print(" *");
        }
        System.out.println("");
    }


    private boolean winnerCheck(int stoneLeft, NimPlayer winner){                                                       //Checking who is winner method
        if (stoneLeft == 0){
            System.out.println("Game Over\n"+winner.getGivenName()+" "+winner.getFamilyName()+ " wins!");
            winner.setGameWon(winner.getGameWon()+1);
            return true;
        }
        return false;
    }
}
