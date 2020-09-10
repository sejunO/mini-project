package mini.project.domain;

import mini.project.util.ArrayList;

public class Member {
  private String tel;
  private String name;
  private String password;
  private String bookstatus;
  public ArrayList<String> book = new ArrayList<>();

  public String getbookstatus() {
    return bookstatus;
  } // 대여 상태 가져오기

  public void setbookstatus(String bookstatus) {
    this.bookstatus = bookstatus;
  } // 대여 상태 설정

  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  @Override
  public String toString() {
    return "Member [tel=" + tel + ", name=" + name + ", password=" + password + ", book=" + book
        + "]";
  }

}

