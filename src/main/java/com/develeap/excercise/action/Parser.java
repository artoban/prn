package com.develeap.excercise.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.develeap.excercise.model.Book;


public class Parser {

    static String prevISBN = null;

    static boolean flag = false;

    public static  Map<String, Book> dataBookMapCollector  = new HashMap();

    public static void ReadFileLineByLine(String filename) {

        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String [] parsedLine = parseLine(line);
                boolean flag2 = false;

                if(parsedLine[2] != null && !parsedLine[2].equals(prevISBN)) {
                    do {
                        Book book = dataBookMapCollector.get(prevISBN);
                        //-----------
                        if(book == null) {
                            book = new Book(parsedLine[0]);
                            if(parsedLine[1] != null) {
                                book.appendAuthors(parsedLine[1]);
                            }
                            dataBookMapCollector.putIfAbsent(prevISBN, book);
                        }
                        //-----------
                        flag = false;
                        flag2 = false;
                        if(prevISBN != parsedLine[2]) {
                            prevISBN = parsedLine[2];
                            flag2 = true;
                        }

                    } while(flag2 != false);
                }
                else {
                    Book book = dataBookMapCollector.get(prevISBN);
                    if(book == null) {
                        book = new Book(parsedLine[0]);
                        if(parsedLine[1] != null) {
                            book.appendAuthors(parsedLine[1]);
                        }
                        dataBookMapCollector.putIfAbsent(prevISBN, book);
                    }
                    else {
                        if(parsedLine[0] != null) {
                            book.appendTitle(parsedLine[0]);
                        }
                        if(parsedLine[1] != null) {
                            book.appendAuthors(parsedLine[1]);
                        }
                        dataBookMapCollector.put(prevISBN, book);
                    }

                    flag = true;
                }
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            System.out.println("Contents of file:");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] parseLine(String line) throws  Exception {

        String[] parsedLine = new String[3];

        parsedLine = getLine(line);
        if(parsedLine[2] != null && flag == false) {
            prevISBN = parsedLine[2];
        }

        return parsedLine;
    }

    public static String[] getLine(String line) {
        String[] resultArray = new String[3];
        int titleLength = Math.min(line.length(), 40);
        int authorsLength = Math.min(line.length(), 30);

        String title = line.substring(0, titleLength).trim();

        int authorsEnd = Math.min( titleLength + authorsLength, line.length() );

        String authors = (line.length() > titleLength) ? line.substring(titleLength, authorsEnd).trim() : "";

        String isbn = line.substring(authorsEnd, line.length()).trim();

        if(!isbn.isEmpty()) {
            resultArray[2] = isbn;
        }
        if(!authors.isEmpty()) {
            resultArray[1]= authors;
        }
        if(!title.isEmpty()) {
            resultArray[0]= title;
        }

        return resultArray;
    }

}
