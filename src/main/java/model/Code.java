package model;

import java.util.Objects;

public final class Code {

  private String value;
  private int userId;
  private int goodId;

  public Code(String value, int userId, int goodId) {
    this.value = value;
    this.userId = userId;
    this.goodId = goodId;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getGoodId() {
    return goodId;
  }

  public void setGoodId(int goodId) {
    this.goodId = goodId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Code code = (Code) o;
    return userId == code.userId
            && goodId == code.goodId
            && Objects.equals(value, code.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, userId, goodId);
  }
}
