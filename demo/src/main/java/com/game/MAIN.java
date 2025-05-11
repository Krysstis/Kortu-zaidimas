package com.game;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

//////////////////// 649 euluciu //////////////////////

public class MAIN 
{
    public static void main( String[] args ) throws IOException
    {
        Scanner input = new Scanner(System.in);
        int option;
        while(true){
            clearScreen();
            System.out.println("Welcome to the card game called 'Cat and Dog'!");
            System.out.println("\nChoose an option: ");
            System.out.println("1. Start");
            System.out.println("2. How to play");
            System.out.println("3. Exit\n");
            System.out.print("Your option is: ");

            if(input.hasNextInt()){
                option = input.nextInt();
            }
            else{
                input.next();
                System.out.println("Wrong Choice, Try Again!");
                continue;
            }
            switch(option){
                case 1: 
                    clearScreen();
                    gamePlay game = new gamePlay();
                    game.GamePlay();
                    break;
                case 2:
                    clearScreen();
                    File file = new File("demo\\src\\main\\java\\com\\game\\Howtoplay.txt");
                    Scanner readTxt = new Scanner(file);
                    while(readTxt.hasNextLine()) System.out.println(readTxt.nextLine());
                    readTxt.close();
                    System.out.println("\nPress '1' to return to the main menu: ");
                    while (true) {
                        if (input.hasNextInt() && input.nextInt() == 1) {
                            break;
                        } else {
                            System.out.println("Wrong Input. Press '1' to return to the main menu: ");
                            input.next();
                        }
                    }
                    break;
                case 3: 
                    clearScreen();
                    System.out.println("You exited the game! See you next time! :)");
                    input.close();
                    return;
                default:
                    System.out.println("Wrong choice, try again!");
                    break;
            }   
        }
    }
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}