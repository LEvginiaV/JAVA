package ru.Lopatina.addressbook.model;

public class Date {
    private final String day;
    private final String month;
    private final String year;

    public Date(String bday, String bmonth, String byear) {
        this.day = bday;
        this.month = bmonth;
        this.year = byear;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
