package com.develeap.excercise.model;

public class Book {
    StringBuffer titleBuffer;
    StringBuffer authorsBuffer;

    public String getAuthorsBuffer() {
        return authorsBuffer.toString();
    }


    public Book (String titleStr) {
        this.titleBuffer = new StringBuffer(titleStr);
    }

    public void appendAuthors(String authorsStr) {
        if ((this.authorsBuffer != null)) {
            this.authorsBuffer.append(authorsStr);
        }
        else{
            this.authorsBuffer = new StringBuffer(authorsStr);
        }
    }

    public void appendTitle(String nameStr) {
        this.titleBuffer.append(nameStr);
    }

}