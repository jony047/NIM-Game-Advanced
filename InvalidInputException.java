/*
 * Name: Jony Saha
 * Username: jonys
 * Student Number:1134030
 * Version 3.0
 */


public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super("Incorrect number of arguments supplied to command.");
    }
    public InvalidInputException(String msg){
        super(msg);
    }
}
