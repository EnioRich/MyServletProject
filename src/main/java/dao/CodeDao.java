package dao;

import model.Code;

import java.util.ArrayList;
import java.util.List;

public final class CodeDao {

  private static final List<Code> CODES = new ArrayList<>();

  public void addCode(final Code code) {
    CODES.add(code);
  }

  public boolean checkCode(final Code code) {
    return CODES.contains(code);
  }
}