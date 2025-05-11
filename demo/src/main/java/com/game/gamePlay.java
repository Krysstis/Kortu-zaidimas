package com.game;

import java.io.IOException;
import java.util.*;

public class gamePlay {
    public void GamePlay()
    {
        String catJson = "demo\\src\\main\\java\\com\\game\\catcards.json";
        String dogJson = "demo\\src\\main\\java\\com\\game\\dogcards.json";
        Queue<CardInfo> catType = new LinkedList<>();
        Queue<CardInfo> dogType = new LinkedList<>();
        deckMaker decks = new deckMaker();
        catPlayer cat = new catPlayer();
        dogPlayer dog = new dogPlayer();
        Scanner scanner = new Scanner(System.in);

        decks.deck(catJson, catType);
        decks.deck(dogJson, dogType);

        while(cat.catHealth > 0 && dog.dogHealth > 0){
            if(catType.isEmpty()){
                decks.deck(catJson, catType);
            }
            else if(dogType.isEmpty()){
                decks.deck(dogJson, dogType);
            }
            else{
                cat.draw(catType);
                dog.draw(dogType);
                clearScreen();
                System.out.println("-------------------------------");
                System.out.println("Cat's Health: " + cat.catHealth);
                System.out.println("Dog's Health: " + dog.dogHealth);
                System.out.println("-------------------------------");
                
                if(dog.stunTurnsRemaining > 0){
                    System.out.println("Cat's Turn!!");
                    System.out.println("Cards: ");
                    for(int i=0; i<cat.cardsHeld.size(); i++){
                        System.out.println((i+1) + ". " + cat.cardsHeld.get(i));
                    }
                    int option = -1;
                    while (true) {
                        System.out.print("\nChoose between (1-" + cat.cardsHeld.size() + ") or type 'e' to exit: ");
                        if (scanner.hasNextInt()) {
                            option = scanner.nextInt() - 1;
                            if (option >= 0 && option < cat.cardsHeld.size()) {
                                break;
                            } else {
                                System.out.println("There is no card like that. Try again.");
                            }
                        } else {
                            String input = scanner.next();
                            if (input.equalsIgnoreCase("e")) {
                                System.out.println("Exiting the game. Goodbye!");
                                //canner.close();
                                try{
                                    Thread.sleep(2000);
                                } catch(InterruptedException e){
                                    System.out.println("The sleep was interrupted");
                                }
                                try {
                                    MAIN.main(new String[]{});
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a number or 'e' to exit.");
                            }
                        }
                    }
                    cat.useCard(option);
                    System.out.println("You Chose This Card: " + (option+1) + ". " + cat.cardsHeld.get(option));
                    if(cat.usedCard.getType() == "stun" && cat.usedCard.getType() != null){
                        System.out.println("Stunning the opponent (dog) for another turn!");
                    }
                    System.out.println("The Dog is stunned and cannot play this turn.");
                    try{
                        Thread.sleep(3000);
                    } catch(InterruptedException e){
                        System.out.println("The sleep was interrupted");
                    }
                    dog.stunTurnsRemaining--;
                    cat.calculate(cat.usedCard, dog, cat);
                }


                else if (cat.stunTurnsRemaining > 0){
                    System.out.println("The Cat is stunned and cannot play this turn.");
                    cat.stunTurnsRemaining--;
                    System.out.println("Dog's Turn!!\n");
                    System.out.print("Dog Is Picking");
                    dog.useCard();
                    for(int i=0; i<3; i++){
                        try{
                            Thread.sleep(2000);
                        } catch(InterruptedException e){
                            System.out.println("The sleep was interrupted");
                        }
                        System.out.print(".");
                    }
                    for(int i=0; i<3; i++){
                        if (i == 0) {
                            System.out.println("\nDog Used This Card: " + dog.usedCard);
                            if(dog.usedCard.getType().equals("stun")){
                                System.out.println("You (cat) have been Stunned for another turn!");
                            }
                        }
                        try{
                            Thread.sleep(1000);
                        } catch(InterruptedException e){
                            System.out.println("The sleep was interrupted");
                        }
                    }
                    dog.calculate(dog.usedCard, dog, cat);
                }
                else{
                    ////////////// CAT ////////////////////////
                    System.out.println("Cat's Turn!!");
                    System.out.println("Cards: ");
                    for(int i=0; i<cat.cardsHeld.size(); i++){
                        System.out.println((i+1) + ". " + cat.cardsHeld.get(i));
                    }
                    int option = -1;
                    while (true) {
                        System.out.print("\nChoose between (1-" + cat.cardsHeld.size() + ") or type 'e' to exit: ");
                        if (scanner.hasNextInt()) {
                            option = scanner.nextInt() - 1;
                            if (option >= 0 && option < cat.cardsHeld.size()) {
                                break;
                            } else {
                                System.out.println("There is no card like that. Try again.");
                            }
                        } else {
                            String input = scanner.next();
                            if (input.equalsIgnoreCase("e")) {
                                System.out.println("Exiting the game. To the main menu we go!");
                                try{
                                    Thread.sleep(2000);
                                } catch(InterruptedException e){
                                    System.out.println("The sleep was interrupted");
                                }
                                try {
                                    MAIN.main(new String[]{});
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a number or 'e' to exit.");
                            }
                        }
                    }
                    System.out.println("You Chose This Card: " + (option+1) + ". " + cat.cardsHeld.get(option));

                    /////////// WHAT CARD //////////////////////
                    cat.useCard(option);
                    dog.useCard();
                    
                    if(cat.usedCard.getType().equals("stun") && cat.usedCard.getType() != null){
                        System.out.println("Stunning the opponent (dog) for next turn!");
                    }
                    try{
                        Thread.sleep(3000);
                    } catch(InterruptedException e){
                        System.out.println("The sleep was interrupted");
                    }

                    ////////////////////// DOG /////////////////
                    System.out.println("Dog's Turn!!\n");
                    System.out.print("Dog Is Picking");
                    for(int i=0; i<3; i++){
                        try{
                            Thread.sleep(2000);
                        } catch(InterruptedException e){
                            System.out.println("The sleep was interrupted");
                        }
                        System.out.print(".");
                    }
                    for(int i=0; i<3; i++){
                        if (i == 0) {
                            System.out.println("\nDog Used This Card: " + dog.usedCard);
                            if(dog.usedCard.getType().equals("stun")){
                                System.out.println("You (cat) have been Stunned for next turn!");
                            }
                        }
                    }
                    if(cat.usedCard.getType().equals("reflect")){
                        cat.reflect(dog.usedCard, dog, cat);
                    }
                    else if(dog.usedCard.getType().equals("reflect")){
                        dog.reflect(cat.usedCard, dog, cat);
                    }
                    else if(cat.usedCard.getType().equals("reflect") && dog.usedCard.getType().equals("reflect")){
                        continue;
                    }
                    else if(cat.usedCard.getType().equals("heal") && (dog.usedCard.getType().equals("damage") || dog.usedCard.getType().equals("steal"))){
                        dog.calculate(dog.usedCard, dog, cat);
                        cat.calculate(cat.usedCard, dog, cat);
                    }
                    else if(dog.usedCard.getType().equals("heal") && (cat.usedCard.getType().equals("damage") || cat.usedCard.getType().equals("steal"))){
                        cat.calculate(cat.usedCard, dog, cat);
                        dog.calculate(dog.usedCard, dog, cat);
                    }
                    else if(cat.usedCard.getType().equals("steal") && dog.usedCard.getType().equals("steal")){ 
                        int dmg = cat.usedCard.getValue() + cat.usedCard.getValue();
                        cat.catHealth -= dmg;
                        dog.dogHealth -= dmg;
                    }
                    else{
                        cat.calculate(cat.usedCard, dog, cat);
                        dog.calculate(dog.usedCard, dog, cat);
                    }

                    if(cat.usedCard.getType().equals("stun") && dog.usedCard.getType().equals("stun")){
                        cat.stunTurnsRemaining = 0;
                        dog.stunTurnsRemaining = 0;
                    }

                    ///////////// THIS IS WHERE THE RANDOM CARD DRAWN FROM BOTH PLAYERS HAPPEN /////////////////////////

                    Random random = new Random();
                    int cardCat = random.nextInt(31) + 20;
                    int cardDog = random.nextInt(31) + 20;
                    cat.randomCard(cardCat);
                    dog.randomCard(cardDog);
                    try{
                        Thread.sleep(7000);
                    } catch(InterruptedException e){
                        System.out.println("The sleep was interrupted");
                    }
                }
            } 
        }
        System.out.println();
        if(dog.dogHealth <= 0){
            clearScreen();
            System.out.println("Cat Is the Winner!!!");
            System.out.println("Congratulation!!");
        }
        else{
            clearScreen();
            System.out.println("Dog Is the Winner!!!");
            System.out.println("So Sorry, Better Next Time");
        }
        scanner.close();
    }
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}