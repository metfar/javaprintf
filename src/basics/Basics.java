/*
 * Basics.java
 * 
 * Primary version of https://github.com/metfar/cForPy for Java, and 
 * https://github.com/metfar/javaprintf ,
 * Just a compilation of functions (remainings of BASIC/C/CPP/PHP).
 *
 * This library is a combination of a lot of previous jobs.  I started 
 * it when I began to study C language, with  a  QBasic  baggage.  So,
_* this  is  based  on  my  BASIC.h(1988), bib.h(1989), bibc.py (c/cpp
 * compat), func.py (basic functions), php.py (php compat).
 * 
 * This has no warranties.
 * 
 * Copyright 1988*- William Martinez Bas <metfar@gmail.com>
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

package checkctm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


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
     public static boolean exists(String argum) {
            boolean out;
            File tmpDir = new File(argum);
            out= tmpDir.exists();
            return(out);
     }
     public static String readParaFile(String argum) throws IOException{
        String out="";
        Scanner in;
        StringBuilder sb;
        if (!exists(argum))
            return(out);
        in = new Scanner(new FileReader(argum));
        sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next()).append(" ");
        }
        in.close();
        out=sb.toString();
        return(out);
    }
     private static String readLineByLine(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
 
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
        }
 
        return (contentBuilder.toString());
    }
    public static String read_file(String fileName){
        String out = "";
        if(exists(fileName))
            out=readLineByLine(fileName);
        return(out);
    }
    public static boolean put_file(String filename,String[] content){
        File fout = new File(filename);
        boolean ok=true;
        try{
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String content1 : content) {
                bw.write(content1);
                bw.newLine();
            }
            bw.close(); 
            
        } catch(IOException e){
            ok=false;
        }
        return(ok);
    }
    public static boolean put_file(String filename,String content){
        File fout = new File(filename);
        boolean ok=true;
        try{
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(content);
            bw.close(); 
            
        } catch(IOException e){
            ok=false;
        }
        return(ok);
    }
    
    public static String[][] read_csv(String fileName,String Sep){
        String[][] out=new String[0][];
        List<String[]> rowList = new ArrayList<String[]>();
        if (!exists(fileName))
            return(out);
        if(Sep==null)   Sep=",";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineItems = line.split(Sep);
                rowList.add(lineItems);
            }
            br.close();
        }
        catch(Exception e){
        }
        out = new String[rowList.size()][];
        for (int i = 0; i < rowList.size(); i++) {
            String[] row = rowList.get(i);
            out[i] = row;
        }
        return(out);
    }
    public static String rot13(String argum){
        String out=""; 
        for (int f = 0; f < argum.length(); f++) {
            char c = argum.charAt(f);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            out+=c;
        }
        return(out);
    }
    public static String[][] read_csv(String fileName){
        return(read_csv(fileName,","));
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
         //print("c:",type(args),"\n");
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
    
    
    public static String filter(Object argum){
        String out="";
        String eliminate="()[]{} ";
        String wtreplace=",;";
        String wbreplace="|||";
        try{ 
            out+=argum.toString();
            for(int f=0; f<eliminate.length();f++)
                out=out.replace(eliminate.substring(f,f+1),"");
            
            for(int f=0; f<wtreplace.length();f++)
                out=out.replace(wtreplace.substring(f,f+1),wbreplace.substring(f,f+1));
            
        } catch(Exception e){}
        return(out);
    }
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException {
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
        printf("%d\n",rndInt(50));
        if(args.length>0)
        {
            String fa=args[0];
            String out="";
            if(exists(fa)){
                printf("\n");
                String[][] fe=read_csv(fa,"\n");
                for (int f=0;f<fe.length;f++)
                {for (int n=0;n<fe[f].length;n++)
                    if(fe[f][n].length()>0)
                    {
                        print(""+rot13(fe[f][n])+"\n" );
                        out+=""+rot13(fe[f][n])+"\n";
                    }
                }
                put_file("outlog.txt",out);
            } else {
               print(PYELLOW,IRED);
               print("--------\n");
               for (int f=0;f<args.length;f++)
                   printf("%s ",rot13(args[f]));
               print("\n--------\n");
            }
        }
        gotoxy(1,21);
        print(NONE);
    }
    
}
