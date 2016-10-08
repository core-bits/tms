/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corebits.ericsson.tms.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tommy
 */
public class SplitDate {

    Date date;
    Calendar calendar;

    public SplitDate(Date date) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
    }

    public String getYear() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("YYYY");
        String year = localDateFormat.format(calendar.getTime());
        return year;
    }

    public String getMonth() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("MM");
        String month = localDateFormat.format(calendar.getTime());
        return month;
    }

    public String getTime() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(calendar.getTime());
        return time;
    }

    public static void main(String[] args) {
        SplitDate sd = new SplitDate(new Date());
        System.out.println("Year :" + sd.getYear() + ", Month :" + sd.getMonth() + ", Time :" + sd.getTime());
    }
}
