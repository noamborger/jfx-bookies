package il.ac.hit.jfxbookies.library.managing;


import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.Client;

import java.util.Calendar;

public class BorrowBook {
    private Client client;
    private Calendar date;

    //set functions
    public void setDate(Calendar date) {
        this.date = date;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    //get functions
    public Calendar getDate(){
        return date;
    }
    public Client getClient(){
        return client;
    }

    //other functions
    public void lendBook(Book book, Client client){


    }
    public void returnBook(Book book, Client client){

    }

}
