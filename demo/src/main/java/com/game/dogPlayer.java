package com.game;

import java.util.*;

public class dogPlayer {
    public int dogHealth = 100;
    public LinkedList<CardInfo> cardsHeld= new LinkedList<CardInfo>();
    public CardInfo usedCard = null;
    public int stunTurnsRemaining = 0;
    public void draw(Queue<CardInfo> dogType){
        if((cardsHeld.size() >= 0  && cardsHeld.size() < 3) || cardsHeld.isEmpty()){
            for(int i=0; i<3; i++){
                cardsHeld.add(dogType.remove());
            }
        }
        else if(cardsHeld.size() > 2 && cardsHeld.size() < 4){
            for(int i=0; i<2; i++){
                cardsHeld.add(dogType.remove());
            }
        }
        else{
            return;
        }
    }
    public void useCard(){
        Random random = new Random();
        CardInfo card = cardsHeld.remove(random.nextInt(cardsHeld.size()));
        usedCard = card;
    }
    public void calculate(CardInfo dogCard, dogPlayer dog, catPlayer cat){
        if(dogCard.getType().equals("damage")){
            cat.catHealth-=dogCard.getValue();
        }
        else if(dogCard.getType().equals("heal")){
            if(dog.dogHealth == 100){
                return;
            }
            else{
                if(dogCard.getValue() > (100-dog.dogHealth)){
                    dog.dogHealth += (100-dog.dogHealth);
                }
                else{
                    dog.dogHealth += dogCard.getValue();
                }
            }
        }
        else if(dogCard.getType().equals("steal")){
            if(dog.dogHealth == 100){
                cat.catHealth -= dogCard.getValue();
            }
            else{
                if(dogCard.getValue() >= (100-dog.dogHealth)){
                    cat.catHealth -= dogCard.getValue();
                    dog.dogHealth += (100-dog.dogHealth);
                }
                else{
                    dog.dogHealth += dogCard.getValue();
                    cat.catHealth -= dogCard.getValue();  
                }
            }
        }
        else if(dogCard.getType().equals("stun")){
            cat.stunTurnsRemaining = 1;
        }
        else{
            return;
        }
    }
    public void reflect(CardInfo catCard, dogPlayer dog, catPlayer cat){
        //System.out.println("Suns korta suveike!!!");
        if(catCard.getType().equals("damage")){
            cat.catHealth -= catCard.getValue();
        }
        else if(catCard.getType().equals("steal") || catCard.getType().equals("heal")){
            if(dog.dogHealth == 100){
                return;
            }
            else{
                if(cat.usedCard.getValue() >= (100-dog.dogHealth)){
                    dog.dogHealth += (100-dog.dogHealth);
                }
                else{
                    dog.dogHealth += cat.usedCard.getValue();
                }
                if(catCard.getType().equals("steal")){
                    cat.catHealth -= cat.usedCard.getValue();
                }
            }
        }
        else{
            cat.stunTurnsRemaining = 1;
        }
    }
    public void randomCard(int card){
        int index = 0;
        for(int i=0; i<card; i++){
            index = (index + 1) % cardsHeld.size();
        }
        System.out.println("Dog's removed random card is: " + (index+1) + ". " + cardsHeld.remove(index));
        // index = (index - 1 + cardsHeld.size()) % cardsHeld.size();
        
    }
}