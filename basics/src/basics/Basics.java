/*
 * Basics.java
 * 
 * Copyright 2019 William Martinez Bas <metfar@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

package basics;

import java.util.*;


/**
 *
 * @author wmartinez
 */
public class Basics {

    
    /*Ink Colours */
    static String IGREY=    "\u001b[1;30m";
    static String IBLACK=    "\u001b[0;30m";
    static String IRED=     "\u001b[1;31m";
    static String IGREEN=   "\u001b[1;32m";
    static String IYELLOW=  "\u001b[1;33m";
    static String IBLUE=    "\u001b[1;34m";
    static String IMAGENTA= "\u001b[1;35m";
    static String ICYAN=    "\u001b[1;36m";
    static String IWHITE=   "\u001b[1;37m";
    /*Paper Colours */
    static String PBLACK=    "\u001b[0;40m";
    static String PGREY=    "\u001b[1;40m";
    static String PRED=     "\u001b[1;41m";
    static String PGREEN=   "\u001b[1;42m";
    static String PYELLOW=  "\u001b[1;43m";
    static String PBLUE=    "\u001b[1;44m";
    static String PMAGENTA= "\u001b[1;45m";
    static String PCYAN=    "\u001b[1;46m";
    static String PWHITE=   "\u001b[1;47m";
    /*Reset colours*/
    static String NONE=    "\u001b[7;30;47m";
    
    public static void print(Object... args){
        String text="";
        for(final Object obj: args) {
            text=text+obj.toString();
                }
        System.out.print(text);
        
    }
    

    public static void printf(String argum,Object... args){
        String delim="%",arg=argum;
        int min,num,z=0,n,f;
        String[] par={" ","i","u","c","s","l","f","d"};
        String fmt;
        String type,impr="";
        String parts[]=arg.split(delim);
        for (f=0;f<parts.length;f++)
        {
            type="";
            
            min=parts[f].length();
            for(n=0;n<par.length;n++)
            {
                num=parts[f].indexOf(par[n]);
                if(num>0 && num<min){
                    min=num;
                    type=par[n];
                }
            }
            if(type.length()>0)
            {
                try{
                fmt="%"+parts[f].substring(0, min+1);
                impr=String.format(Locale.US,fmt,args[z]);
                print(impr);
                z++;
                } catch (Exception e){ }
            }
            else
                try{
                fmt="%"+parts[f];
                if(fmt.length()>1 && z<args.length)
                {
                    impr=String.format(Locale.US,fmt,args[z]);
                    print(impr);
                    z++;
                }
                else
                    if(parts[f].length()>0)
                        print("%",parts[f]);
                } catch (Exception e){
                    if (z==args.length)
                        {                            
                            for(n=f;n<parts.length;n++)
                                    print("%",parts[n]);
                            f=parts.length;
                            z++;
                        }
                }
            
            if(f<parts.length && min+1<=parts[f].length())
                print(parts[f].substring(min+1,parts[f].length()));
        }
        }
    
    public static void clrscr(){
        printf("%c[2J",0x1b);
    }
    
    public static void gotoxy(int x,int y){
        printf("%c[%d;%dH",0x1b,y,x);
    }
    
    /**
     *
     * @param args
     * @return
     */
    public static String typeof(Object... args){
        String type="Custom";
        try{
            if(args instanceof String[] || 
                args instanceof Integer[] ||
                args instanceof Double[]||
                args instanceof Float[])
                type="Array";
            else if(args[0] instanceof String)
                type="String";
            else if(args[0] instanceof Double)
                type="Double";
            else if(args[0] instanceof Integer)
                type="Integer";
            else if(args[0] instanceof Float)
                type="Float";
        } catch (Exception e){
            type="Error";
        }
        return(type);
    }

    /**
     *
     * @param args
     * @return
     */
    public static String type(Object... args){
        String typ=typeof(args);
        if("Array".equals(typ.substring(0,4)))
            typ="Array";
        return(typ);
        
    }
    
    /**
     * isarray
     * @param args
     * @return boolean
     */
    public static boolean isarray(Object... args){
        return("Array".equals(type(args).substring(0,5)));
    }
    
    /**
     * isstring
     * @param arg
     * @return boolean
     */
    public static boolean isstring(Object... args){
        return("String".equals(type(args)));
    }
    public static boolean isinteger(Object... args){
        return("Integer".equals(type(args)));
    }
    public static boolean isdouble(Object... args){
        return("Double".equals(type(args)));
    }
    public static int len(Object... args){
         int length=0;String a=args[0].toString();        
         print("c:",type(args),"\n");
         if(isstring(args))
             length=a.length();
         if(isarray(args))
             length=args.length;
         return(length);
        }
    
    public static double rnd(Object... args){            
            double val=Math.random();
            Integer aux;
            
            if(args.length==0)
                return(val* 100.0);
            else if(isinteger(args[0]))
            {
                aux=Integer.parseInt (args[0].toString());
                val*= aux.doubleValue();
            }
            else if(isdouble(args))
                val*= Double.parseDouble (args[0].toString());
            else
                val*=100.0;
            return(val);
        }
    public static int rndInt(Object... args){            
        double value=rnd(args);
        String chainVal;
        chainVal = String.format(Locale.US,"%d",Math.round(value));
        return(Integer.parseInt(chainVal));
    }
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        String[] a={"la","casa","roja"};
        String b="La casa roja";
        Double[] c={1.0,2.0,3.0};
        printf("%c[2J",0x1b);
        print(PBLUE,IRED);
        print("casa",(float)3.12,1.7,'\n'); //print like pythons
        print(IBLUE);
        printf("\n%5.05f %03d %d\n",3.12,3,1415);//printf like c/php
        print(PYELLOW,IRED);
        printf("%s \n%d %_\n","casa",3);
        print("\n");
        printf("\n%s\n",typeof((Object) a));
        print(IGREY);
        printf("\n%s\n",typeof((Object)c));
        gotoxy(1,1);
        print(rnd(50),"\n");
        print(PBLACK,IGREEN);
        gotoxy(50,10);
        print(rndInt(50));
        gotoxy(1,21);
        print(NONE);
    }
    
}
