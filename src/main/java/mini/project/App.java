package mini.project;

import java.util.LinkedList;
import java.util.List;
import mini.project.domain.Book;
import mini.project.handler.BookHandler;
import mini.project.util.Prompt;

public class App {
  public static void main(String[] args) {
    System.out.println("=========== 도서 관리 프로그램 ===========");
    System.out.println("        1. 도서 등록 \r\n" + 
        "        2. 도서 목록 및 검색\r\n" + 
        "        3. 도서 삭제\r\n" + 
        "        4. 도서 정보 수정\r\n" + 
        "        5. 도서 대여 및 반납\r\n" + 
        "        6. 종료");
    System.out.println("==========================================");

    List<Book> bookList = new LinkedList<>();
    BookHandler bookHandler = new BookHandler(bookList);

    loop:
      while (true) {
        String command = Prompt.inputString("번호를 선택해주세요 => ");

        switch (command) {
          case "1": bookHandler.add(); break;
          case "2": bookHandler.list(); break;
          case "3": bookHandler.delete(); break;
          case "4": bookHandler.update(); break;
          case "5": bookHandler.detail(); break;
          case "6": 
            System.out.println("도서 관리 프로그램을 종료합니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println(); 
      }

    Prompt.close();
    //OK!
  }
}

