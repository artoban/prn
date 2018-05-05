package com.develeap.excercise.action;

import com.develeap.excercise.model.Book;
import com.develeap.excercise.storage.dao.AuthorsToBooks;
import com.develeap.excercise.util.Helper;
import java.util.Iterator;
import java.util.Map;


public class Analizer {

    public static void MakeReport() {

        Map<String, Book> dataBookMap = Parser.dataBookMapCollector;
        AuthorsToBooks autors2Books = new AuthorsToBooks();

        autors2Books.createStorage(dataBookMap);
        Map<String, Integer> unsortedStorage = autors2Books.getStorage();

        System.out.println("\n");
        System.out.println("***********************************************************");

        System.out.println("There are " + dataBookMap.size() + " books and " + unsortedStorage.size() + " authors in this file.");

        Map<String, Integer> resultMap = Helper.SortMapByValues(unsortedStorage);

        System.out.println("The most productive authors are:");
        Iterator<Map.Entry<String, Integer>> iterator = resultMap.entrySet().iterator();
        int reqQuantity = 3;
        while (iterator.hasNext() && reqQuantity > 0) {
            Map.Entry<String, Integer> entry = iterator.next();
            StringBuffer temp = new StringBuffer(entry.getKey().replace(",", " "));

            String[] strTemp = temp.toString().split(" ");
            if(strTemp.length == 1) {
                String[] temp2 = strTemp;
                strTemp = new String[2];
                strTemp[0] = temp2[0];
                strTemp[1] = "";
            }

            System.out.println(" - " + strTemp[1] + " " + strTemp[0] + " (" + entry.getValue() + " titles)");
            reqQuantity--;
        }

        System.out.println("***********************************************************");
    }
}
