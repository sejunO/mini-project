package mini.project;

import java.util.ArrayList;
import java.util.Iterator;

public class BookInfo {

  ArrayList<Book> bookList = new ArrayList<>();

  public void init() {

    Book book1 = new Book();
    book1.setNo(1);
    book1.setTitle("refactoring");
    book1.setAuthor(" ");
    book1.setPublisher(" ");
    book1.setAvailable(true);
    bookList.add(book1);

    Book book2 = new Book();
    book2.setNo(2);
    book2.setTitle("java");
    book2.setAuthor(" ");
    book2.setPublisher(" ");
    book2.setAvailable(true);
    bookList.add(book2);

    Book book3 = new Book();
    book3.setNo(3);
    book3.setTitle("C");
    book3.setAuthor(" ");
    book3.setPublisher(" ");
    book3.setAvailable(true);
    bookList.add(book3);

  }

  public String findByTitle(String title) {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle() == title) {
        return book.getTitle();
      }
    }
    return null;
  }

  public void list() {
    System.out.println("[프로젝트 목록]");
    Iterator<Book> iterator = bookList.iterator();

    while (iterator.hasNext()) {
      Book book = iterator.next();
      for (int i = 0; i < bookList.size(); i++) {
        System.out.printf("%d, %s \n",
            book.getNo(),
            book.getTitle());

      }
    }
  }
}



