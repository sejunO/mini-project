package mini.project.handler;

import java.util.List;
import mini.project.domain.Book;
import mini.project.util.Prompt;

public class BookHandler {
  List<Book> bookList;

  public BookHandler(List<Book> bookList) {
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

    bookList.add(book);

  }

  public void list() {
    System.out.println("[도서 목록]");

    Book[] books = bookList.toArray();

    for (Book book : books) {
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

  //  private Book findByTitle(String title) {
  //    for (int i = 0; i < bookList.size(); i++) {
  //      Book book = bookList.get(i);
  //      if (book.getTitle() == title) {
  //        return book;
  //      }
  //    }
  //    return null;
  //  }
  public void detail() {

    String name = Prompt.inputString("대여할 도서 제목을 입력해주세요: ");
    for(int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle() == name && book.isAvailable() == true) {
        System.out.println("대여가능합니다.");
      }
    }
    /* if (bookInfo.findByTitle(name).isAvailable() == true) {
            System.out.println(name + "도서는 현재 대여 가능합니다.");
          } else {
            System.out.println(name + "도서는 현재 대여가 불가능합니다.");
          }*/
  }

}








