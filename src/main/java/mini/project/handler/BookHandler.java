package mini.project.handler;

import java.sql.Date;
import mini.project.domain.Book;
import mini.project.util.Iterator;
import mini.project.util.List;
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
          book.getReceivingDate());
    }
  }

  public void update() {
    System.out.println("[도서 정보 변경]");
    int no = Prompt.inputInt("번호? ");
    Book board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(
        String.format("제목(%s)? ", board.getTitle()));
    String content = Prompt.inputString(
        String.format("내용(%s)? ", board.getContent()));
    String writer = Prompt.inputString(
        String.format("작성자(%s)? ", board.getWriter()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    board.setTitle(title);
    board.setContent(content);
    board.setWriter(writer);
    System.out.println("게시글을 변경하였습니다.");
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

private Book findByNo(int no) {
  for (int i = 0; i < bookList.size(); i++) {
    Book book = bookList.get(i);
    if (book.getNo() == no) {
      return book;
    }
  }
  return null;
}

private int indexOf(int no) {
  for (int i = 0; i < bookList.size(); i++) {
    Book book = bookList.get(i);
    if (book.getNo() == no) {
      return i;
    }
  }
  return -1;
}


}








