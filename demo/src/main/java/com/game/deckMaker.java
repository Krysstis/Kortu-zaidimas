package com.game;

import java.util.*;
import com.game.cardMaker.Card;
public class deckMaker {
    public void deck(String player, Queue<CardInfo> deck) {
        List<Card> cards = cardMaker.readJsonFile(player);
        Set<Card> uniqueCards = new HashSet<>(cards);
        int uniqueCardCount = Math.max(13, (40 / 3)), randomIndex;
        List<Card> uniqueCardList = new ArrayList<>(uniqueCards);
        Random random = new Random();

        List<CardInfo> allCards = new ArrayList<>();

        for (int i = 0; i < uniqueCardCount; i++) {
            randomIndex = random.nextInt(uniqueCardList.size());
            Card uniqueCard = uniqueCardList.get(randomIndex);
            CardInfo cardInfo = new CardInfo(uniqueCard.getType(), uniqueCard.getName(), uniqueCard.getValue());
            allCards.add(cardInfo);
        }

        int remainingCards = 40 - allCards.size();
        for (int i = 0; i < remainingCards; i++) {
            randomIndex = random.nextInt(cards.size());
            Card card = cards.get(randomIndex);
            CardInfo cardInfo = new CardInfo(card.getType(), card.getName(), card.getValue());
            allCards.add(cardInfo);
        }

        Collections.shuffle(allCards);

        deck.addAll(allCards);
    }
}