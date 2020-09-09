package mini.project;

public class BookHandler {

  BookInfo bookInfo;

  public BookHandler(BookInfo bookInfo) {
    this.bookInfo = bookInfo;
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




