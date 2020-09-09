package mini.project.handler;

import java.sql.Date;
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
    this.availableBookList =availableBookList;
    this.unavailableBookList = unavailableBookList;

  }


  public void add() {
    System.out.println("[도서 등록]");

    Book book = new Book();
    book.setNo(Prompt.inputInt("도서 코드를 입력해주세요 > "));
    book.setTitle(Prompt.inputString("제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString("저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString("출판사를 입력해주세요 > "));
    book.setAvailable(true);
    book.setReceivingDate(new Date(System.currentTimeMillis()));
    book.setViewCount(0);

    bookList.add(book);

    System.out.println("도서 등록을 완료하였습니다.");

  }

  public void list() {
    System.out.println("[도서 전체 목록]"); // 전체목록

    Iterator<Book> iterator = bookList.iterator();

    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("도서 코드 : %d\n"
          + "도서 제목 : %s\n"
          + "도서 저자 : %s\n"
          + "도서 출판사 : %s\n"
          + "도서 입고일 : %s\n"
          + "-----------------------------\n",
          book.getNo(),
          book.getTitle(),
          book.getAuthor(),
          book.getPublisher(),
          book.getReceivingDate(),
          book.getViewCount());
    }
  }

  public void availableList() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.isAvailable()) {
        availableBookList.add(book);
      }
    }
    System.out.println("대여 가능 목록");
    Iterator<Book> iterator = availableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf(book.getTitle() + ", ");
    }
    //    StringBuilder books = new StringBuilder();
    //    for (int i = 0; i < bookList.size(); i++) {
    //      String availableBook = bookList.get(i).getTitle();
    //
    //      if (bookList.get(i).isAvailable()) {
    //        books.append(availableBook + ", ");
    //      }
    //    }
    //    System.out.printf("현재 %s 대여 가능합니다", books);
    //  
  }

  public void update() {

  }

  public void detail() {
    String title = Prompt.inputString("대여할 도서 제목을 입력해주세요: ");
    for(int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
        System.out.printf(title + " 도서는 현재 대여 가능합니다.");
        String response = Prompt.inputString(" 대여 하시겠습니까? (y/N) ");
        if (response.equalsIgnoreCase("y")) {
          borrowBook(book);
          System.out.println(title + " 도서를 대여하였습니다.");
        }
      } else {
        System.out.printf(title + " 도서는 현재 대여 불가능합니다.");
      }
    }
  }

  public void returnBook() {
    String title = Prompt.inputString("반납할 도서 제목을 입력해주세요: ");
    for(int i = 0; i < unavailableBookList.size(); i++) {

    }
  }


  public void delete() {
    System.out.println("[도서 삭제]");
    int no = Prompt.inputInt("도서 코드를 입력해주세요 ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 코드의 도서가 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    bookList.remove(index);
    System.out.println("도서를 삭제하였습니다.");

  }




  private int indexOf(int no) {
    for (int i = 0; i < bookList.size(); i++) {
      Book board = bookList.get(i);
      if (board.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

  public void borrowBook(Book book) {
    book.setAvailable(false);
    unavailableBookList.add(book);
  }
}










