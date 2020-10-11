package com.example.finalprojectprep;

public class calData
{
    private String year;
    private String month;
    private String day;
    private String timeStart;
    private String timeOfDay;
    private String timeEnd;
    private String content;

    calData(String m, String d, String y, String ts, String te, String con)
    {
        year = y;
        month = m;
        day = d;
        timeStart = ts;
        timeEnd = te;
        content = con;
    }

    public void setYear(String y){ year = y;};
    public void setMonth(String m){ month = m;};
    public void setDay(String d){ day = d;};
    public void setTimeStart(String ts){ timeStart = ts;};
    public void setTimeEnd(String te){timeEnd = te;};
    public void setTimeOfDay(String tod){ timeOfDay = tod;};
    public void setContent(String con){content = con;};

    public String getYear(){ return year;};
    public String getMonth(){ return month;};
    public String getDay(){ return day;};
    public String getTimeStart(){ return timeStart;};
    public String getTimeEnd(){ return timeEnd;};
    public String getTimeOfDay(){ return timeOfDay;};
    public String getContent(){return content;};
}
