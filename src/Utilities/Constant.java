package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
  public static final String DATA_FILE = "Student.txt";
  public static final String CAMPUS_PATTERN = "^(SE|HE|DE|QE|CE)";
  public static final String ID_PATTERN = "^(SE|HE|DE|QE|CE)\\d{6}$";
  public static final String NAME_PATTERN = "[A-Za-z|\\s]{2,20}";
  public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  public static final String PHONE_PATTERN = "^(0?)(3[2-9]|5[689]|7[06789]|8[0-689]|9[0-46-9])[0-9]{7}$";
  public static final String MOUNTAIN_PATTERN = "^MT(0?[1-9]|1[0-3])$";
  public static final List<String> whitelistPhone = new ArrayList<>(Arrays.asList("086",
      "096",
      "097",
      "098",
      "032",
      "033",
      "034",
      "035",
      "036",
      "037",
      "038",
      "039",
      "091",
      "094",
      "088",
      "083",
      "084",
      "085",
      "081",
      "082"));
  public static final List<String> validMountainCode = new ArrayList<>();

}