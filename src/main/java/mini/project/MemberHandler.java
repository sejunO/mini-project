package mini.project;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberHandler {

  ArrayList<Member> memberList = new ArrayList<>();

  public void add() {

    Member member = new Member();

    member.setName(Prompt.inputString("name? : ")); 
    member.setEmail(Prompt.inputString("email? : ")); 
    member.setAge(Prompt.inputInt("age? : "));

    memberList.add(member);
  }

  public void list() {
    Iterator<Member> iterator = memberList.iterator();

    while(iterator.hasNext()) {
      Member member = iterator.next();
      System.out.printf("이름: %s, 이메일: %s, 나이: %d \n", 
          member.getName(), member.getEmail(), member.getAge() );
    }
  }


}
