package com.game;

import java.util.*;

public class catPlayer {
    public int catHealth = 100;
    public LinkedList<CardInfo> cardsHeld= new LinkedList<CardInfo>();
    public CardInfo usedCard = null;
    public int stunTurnsRemaining = 0;
    public void draw(Queue<CardInfo> catType){

        if((cardsHeld.size() >= 0  && cardsHeld.size() < 3) || cardsHeld.isEmpty()){
            for(int i=0; i<3; i++){
                cardsHeld.add(catType.remove());
            }
        }
        else if(cardsHeld.size() > 2 && cardsHeld.size() < 4){
            for(int i=0; i<2; i++){
                cardsHeld.add(catType.remove());
            }
        }
        else{
            return;
        }
    }
    public void useCard(int option){
        CardInfo card = cardsHeld.remove(option);
        usedCard = card;
    }
    public void calculate(CardInfo catCard, dogPlayer dog, catPlayer cat){

        if(catCard.getType().equals("damage")){
            dog.dogHealth-=catCard.getValue();
        }
        else if(catCard.getType().equals("heal")){
            if(cat.catHealth == 100){
                return;
            }
            else{
                if(catCard.getValue() >= (100-cat.catHealth)){
                    cat.catHealth += (100-cat.catHealth);
                }
                else{
                    cat.catHealth += catCard.getValue();
                }
            }
        }
        else if(catCard.getType().equals("steal")){
            if(cat.catHealth == 100){
                dog.dogHealth -= catCard.getValue();
            }
            else{
                if(catCard.getValue() >= (100-cat.catHealth)){
                    dog.dogHealth -= catCard.getValue();
                    cat.catHealth += (100-cat.catHealth);
                }
                else{
                    cat.catHealth += catCard.getValue();
                    dog.dogHealth -= catCard.getValue();  
                }
            }
        }
        else if(catCard.getType().equals("stun")){
            dog.stunTurnsRemaining = 1;
        }
        else{
            return;
        }
    }
    public void reflect(CardInfo dogCard, dogPlayer dog, catPlayer cat){
        //System.out.println("Mano korta suveike!!!");
        if(dogCard.getType().equals("damage")){
            dog.dogHealth -= dogCard.getValue();
        }
        else if(dogCard.getType().equals("steal") || dogCard.getType().equals("heal")){
            if(cat.catHealth == 100){
                return;
            }
            else{
                if(dog.usedCard.getValue() >= (100-cat.catHealth)){
                    cat.catHealth += (100-cat.catHealth);
                }
                else{
                    cat.catHealth += dog.usedCard.getValue();
                }
                if(dogCard.getType().equals("steal")){
                    dog.dogHealth -= dog.usedCard.getValue();
                }
            }
        }
        else{
            dog.stunTurnsRemaining = 1;
        }
    }
    public void randomCard(int card){
        int index = 0;
        for(int i=0; i<card; i++){
            index = (index + 1) % cardsHeld.size();
        }
        System.out.println("Cat's removed random card is: " + (index+1) + ". " + cardsHeld.remove(index));
        // index = (index - 1 + cardsHeld.size()) % cardsHeld.size();
        
    }
}