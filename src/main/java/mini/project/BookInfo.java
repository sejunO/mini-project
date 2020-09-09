package mini.project;

import java.util.ArrayList;

public class BookInfo {
  ArrayList<Book> bookList = new ArrayList<>();

  public void init() {

    Book book1 = new Book();
    book1.setNo(1);
    book1.setTitle("refactoring");
    book1.setAuthor("");
    book1.setPublisher("");
    book1.setAvailable(true);
    bookList.add(book1);

    Book book2 = new Book();
    book2.setNo(1);
    book2.setTitle("java");
    book2.setAuthor("");
    book2.setPublisher("");
    book2.setAvailable(true);
    bookList.add(book2);

    Book book3 = new Book();
    book3.setNo(1);
    book3.setTitle("C");
    book3.setAuthor("");
    book3.setPublisher("");
    book3.setAvailable(true);
    bookList.add(book3);




  }


}



