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

    public static void print(Object... args){
        String text="";
        for(final Object obj: args) {
            text=text+obj.toString();
                }
        System.out.print(text);
        
    }
    

    public static void printf(String argum,Object... args){
        String delim="%",arg=argum;
        int min,num,z=0;
        String[] par={"i","u","c","s","l","f","d"};
        String fmt;
        String type;
        String parts[]=arg.split(delim);
        for (int f=0;f<parts.length;f++)
        {
            type="";
            
            min=parts[f].length();
            for(int n=0;n<par.length;n++)
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
                print(String.format(Locale.US,fmt,args[z]));
                z++;
                } catch (Exception e){
                    print("%");
                }
                
            }
            else
                try{
                fmt="%"+parts[f];
                print(String.format(Locale.US,fmt,args[z]));
                z++;
                } catch (Exception e){
                    
                }
            if(min+1<=parts[f].length())
                print(parts[f].substring(min+1,parts[f].length()));
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        print("casa",(float)3.12,1.7,'\n'); //print like pythons
        printf("\n%5.05f %03d %d\n",3.12,3,1415);//printf like c/php
        
    }
    
}
