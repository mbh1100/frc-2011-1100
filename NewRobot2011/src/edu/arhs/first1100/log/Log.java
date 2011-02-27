package edu.arhs.first1100.log;

import java.util.Vector;

public class Log
{
    private static int defcon = 4;
    private static Vector records;
    
    public static void defcon3(Object obj, String message)
    {
        if(defcon <= 3 && checkClass(obj))
        {
            System.out.print("[3] ");
            log(obj, message);
        }
    }

    public static void defcon2(Object obj, String message)
    {
        if(defcon <= 2 && checkClass(obj))
        {
            System.out.print("[2] ");
            log(obj, message);
        }
    }
    
    public static void defcon1(Object obj, String message)
    {
        if(defcon <= 1 && checkClass(obj))
        {
            System.out.print("[1] ");
            log(obj, message);
        }
    }

    public static void setDefconLevel(int level)
    {
        if(level == 1)
        {
            System.out.println("***********");
            System.out.println("* WARNING *");
            System.out.println("***********");
            System.out.println("DEFCON 1 ACTIVATED");
        }
        defcon = level;
    }

    public static void addClass(Class aClass)
    {
        records.addElement(aClass);
    }

    public static boolean checkClass(Object obj)
    {
        boolean record = false;
        for(int i = 0; i < records.size() && !record; i++)
        {
            if(obj.getClass().equals(records.elementAt(i))) record = true;
        }
        return record;
    }
    
    private static void log(Object obj, String message)
    {
        String name = obj.getClass().getName();
        System.out.println(name.substring(name.lastIndexOf('.')+1)+": "+message);
    }
}