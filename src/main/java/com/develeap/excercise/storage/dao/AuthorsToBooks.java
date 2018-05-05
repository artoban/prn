package com.develeap.excercise.storage.dao;

import com.develeap.excercise.model.Book;

import java.util.*;

public class AuthorsToBooks {

    static Map<String,Integer> storageMap = new HashMap();

    public AuthorsToBooks() {}


    public static Map<String,Integer> getStorage() {
        return storageMap;
    }

    public static void createStorage(Map<String, Book> dataBookMap) {

        Iterator<Map.Entry<String, Book>> iterator = dataBookMap.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<String, Book> entry = iterator.next();
            /////System.out.println(entry.getKey() + ":" + entry.getValue());
            Book book = entry.getValue();

            String[] parts = book.getAuthorsBuffer().split(",");
            if(parts.length == 1) {
                String[] temp = parts;
                parts = new String[2];
                parts[0] = temp[0];
                parts[1] = "";
            }

            String[] lasts = parts[0].split("/");
            String[] firsts = parts[1].split("/");


            for(int i=0; i<lasts.length; i++) {
                StringBuffer authorBuffer = new StringBuffer(lasts[i] + "," + firsts[i]);
                storageMap.putIfAbsent(String.valueOf(authorBuffer),0);
                int count = storageMap.get(String.valueOf(authorBuffer));
                storageMap.put(String.valueOf(authorBuffer), ++count);
            }
        }
    }

}
