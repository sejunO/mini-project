package mini.project.handler;

import mini.project.util.Prompt;

public class BookRentalCommand implements Command {


  BookRental bookRental;
  BookReturn bookReturn;

  public BookRentalCommand(BookRental bookRental, BookReturn bookReturn) {

    this.bookRental = bookRental;
    this.bookReturn = bookReturn;
  }

  @Override
  public void execute() throws InterruptedException {
    loop: while (true) {
      String command = Prompt.inputString("\n\n\t\t[ 도서 대여 및 반납 ]\n"
          + "\t 1.도서 대여 \n\t 2.도서 반납 \n\t 3.이전으로\n\t" + "\n\t번호를 선택하세요 => ");
      switch (command) {
        case "1":
          bookRental.rental();
          Thread.sleep(200);
          break;
        case "2":
          bookReturn.returnBook();
          Thread.sleep(200);
          break;
        case "3":
          System.out.println("\n\t* 이전으로 갑니다. *");
          Thread.sleep(200);
          break loop;
        default:
          System.out.println("\n\t* 실행할 수 없는 명령입니다. *");
          Thread.sleep(200);
      }
      System.out.println();
    }
  }
}


