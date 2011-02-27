package edu.arhs.first1100.util;

public class Log
{
    private static int defcon = 4;
    
    public static void defcon3(Object obj, String message)
    {
        if(defcon == 3)
        {
            System.out.print("[3] ");
            log(obj, message);
        }
    }

    public static void defcon2(Object obj, String message)
    {
        if(defcon == 2 || defcon == 3)
        {
            System.out.print("[2] ");
            log(obj, message);
        }
    }
    
    public static void defcon1(Object obj, String message)
    {
        if(defcon == 1 || defcon == 2 || defcon == 3)
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
    
    private static void log(Object obj, String message)
    {
        String name = obj.getClass().getName();
        System.out.println(name.substring(name.lastIndexOf('.')+1)+": "+message);
    }
}