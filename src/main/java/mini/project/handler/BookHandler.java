package mini.project.handler;

import java.sql.Date;
import mini.project.domain.Book;
import mini.project.domain.Member;
import mini.project.util.Iterator;
import mini.project.util.List;
import mini.project.util.Prompt;

public class BookHandler {

  List<Book> bookList; // 전체 도서 목록
  List<Book> availableBookList; // 대여 가능 도서 목록
  List<Book> unavailableBookList; // 이미 대여된 도서 목록
  MemberHandler memberHandler;

  public BookHandler(
      MemberHandler memberHandler, List<Book> bookList ,
      List<Book> availableBookList, List<Book> unavailableBookList) {
    this.memberHandler = memberHandler;
    this.bookList = bookList;
    this.availableBookList =availableBookList;
    this.unavailableBookList = unavailableBookList;

  }

  //도서 등록
  public void add() throws InterruptedException {
    System.out.println("\n\n [도서 등록]");
    System.out.println("\n * 도서를 등록합니다.*\n");
    Book book = new Book();
    /*Scanner sc = new Scanner(System.in);
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
    } // end while*/
    //book.setNo(Prompt.inputInt(" 도서 코드를 입력해주세요 > "));
    loop:  
      while(true) {
        int no = Prompt.inputInt(" 도서 코드를 입력해주세요 > ");
        int count = 0;
        boolean count2 = true;
        while(count2) {
          for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getNo() == no) {
              count++;
              System.out.println("\n* 도서 코드가 중복됩니다. 다시 입력하세요. *");
              count2 = false;
            }
          }
          if (count == 0) {
            book.setNo(no);
            break loop;
          }
        }
      }
    book.setTitle(Prompt.inputString(" 제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString(" 저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString(" 출판사를 입력해주세요 > "));
    book.setAvailable(true);
    book.setReceivingDate(new Date(System.currentTimeMillis()));
    book.setViewCount(0);

    bookList.add(book);

    System.out.println("\n* 도서 등록을 완료하였습니다. *");
    Thread.sleep(700);

  }

  //도서 목록 선택
  public void list() throws InterruptedException {
    loop:
      while (true) {
        String command = Prompt.inputString("\n\n[ 도서 목록 및 검색 ]\n" +
            " 1.전체 목록\n 2.대여 가능 목록\n 3.대여 도서 목록\n 4.이전으로\n" +
            "\n"+ 
            "번호를 선택하세요 => ");
        switch (command) {
          case "1": list1(); Thread.sleep(700); break;
          case "2": availableList(); Thread.sleep(700); break;
          case "3": unavailableList(); Thread.sleep(700); break;
          case "4": 
            System.out.println("\n* 이전으로 갑니다. *");
            Thread.sleep(700);
            break loop;
          default:
            System.out.println("\n* 실행할 수 없는 명령입니다. *");
            Thread.sleep(700);
        }
        System.out.println(); 
      }
  }

  //전체목록
  public void list1() throws InterruptedException {
    System.out.println("\n\n[도서 전체 목록]\n"); 

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
      Thread.sleep(700);
    }
  }
  // 대여 가능 도서 목록
  // 현재 함수를 부를 때 마다 추가됨.
  // 수정 필요.
  public void availableList() throws InterruptedException {
    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      if (book.isAvailable()) {
        availableBookList.add(book);
      }
    }
    System.out.println("\n\n[대여 가능 목록]\n");
    Iterator<Book> iterator = availableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf("[ "+ book.getTitle() +" ]" + " , ");
      Thread.sleep(700);
    }
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


  // 이미 대여된 도서 목록
  public void unavailableList() throws InterruptedException {

    System.out.println("\n\n[대여된 도서 목록]");
    Iterator<Book> iterator = unavailableBookList.iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      System.out.printf(book.getTitle() + ", ");
      Thread.sleep(700);
    }
  }

  //도서 정보 변경
  public void update() throws InterruptedException {
    System.out.println("\n\n[도서 정보 변경]\n");
    int no = Prompt.inputInt("변경할 도서 코드를 입력해주세요 > ");
    Book book = findByNo(no);

    if (book == null) {
      System.out.println("* 해당 코드의 도서가 없습니다. *");
      Thread.sleep(700);
      return;
    }

    String title = Prompt.inputString(
        String.format("* 해당 코드의 도서 제목은 [ %s ] 입니다. *\n변경할 도서 제목을 입력해주세요 > ", book.getTitle()));
    String author = Prompt.inputString(
        String.format("* 해당 코드의 도서 저자는 [ %s ] 입니다. *\n변경할 도서 저자를 입력해주세요 > ", book.getAuthor()));
    String publisher = Prompt.inputString(
        String.format("* 해당 코드의 도서 출판사는 [ %s ] 입니다. *\n변경할 도서 출판사를 입력해주세요 > ", book.getPublisher()));


    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 도서 정보 변경을 취소하였습니다.*");
      Thread.sleep(700);
      return;
    }

    book.setTitle(title);
    book.setAuthor(author);
    book.setPublisher(publisher);
    System.out.println("\n* 도서 변경을 완료하였습니다.*");
    Thread.sleep(700);

  }

  // 도서 대여/반납 선택
  public void rental() throws InterruptedException {
    loop:
      while (true) {
        String command = Prompt.inputString("\n\n[ 도서 대여 및 반납 ]\n" +
            " 1.도서 대여 \n 2.도서 반납 \n 3.이전으로\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": rental1(); Thread.sleep(700); break;
          case "2": returnBook(); Thread.sleep(700); break;
          case "3": 
            System.out.println("\n* 이전으로 갑니다. *");
            Thread.sleep(700);
            break loop;
          default:
            System.out.println("* 실행할 수 없는 명령입니다. *");
            Thread.sleep(700);
        }
        System.out.println(); 
      }
  }

  // 도서 대여
  public void rental1() throws InterruptedException {
    String title = Prompt.inputString("\n 대여할 도서 제목을 입력해주세요 > ");

    for(int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);

      if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
        System.out.printf("[ "+title+" ]"+ "* 도서는 현재 대여 가능합니다. *");
        String response = Prompt.inputString(" 대여 하시겠습니까? (y/N) ");
        if (response.equalsIgnoreCase("y")) {
          // 대여자 검증
          String name = Prompt.inputString("\n 대여자를 입력해주세요 > ");
          if (memberHandler.findByName(name) == null) {
            System.out.println("* 등록된 회원이 아닙니다. *");
            Thread.sleep(700);
            return;
          }
          // 대여완료
          System.out.println("* [ "+name+" ]님은 "+"[ "+title+" ]"+" 도서를 대여하셨습니다. *");
          System.out.println("\n* 대여일자는 " + new Date(System.currentTimeMillis()) + " 입니다. *\n");
          // 대여자 정보에 대여도서 추가
          Member member = memberHandler.findByName(name);
          member.book.add(title);
          // 도서 대여 가능여부 변경
          borrowBook(book);
          // 대여된 도서 목록에 저장
          unavailableBookList.add(book);
          Thread.sleep(700);
          return;
        }
        if (response.equalsIgnoreCase("n")) {
          System.out.println("* 도서 대여를 종료합니다. *");
          Thread.sleep(700);
          return;
        }
      }
    }
    System.out.printf("[ "+title+" ]"+" 도서는 존재하지 않거나 현재 대여 불가능합니다.");
    Thread.sleep(700);

  }
  // 도서 반납
  //public void returnBook() throws InterruptedException {
  // title = Prompt.inputString("반납할 도서 제목을 입력해주세요: ");
  // 현재 반납자가 아닌사람이 반납해도 반납이 허용됨.
  // 도서는 반납되지만 회원 정보에서 빌린 도서로 표시 됨.

  public void returnBook() throws InterruptedException {
    String title = Prompt.inputString("반납할 도서 제목을 입력해주세요 > ");
    String name = Prompt.inputString("반납하시는 분 이름을 입력해주세요 > ");
    Member member = memberHandler.findByName(name);
    if (member == null) {
      System.out.println("등록된 회원이 아닙니다.");
      return;
    }

    for (int i = 0; i < member.book.size(); i++) {
      if (member.book.get(i).equalsIgnoreCase(name)) {
        member.book.remove(i);
      }
    }

    for(int i = 0; i < unavailableBookList.size(); i++) {
      Book book = unavailableBookList.get(i);
      if (title.equalsIgnoreCase(book.getTitle()) && !book.isAvailable() ) {
        book.setAvailable(true);
        System.out.println("[ "+title+" ]"+" 도서가 반납되었습니다.");
        System.out.println("\n반납일자는 " + new Date(System.currentTimeMillis()) + " 입니다.\n");
        Thread.sleep(700);
        return;
      }
    }
    System.out.println("[ "+title+" ]"+" 도서는 존재하지 않거나 현재 대여되지 않았습니다.");
    Thread.sleep(700);
  }

  // 등록된 도서 삭제
  public void delete() throws InterruptedException {
    System.out.println("\n\n [도서 삭제]");
    System.out.println("\n * 도서를 삭제합니다.*\n");
    int no = Prompt.inputInt("도서 코드를 입력해주세요 > ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("* 해당 코드의 도서가 없습니다. *");
      Thread.sleep(700);
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 해당 도서의 삭제를 취소하였습니다. *");
      Thread.sleep(700);
      return;
    }

    bookList.remove(index);
    System.out.println("* 해당 도서를 삭제하였습니다. *");
    Thread.sleep(700);

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
