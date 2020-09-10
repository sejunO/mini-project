package mini.project.handler;

import mini.project.domain.Member;
import mini.project.util.List;
import mini.project.util.Prompt;

public class MemberHandler {

  List<Member> memberList;

  public MemberHandler(List<Member> list) {
    this.memberList = list;
  }

  public void member() throws InterruptedException {
    loop:
      while (true) {
        String command = Prompt.inputString("\n\n[ 회원 등록 및 관리 ]\n"+
            " 1.회원 등록\n 2.회원 정보 변경\n 3.회원 상세정보\n 4.이전으로\n" +
            "\n"+ 
            "번호를 선택해주세요 => ");
        switch (command) {
          case "1": memberAdd(); Thread.sleep(500); break;
          case "2": memberSet(); Thread.sleep(500); break;
          case "3": memberDetail(); Thread.sleep(500); break;
          case "4":
            System.out.println("\n* 이전으로 갑니다. *");
            Thread.sleep(500);
            break loop;
          default:
            System.out.println("\n* 실행할 수 없는 명령입니다. *");
            Thread.sleep(500);
        }
        System.out.println(); 
      }
  }

  private void memberDetail() throws InterruptedException {
    System.out.println("\n\n[회원 상세정보]\n");
    System.out.println("* 회원 상세정보를 확인합니다 *");
    String findName = Prompt.inputString("\n확인할 회원의 이름을 입력해주세요 > ");
    Member member = findByName(findName);

    if (member == null) {
      System.out.println("\n* 해당 이름의 회원이 없습니다. *");
      Thread.sleep(500);
      return;
    }
    // 대여정보가 안나옴3 수정필요!
    System.out.printf("\n\n [ 회원 상세 정보 ] \n" 
        + "이 름 : %s\n" + "연락처 : %s\n" + "대여정보 : \n  ",
        member.getName(), member.getTel());
    for (int i = 0; i < member.book.size(); i++) {
      System.out.printf(member.book.get(i));
      System.out.printf(member.book.get(i) + ", ");
      Thread.sleep(500);
    }

  }

  private void memberSet() throws InterruptedException {
    System.out.println("\n\n[회원 정보 변경]\n");
    System.out.println("* 회원 정보를 변경합니다 * ");
    String findName = Prompt.inputString("\n정보를 변경할 회원의 이름을 입력해주세요 > ");
    Member member = findByName(findName);
    if (member == null) {
      System.out.println("* 해당하는 이름의 회원이 없습니다. *\n");
      Thread.sleep(500);
      return;
    }
    String pass = Prompt.inputString("회원 암호를 입력해야 변경됩니다. \n암호를 입력해주세요 > ");
    if (!member.getPassword().equalsIgnoreCase(pass)) {
      System.out.println("\n* 비밀 번호가 다릅니다.*");
      Thread.sleep(500);
      return;
    }

    String name = Prompt.inputString(
        String.format("\n* 현재 회원의 이름은 [ %s ] 입니다. *\n새로 변경할 이름을 입력해주세요 > ", member.getName()));
    String password = Prompt.inputString("새로 변경하실 암호를 입력하세요 > ");
    String tel = Prompt.inputString(
        String.format("\n* 현재 회원의 연락처는[ %s ] 입니다. *\n새로 변경할 연락처를 입력해주세요 >  ", member.getTel()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("* 회원 변경을 취소하였습니다. *");
      Thread.sleep(500);
      return;
    }

    member.setName(name);
    member.setPassword(password);
    member.setTel(tel);

    System.out.println("\n* [ "+ member.getName() +"] 님의" + " 정보를 변경하였습니다. *");
    Thread.sleep(500);
  }

  public void memberAdd() throws InterruptedException {
    System.out.println("\n\n[ 회원 등록 ]\n");
    System.out.println("* 회원 등록을 합니다 *\n");

    Member member = new Member();
    member.setName(Prompt.inputString("이름을 입력해주세요 > "));
    member.setPassword(Prompt.inputString("암호를 입력해주세요 > "));
    member.setTel(Prompt.inputString("연락처를 입력해주세요 > "));
    memberList.add(member);
    System.out.println("* [ "+ member.getName() +" ]" + "님 회원가입 감사합니다. *");
    Thread.sleep(500);
  }

  public Member findByName(String name) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }
}
