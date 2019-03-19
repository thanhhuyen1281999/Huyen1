/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Book;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import util.MyList;

/**
 *
 * @author ThanhHuyen
 */
public class BookList {

    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    public BookList() {
        books = new MyList();
    }

    //1.0 accept information of a Book
    private Book getBook() {
        Scanner ip = new Scanner(System.in);
        String code = "";
        String title = "";
        int quantity = 0;
        int lended = 0;
        double price = 0;
        boolean l;
        //input information book
        do {
            System.out.println("Enter information of a Book");
            System.out.println("Book Code: ");
            code = ip.nextLine().trim();
            l = true;
            //check book code is null
            if (code.length() == 0) {
                System.out.println("Book code is not null!");
                l = false;
            }
            //check book code is exist 
            if (books.checkCode(code) == 1) {
                System.out.println("Book is exist");
                l = false;
            }
        } while (l != true);

        System.out.println("Book Title: ");
        title = ip.nextLine().trim();
        //check book title is null
        while (title.length() == 0) {
            System.out.println("Title is not null!");
            System.out.println("Title: ");
            title = ip.nextLine().trim();
        }

        do {
            try {
                System.out.println("Book Quantity: ");
                quantity = Integer.parseInt(ip.nextLine().trim());
                l = true;
                //check quantity must be greater than 0
                if (quantity <= 0) {
                    System.out.println("Quantity is greater than 0");
                    l = false;
                }
            } catch (Exception e) {//check quantity is character
                System.out.println("Error! Quantity is number!");
                l = false;
            }
        } while (l != true);

        do {
            try {
                System.out.println("Book Lended: ");
                lended = Integer.parseInt(ip.nextLine().trim());
                l = true;
                //check lended is less than 0
                if (lended < 0) {
                    System.out.println("Lended is greater than 0");
                    l = false;
                } else if (lended > quantity) {//check lended is greater than quantity
                    System.out.println("Please enter lended less than or equal quantity!");
                    l = false;
                }
            } catch (Exception e) {//check lended is not character
                System.out.println("Error! Lended is number!");
                l = false;
            }
        } while (l != true);

        do {
            try {
                System.out.println("Book Price: ");
                price = Double.parseDouble(ip.nextLine().trim());
                l = true;
                if (price <= 0) {//check price must be greater than 0
                    System.out.println("Price is greater than 0");
                    l = false;
                }
            } catch (Exception e) {//check price is not string
                System.out.println("Error! Price is real number!");
                l = false;
            }
        } while (l != true);
        //set book
        Book obj = new Book(code, title, quantity, lended, price);
        return obj;
    }

    //1.1 accept and add a new Book to the end of book list
    public void addLast() {
        books.addLast(getBook());
        //display if successful
        System.out.println("A new book has been added the end of book list!");
    }

    //1.2 output information of book list
    public void list() {
        //check list is empty
        if (books.isEmpty() == true) {
            System.out.println("List is empty");
        } else {//check list is not empty
            String n = String.format("%-10s%-20s%-10s%-10s%-10s%-10s", "Code", "Title", "Quantity", "Lender", "Price", "Value");
            System.out.println(n);
            books.traverse();
        }
    }

    //1.3 search book by book code
    public void search() {
        Scanner ip = new Scanner(System.in);
        String code = "";
        boolean l;
        do {//input book code
            System.out.println("Enter book code: ");
            code = ip.nextLine().trim();
            l = true;
            if (code.length() == 0) {//check book code is not null
                System.out.println("Book code is not null!");
                l = false;
            }
        } while (l != true);
        //check list is empty
        if (books.isEmpty() == false) {
            //check book code is exist
            if (books.checkCode(code) == 1) {
                books.search(code);
            } else {//check book code is not exist
                System.out.println("Book is not found!");
            }
        } else {//error if list is empty
            System.out.println("List book is empty");
        }
    }

    //1.4 accept and add a new Book to the begining of book list
    public void addFirst() {
        books.addFirst(getBook());
        System.out.println("A new book has been added the beginning of book list!");
    }

    //1.5 Add a new Book after a position k
    public void addAfter() {
        Scanner ip = new Scanner(System.in);
        //input information book
        Book n = getBook();
        int k = 0;
        boolean m;
        do {
            try {//input adding position
                System.out.println("Enter adding position: ");
                k = Integer.parseInt(ip.nextLine());
                m = true;
                if (k < 0) {//check position is less than 0
                    System.out.println("Position must be greater than 0!");
                    m = false;
                }
                //check position size of list 
                if (k >= 0 && k < books.size()) {
                    m = true;
                } else {//check invalid
                    m = false;
                    System.out.println("Position must be from 0 to " + (books.size() - 1));
                }
            } catch (Exception e) {//check position is chracter
                System.out.println("Error! Position must be number");
                m = false;
            }
        } while (m != true);
        //check list is not empty
        if (books.isEmpty() == false) {
            books.addAfter(n, k);
            System.out.println("A new book has been added a after position " + k);
        } else {//check list is empty
            System.out.println("List book is empty");
        }

    }

    //1.6 Delete a Book at position k
    public void deleteAt() {
        Scanner ip = new Scanner(System.in);
        int k = 0;
        boolean m;
        do {
            try {//input deleting position
                System.out.println("Enter deleting position: ");
                k = Integer.parseInt(ip.nextLine());
                m = true;
                if (k < 0) {//check position less than 0
                    System.out.println("Position must be greater than 0!");
                    m = false;
                }
                if (k >= 0 && k < books.size()) {//check size list valid                
                    m = true;
                } else {//check invalid
                    System.out.println("Position must be from 0 to " + (books.size() - 1));
                    m = false;
                }
            } catch (Exception e) {//check position is character
                System.out.println("Error! Position must be number");
                m = false;
            }
        } while (m != true);
        //check list is not empty
        if (books.isEmpty() == false) {
            books.deleteAt(k);
            System.out.println("A new book has been deleted in position " + k);
        } else {//check list is empty
            System.out.println("List book is empty");
        }
    }
}
