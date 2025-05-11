package com.game;

import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class cardMaker
{
    public static List<Card> readJsonFile(String player)
    {
        Gson jsonFile = new Gson();
        List<Card> read = null;

        try (FileReader reader = new FileReader(player)){
            Type list = new TypeToken<List<Card>>() {}.getType();
            read = jsonFile.fromJson(reader, list);
    
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        }
        return read;
    }

    public static class Card {
        private String type;
        private String name;
        private int value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}