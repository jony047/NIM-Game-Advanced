/*
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */


import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class NimAIPlayer extends NimPlayer implements Testable{


    public NimAIPlayer() {
        super();
    }

    public NimAIPlayer(String userName, String familyName, String givenName, int gamePlayed, int gameWon) {
        super(userName, familyName, givenName, gamePlayed, gameWon);
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String advancedMove(boolean[] available, String lastMove) {
        // the implementation of the victory
        // guaranteed strategy designed by you

        String move="";

        //first move for AI player when last move is empty

        if (lastMove.isEmpty()){
            if ((available.length % 2) == 0)
                move = (available.length/2)+" "+2;
            else
                move = ((available.length/2)+1)+" "+1;
        }

        //Counting stone left

        int stoneLeft = 0;
        for (int i=0; i < available.length; i++){
            if (available[i])
                stoneLeft++;
        }

        //if last one or two stone left, try to pick both or last one

        if (stoneLeft == 1){
            for (int i=0; i<available.length; i++){
                if (available[i])
                    return (i+1)+" "+1;
            }
        }
        if (stoneLeft == 2){
            ArrayList<Boolean> myList = new ArrayList<Boolean>();
            for (int i=0; i <available.length; i++)
                myList.add(i,available[i]);

            for (int i=(myList.size()-1); i>=0; i--) {
                if (myList.get(i) && myList.get(i-1))
                    return (i) + " " + 2;
            }
            for (int i=0; i<myList.size(); i++){
                if (myList.get(i))
                    return (i+1)+" "+1;
            }

        }


        //in between game strategy

        if(!lastMove.isEmpty()){
                String[] rivalMove = lastMove.split(" ");
                int lastMoveStartPosition = Integer.parseInt(rivalMove[0]);
                int lastMoveRemoveCount = Integer.parseInt(rivalMove[1]);

                int n = available.length;


                if (n%2 == 1){
                    if (lastMoveRemoveCount == 1){
                        if (lastMoveStartPosition > n/2){
                            for (int i=0; i<=n/2; i++){
                                if (available[i])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }

                        else {
                            for (int i=n-1; i>=n/2; i--){
                                if (available[i])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }
                    }
                    else {
                        if (lastMoveStartPosition > n/2){
                            for (int i=0; i<=n/2; i++){
                                if (available[i] && available[i+1])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }

                        else {
                            for (int i=n-1; i>=n/2; i--){
                                if (available[i] && available[i+1])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }

                    }

                }

                else{
                    if (lastMoveRemoveCount == 1){
                        if(lastMoveStartPosition > n/2){
                            for (int i=0; i<n/2; i++){
                                if (available[i])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }
                        else{
                            for (int i=n-1; i>n/2; i--){
                                if (available[i])
                                    move= (i+1)+" "+lastMoveRemoveCount;
                            }
                        }
                    }
                    else{
                        if(lastMoveStartPosition > n/2){
                            for (int i=0; i<n/2; i++){
                                if (available[i] && available[i+1])
                                    move = (i+1)+" "+lastMoveRemoveCount;
                            }
                        }
                        else{
                            for (int i=n-1; i>n/2; i--){
                                if (available[i] && available[i+1])
                                    move= (i+1)+" "+lastMoveRemoveCount;
                            }
                        }
                    }

                }
            }


            return move;
        }



    //AI player must win removeStone method

    @Override
    public int removeStone(int stoneLeft, int upperBound) {
        System.out.println(this.getGivenName() + "'s turn - remove how many?\n");

        int k = 0;
        int remove=0;
        boolean stoneRemove = false;
            while(!stoneRemove){
                int left = (k*(upperBound+1))+1;                                                                        //stone left count formula
                for (remove = 1; remove <= upperBound; remove++){
                    if ( remove == (stoneLeft - left)){                                                                 //find stone number to be remove by keeping stone left amount of stones for opponent
                        stoneRemove = true;
                        break;                                                                                          //break the for loop and while loop
                    }
                }
                k += 1;
                if (k==100)                                                                                             //break the while loop after trying 100 times for k (0,1,2...100), remove 1 stone default
                    return 1;                                                                                           //for the case when stone left does not satisfy the remove formula
            }
        return remove;
    }
}
