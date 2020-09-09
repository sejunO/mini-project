package mini.project.handler;

import java.util.Iterator;
import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookHandler {
  List<Book> bookList;
  List<Book> availableBookList;
  List<Book> unavailableBookList;
  public BookHandler(
      List<Book> bookList , List<Book> availableBookList, List<Book> unavailableBookList) {
    this.bookList = bookList;

  }


  public void add() {
    System.out.println("[도서 등록]");

    Book book = new Book();
    book.setNo(Prompt.inputInt("도서 코드를 입력해주세요 > "));
    book.setTitle(Prompt.inputString("제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString("저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString("출판사를 입력해주세요 > "));
    book.setReceivingDate(new java.sql.Date(System.currentTimeMillis()));
    book.setAvailable(true);

    bookList.add(book);

  }

  public void list() {
    System.out.println("[도서 목록]");

    Iterator<Book> iterator = bookList.iterator();

    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n",
          book.getNo(),
          book.getTitle(),
          book.getAuthor(),
          book.getPublisher(),
          book.getReceivingDate());
    }
  }

  public void delete() {
    System.out.println("삭제한다.");

  }

  public void update() {
    System.out.println("수정한다.");

  }

  public void detail() {

    String title = Prompt.inputString("대여할 도서 제목을 입력해주세요: ");
    for(int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle().equalsIgnoreCase(title)) {
        if ( book.isAvailable()) {
          System.out.printf(title + " 도서는 현재 대여 가능합니다.");
          String response = Prompt.inputString("대여 하시겠습니까? (y/N) ");
          if (response.equalsIgnoreCase("y")) {
            borrowBook(book);
          }
        } else {
          System.out.printf(title + " 도서는 현재 대여 불가능합니다.");
        }
      } else 
        System.out.println("도서를 찾을 수 없습니다.");
    }
  }

  public void borrowBook(Book book) {
    book.setAvailable(false);
  }
}










