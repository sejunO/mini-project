package mini.project;

public class BookHandler {

  BookInfo bookInfo;

  public BookHandler(BookInfo bookInfo) {
    BookList bookList = new BookList();
    this.bookInfo = bookInfo;
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
          book.getReceivingDate(Book));
    }
  }

  public static void delete() {
    System.out.println("삭제한다.");

  }

  public static void update() {
    System.out.println("수정한다.");

  }

  public static void detail() {
    System.out.println("대여 및 반납.");

  }


}
