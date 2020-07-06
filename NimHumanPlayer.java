/**
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class NimHumanPlayer extends NimPlayer implements Testable{


    public NimHumanPlayer() {
        super();

    }

    public NimHumanPlayer(String userName, String familyName, String givenName, int gamePlayed, int gameWon) {
        super(userName, familyName, givenName, gamePlayed, gameWon);

    }


    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public int removeStone(int stoneLeft, int upperBound) {                                                             //remove stone method of Human player

        while(true){                                                                                                    //put into a while loop until a valid move has been made
            try{
                System.out.println(this.getGivenName()+"'s turn - remove how many?\n");
                int remove = Nimsys.keyboard.nextInt();

                if (stoneLeft > upperBound){                                                                            //Invalid input check and throwing exception
                    if(remove == 0 || remove > upperBound){
                        throw  new Exception("Invalid move. You must remove between 1 and "+upperBound+" stones.\n");
                    }
                }
                if (stoneLeft <= upperBound){
                    if (remove == 0 || remove > stoneLeft){
                        throw  new Exception("Invalid move. You must remove between 1 and "+stoneLeft+" stones.\n");    //Invalid input check and throwing exception
                    }
                }

                Nimsys.keyboard.nextLine();
                return remove;

            }catch (InputMismatchException e){
                System.out.println("Invalid input!!");                                                                  //Any other inputs other than int will be catched and ask for valid move again
                NimGame.printStone(stoneLeft);
                Nimsys.keyboard.nextLine();
            }catch (Exception e){
                System.out.println(e.getMessage());                                                                     //catching exception for invalid move
                NimGame.printStone(stoneLeft);
            }
        }
    }

    @Override
    public String advancedMove(boolean[] available, String lastMove) {                                                  //Human player advanced move method override
       while(true){
            try{
                System.out.println(this.getGivenName()+"'s turn - which to remove?\n");
                String newMove = Nimsys.keyboard.nextLine();
                StringTokenizer updatedMove = new StringTokenizer(newMove," ",false);                //String tokenizer to get the inputs (position, stone to remove)

                int startPosition = Integer.parseInt(updatedMove.nextToken());
                int stoneRemove = Integer.parseInt(updatedMove.nextToken());

                ArrayList<Boolean> myList = new ArrayList<Boolean>();                                                   //Boolean Arraylist to store all stones
                for (int i=0; i <available.length; i++)
                    myList.add(i,available[i]);


                if (stoneRemove == 1){
                    if (!myList.get(startPosition-1))
                        throw new Exception("Invalid move.\n");                                                         //Invalid input throwing exception of stone already removed from a position
                    else
                        available[startPosition-1] = false;
                }

                if (stoneRemove == 2){
                    if (!myList.get(startPosition) || !myList.get(startPosition-1))
                        throw new Exception("Invalid move.\n");                                                         //Invalid input throwing exception of stone already removed from a position
                    else{
                        available[startPosition-1] = false;                                                             //remove stone, making the position false
                        available[startPosition] = false;
                    }

                }

                if (startPosition > myList.size() || startPosition < 1 || stoneRemove <1 || stoneRemove >2)             //Invalid input throwing exception if position is wrong or stone remove > 2
                    throw new Exception("Invalid move.\n");

                return newMove;

            }catch (IndexOutOfBoundsException e){                                                                       //Array index out of bound exception catch
                System.out.println("Invalid move.\n");
                AdvancedGame.stoneDisplay(available);
            }
            catch (Exception e){                                                                                        //Invalid input exception catching
                System.out.println(e.getMessage());
                AdvancedGame.stoneDisplay(available);
            }
        }
    }
}
