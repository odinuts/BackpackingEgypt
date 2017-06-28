package com.odinuts.backpackingegypt.models;

import java.util.ArrayList;
import java.util.Locale;
import java.util.*;

public class Countries {

  public static List<String> getCountries() {
    Locale[] locales = Locale.getAvailableLocales();
    List<String> countries = new ArrayList<>();

    for (Locale locale : locales) {
      String country = locale.getDisplayCountry();
      if (country.trim().length() > 0 && !countries.contains(country)) {
        countries.add(country);
      }
    }
    Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
    return countries;
  }
}