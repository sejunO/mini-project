package mini.project;

public class BookHandler {

  BookInfo bookInfo;

  public BookHandler(BookInfo bookInfo) {
    BookList bookList = new BookList();
    this.bookInfo = bookInfo;
  }
  public void checkBook() {
    System.out.println(bookInfo.findByInBook() + "입니다");
  }

  public static void add() {
    System.out.println("[도서 등록]");

    Book book = new Book();
    book.setNo(Prompt.inputInt("도서 코드를 입력해주세요 > "));
    book.setTitle(Prompt.inputString("제목을 입력해주세요 > "));
    book.setAuthor(Prompt.inputString("저자를 입력해주세요 > "));
    book.setPublisher(Prompt.inputString("출판사를 입력해주세요 > "));
    book.setReceivingDate(new java.sql.Date(System.currentTimeMillis()));

    BookList.add(book);

  }

  public static void list() {
    System.out.println("[도서 목록]");

    Book[] books = BookList.toArray();

    for (Book book : books) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          book.getNo(),
          book.getTitle(),
          book.getAuthor(),
          book.getPublisher(),
    }
  }

  public static void delete() {
    System.out.println("삭제한다.");

  }

  public static void update() {
    System.out.println("수정한다.");

  }

  public void detail() {

    String name = Prompt.inputString("찾는 도서 제목을 입력해주세요: ");

    if (bookInfo.findByTitle(name) != null) {
      System.out.println(name);
      /* if (bookInfo.findByTitle(name).isAvailable() == true) {
        System.out.println(name + "도서는 현재 대여 가능합니다.");
      } else {
        System.out.println(name + "도서는 현재 대여가 불가능합니다.");
      }*/
    } else {
      System.out.println(name + "을 찾을 수 없습니다.");
    }

  }




}




