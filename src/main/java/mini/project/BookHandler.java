package mini.project;

public class BookHandler {

  BookInfo bookInfo;

  public BookHandler(BookInfo bookInfo) {
    this.bookInfo = bookInfo;
  }

  public void borrowBook() {

  }

  public void checkBook() {
    while (true) {
      String name = Prompt.inputString("찾는 도서 제목을 입력해주세요: ");
      for (int i = 0; bookInfo.findByTitle)
    }
  }

  public static void add() {
    System.out.println("추가한다.");

  }

  public static void list() {
    System.out.println("목록 보기 검색.");
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
