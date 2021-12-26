package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFormatCommon {
    public static DateFormat dateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }
}
