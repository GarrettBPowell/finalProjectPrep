package com.example.finalprojectprep;

public class calData
{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String timeOfDay;

    public void setYear(int y){ year = y;};
    public void setMonth(int m){ month = m;};
    public void setDay(int d){ day = d;};
    public void setHour(int h){ hour = h;};
    public void setMinute(int m){ minute = m;};
    public void setTimeOfDay(String tod){ timeOfDay = tod;};

    public int getYear(int y){ return year;};
    public int getMonth(int m){ return month;};
    public int getDay(int d){ return day;};
    public int getHour(int h){ return hour;};
    public int getMinute(int m){ return minute;};
    public String getTimeOfDay(String tod){ return timeOfDay;};
}
