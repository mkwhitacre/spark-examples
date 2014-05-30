package com.mkwhitacre.spark.model;

import java.io.Serializable;
import java.util.Arrays;

public class LegoSet implements Serializable {

  private final int setId;
  private final String number;
  private final String variant;
  private final String theme;
  private final String subTheme;
  private final String year;
  private final String name;
  private final String minifigs;
  private final int pieces;
  private final double ukPrice;
  private final double usPrice;
  private final double caPrice;
  private final double euPrice;
  private final String imageUrl;
  private final boolean owned;
  private final boolean wanted;
  private final int qtyOwned;

  public LegoSet(final int setId,
                 final String number,
                 final String variant,
                 final String theme,
                 final String subTheme,
                 final String year,
                 final String name,
                 final String minifigs,
                 final int pieces,
                 final double ukPrice,
                 final double usPrice,
                 final double caPrice,
                 final double euPrice,
                 final String imageUrl,
                 final boolean owned,
                 final boolean wanted,
                 final int qtyOwned) {
    this.setId = setId;
    this.number = number;
    this.variant = variant;
    this.theme = theme;
    this.subTheme = subTheme;
    this.year = year;
    this.name = name;
    this.minifigs = minifigs;
    this.pieces = pieces;
    this.ukPrice = ukPrice;
    this.usPrice = usPrice;
    this.caPrice = caPrice;
    this.euPrice = euPrice;
    this.imageUrl = imageUrl;
    this.owned = owned;
    this.wanted = wanted;
    this.qtyOwned = qtyOwned;
  }

  public int getSetId() {
    return setId;
  }

  public String getNumber() {
    return number;
  }

  public String getVariant() {
    return variant;
  }

  public String getTheme() {
    return theme;
  }

  public String getSubTheme() {
    return subTheme;
  }

  public String getYear() {
    return year;
  }

  public String getName() {
    return name;
  }

  public String getMinifigs() {
    return minifigs;
  }

  public int getPieces() {
    return pieces;
  }

  public double getUkPrice() {
    return ukPrice;
  }

  public double getUsPrice() {
    return usPrice;
  }

  public double getCaPrice() {
    return caPrice;
  }

  public double getEuPrice() {
    return euPrice;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public boolean isOwned() {
    return owned;
  }

  public boolean isWanted() {
    return wanted;
  }

  public int getQtyOwned() {
    return qtyOwned;
  }

  @Override
  public String toString() {
    return "[LegoSet: Name " + name + " SetId " + setId + "]";
  }

  public static LegoSet parse(String legoCSV) {
    if (legoCSV == null || legoCSV.trim().length() == 0) {
      throw new IllegalArgumentException("The 'legoCSV' String cannot be empty.");
    }
    //SetID,Number,Variant,Theme,Subtheme,Year,Name,Minifigs,Pieces,UKPrice,USPrice,CAPrice,EUPrice,ImageURL,Owned,Wanted,QtyOwned

    String[] split = legoCSV.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    if (split.length != 17) {
      throw new RuntimeException("Incorrect number of values in string. [" + Arrays.toString(split) + "] expected 17 got " + split.length);
    }

    return new LegoSet(
        getIntValue(split[0], -1),     //SetID
        getStringValue(split[1]),  // Number
        getStringValue(split[2]),  // Variant
        getStringValue(split[3]),  // Theme
        getStringValue(split[4]),  // Subtheme
        getStringValue(split[5]),  // Year
        getStringValue(split[6]),  // Name
        getStringValue(split[7]),  // Minifigs
        getIntValue(split[8], -1),  // Pieces
        getDoubleValue(split[9], -1),  // UKPrice
        getDoubleValue(split[10], -1), // USPrice
        getDoubleValue(split[11], -1), // CA Price
        getDoubleValue(split[12], -1), // EU Price
        getStringValue(split[13]),  // ImageURL
        getBooleanValue(getStringValue(split[14]), false), // Owned
        getBooleanValue(getStringValue(split[15]), false), // Wanted
        getIntValue(split[16], -1) // QtyOwned
    );
  }

  private static boolean getBooleanValue(String value, boolean defaultValue) {
    if (value != null && value.length() > 0) {
      boolean v = value.equalsIgnoreCase("no");
      return !v;
    }
    return defaultValue;
  }

  private static double getDoubleValue(String value, double defaultValue) {
    if (value != null && value.length() > 0) {
      return Double.valueOf(value);
    }
    return defaultValue;
  }

  private static int getIntValue(String value, int defaultValue) {
    if (value != null && value.length() > 0) {
      return Integer.valueOf(value);
    }
    return defaultValue;
  }

  private static String getStringValue(String value) {
    return value.replace("\"", "");
  }
}
