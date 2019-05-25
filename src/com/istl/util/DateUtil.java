/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    Date date= new Date();

    public DateUtil() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   public String Date(Date date){
       DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
       return  dateFormat.format(date);
   
   }
   
   
    
    
}
