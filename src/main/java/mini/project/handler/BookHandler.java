package mini.project.handler;

import java.sql.Date;
import java.util.Scanner;
import mini.project.domain.Book;
import mini.project.util.Iterator;
import mini.project.util.List;
import mini.project.util.Prompt;

public class BookHandler {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록
  List<Book> unavailableBookList; // 이미 대여된 도서 목록

  public BookHandler(
      List<Book> bookList , List<Book> availableBookList, List<Book> unavailableBookList) {
    this.bookList = bookList;
    this.availableBookList =availableBookList;
    this.unavailableBookList = unavailableBookList;

  }

  //도서 등록
  public void add() {
    System.out.println("\n--------------\n [도서 등록]"
        + "\n--------------");
    System.out.println("\n   도서를 등록합니다."
        + "\n");
    Book book = new Book();
    Scanner sc = new Scanner(System.in);
    String keyboardscan = sc.nextLine();
    while (true) {
      int cnt=0;
      System.out.println("도서 코드를 입력해주세요 > ");
      String temp = sc.nextLine();
      for (int i = 0; i < bookList.size(); i++) {
        if (temp.equals(bookList.get(i).getNo())) {
          cnt++;
          System.out.println("도서 번호 중복입니다. 다시 입력하세요.");
          break;
        } // end if
      } // end for
      if(cnt == 0) {
        book.setNo(temp);
        break;
      }
    } // end while
    book.setNo(Prompt.inputInt(" 도서 코드를 입력해주세요 > "));
    book.setTitle(Prompt.inputString(" 제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString(" 저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString(" 출판사를 입력해주세요 > "));
    book.setAvailable(true);
    book.setReceivingDate(new Date(System.currentTimeMillis()));
    book.setViewCount(0);

    bookList.add(book);

    System.out.println("도서 등록을 완료하였습니다.");

  }

  //도서 목록 선택
  public void list() {
    loop:
      while (true) {
        String command = Prompt.inputString("\n---------------------\n"
            + "[ 도서 목록 및 검색 ]"
            + "\n---------------------\n" +
            " 1.전체 목록 2.대여 가능 목록 3.대여 도서 목록 4.이전으로\n" +
            "\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": list1(); break;
          case "2": availableList(); break;
          case "3": unavailableList(); break;
          case "4": 
            System.out.println("이전으로 갑니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println(); 
      }
  }

  //전체목록
  public void list1() {
    System.out.println("\n---------------------\n[도서 전체 목록]"
        + "\n---------------------\n"); 

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
  // 대여 가능 도서 목록
  public void availableList() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.isAvailable()) {
        availableBookList.add(book);
      }
    }
    System.out.println("\n----------------\n[대여 가능 목록]\n----------------");
    Iterator<Book> iterator = availableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("[ "+ book.getTitle() +" ]" + " , ");
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

  // 이미 대여된 도서 목록
  public void unavailableList() {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (!book.isAvailable()) {
        unavailableBookList.add(book);
      }
    }
    System.out.println("[대여된 도서 목록]");
    Iterator<Book> iterator = unavailableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf(book.getTitle() + ", ");
    }
  }

  //도서 정보 변경
  public void update() {
    System.out.println("\\n---------------------\n[도서 정보 변경]"
        + "\n---------------------\n");
    int no = Prompt.inputInt("변경할 도서 코드를 입력해주세요 > ");
    Book book = findByNo(no);

    if (book == null) {
      System.out.println("해당 코드의 도서가 없습니다.");
      return;
    }

    String title = Prompt.inputString(
        String.format("해당 코드의 도서 제목은 [ %s ] 입니다.\n변경할 도서 제목을 입력해주세요 > ", book.getTitle()));
    String author = Prompt.inputString(
        String.format("해당 코드의 도서 저자는 [ %s ] 입니다.\n변경할 도서 저자를 입력해주세요 > ", book.getAuthor()));
    String publisher = Prompt.inputString(
        String.format("해당 코드의 도서 출판사는 [ %s ] 입니다.\n변경할 도서 출판사를 입력해주세요 > ", book.getPublisher()));


    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("도서 정보 변경을 취소하였습니다.");
      return;
    }

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublisher(publisher);
    System.out.println("도서 변경을 완료하였습니다.");

  }

  // 도서 대여/반납 선택
  public void rental() {
    loop:
      while (true) {
        String command = Prompt.inputString("\n---------------------\n[ 도서 대여 및 반납 ]"
            + "\n---------------------\n" +
            " 1.도서 대여 \n 2.도서 반납 \n 3.이전으로\n\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": rental1(); break;
          case "2": returnBook(); break;
          case "3": 
            System.out.println("이전으로 갑니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println(); 
      }
  }

  // 도서 대여
  public void rental1() {
    String title = Prompt.inputString("\n 대여할 도서 제목을 입력해주세요 > ");

    for(int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
        System.out.printf("[ "+title+" ]"+ " 도서는 현재 대여 가능합니다.");
        String response = Prompt.inputString(" 대여 하시겠습니까? (y/N) ");
        if (response.equalsIgnoreCase("y")) {
          String name = Prompt.inputString("\n 대여자를 입력해주세요 > ");
          System.out.println("\n대여일자는 " + new Date(System.currentTimeMillis()) + " 입니다.\n");
          System.out.println("[ "+name+" ]님은 "+"[ "+title+" ]"+" 도서를 대여하셨습니다 !");
          borrowBook(book);
        }
      } else {
        System.out.printf("[ "+title+" ]"+" 도서는 존재하지 않거나 현재 대여 불가능합니다.");
      } 
    }
  }
  // 도서 반납
  public void returnBook() {
    String title = Prompt.inputString("반납할 도서 제목을 입력해주세요: ");
    for(int i = 0; i < unavailableBookList.size(); i++) {
      Book book = unavailableBookList.get(i);
      if (title.equalsIgnoreCase(book.getTitle()) && !book.isAvailable() ) {
        book.setAvailable(true);
        System.out.println("[ "+title+" ]"+" 도서가 반납되었습니다.");
      }
    }
  }

  // 등록된 도서 삭제
  public void delete() {
    loop:
      while (true) {
        String command = Prompt.inputString("\n---------------------\n[ 도서 삭제 ]"
            + "\n---------------------\n" +
            " 1.전체 삭제 2.개별 삭제 3.이전으로\n\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": delete1(); break;
          case "2": delete2(); break;
          case "3": 
            System.out.println("이전으로 갑니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println(); 
      }
  }

  //도서 전체 삭제
  private void delete1() {

    System.out.println("\n---------------------\n[도서 전체 삭제]"
        + "\n---------------------\n");
    System.out.println("y를 입력하시면 도서의 목록이 전체 삭제됩니다.\n");
    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("도서 전체 삭제를 취소하였습니다.");
      return;
    } else {
      for (int i = bookList.size(); i > bookList.size(); i--) {
        bookList.remove(i);
      }
    }
    System.out.println("\n도서를 전체 삭제하였습니다.");
  }

  // 해당 코드 도서 삭제
  public void delete2() {
    System.out.println("\n---------------------\n[도서 삭제]"
        + "\n---------------------\n");
    int no = Prompt.inputInt("도서 코드를 입력해주세요 ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 코드의 도서가 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("해당 도서의 삭제를 취소하였습니다.");
      return;
    }

    bookList.remove(index);
    System.out.println("해당 도서를 삭제하였습니다.");

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
      Book board = bookList.get(i);
      if (board.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

  public void borrowBook(Book book) {
    book.setAvailable(false);
  }
}
