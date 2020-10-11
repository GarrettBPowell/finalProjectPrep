package com.example.finalprojectprep;

public class calData
{
    private int year;
    private int month;
    private int day;
    private String time;
    private String timeOfDay;

    calData(int y, int m, int d, String t)
    {
        year = y;
        month = m;
        day = d;
        time = t;
    }

    public void setYear(int y){ year = y;};
    public void setMonth(int m){ month = m;};
    public void setDay(int d){ day = d;};
    public void setHour(String t){ time = t;};
    public void setTimeOfDay(String tod){ timeOfDay = tod;};

    public int getYear(){ return year;};
    public int getMonth(){ return month;};
    public int getDay(){ return day;};
    public String getHour(){ return time;};
    public String getTimeOfDay(){ return timeOfDay;};
}
