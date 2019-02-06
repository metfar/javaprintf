# javaprintf
Just another implementation of printf (c/php-like) and print (Python-like) <and some more functions/> in java
                                                   
                                                   <metfar@gmail.com>

 
# History

Every time I start to study a new language, I miss the lost structures/commands/statements.
So I try to construct that lost functions in the new programming language I'm studying.
I started this practice a very long time ago, around 1988. 
This is my practical introduction to modern java language, dragging old customs.

# Implemented functions/procedures

print -> it is a function that prints to stdout whatever kind of object is submitted in its parameters. (Python's print-like)

printf-> provides a c/php-like printf (or an aproximation to it).

background(n)-> set paper colour to n (number or Pcolour).

foreground(n)-> set ink colour to n (number or Icolour).

printc-> set colours to background and foreground, and then, print.

input-> prompt a message and return a string row from stdin.

clrscr-> ansi code for clear screen;

gotoxy-> ansi code move cursor to x column, y row

type/typeof-> returns a string guessing which type of data is provided as a parameter.

isarray-> is true if the parameter is an array

isinteger-> is true if the parameter is an integer

isdouble-> is true if the parameter type is double

len-> return string.length or array.length

rnd-> random double number; the parameter is a multiplying coefficient.

rndInt-> random integer number; the parameter is a multiplying coefficient.


# Constants

I*  -> Ink (foreground) colours to console with ansi codes { IGREY; IBLACK; IRED; IGREEN; IYELLOW; IBLUE; IMAGENTA; ICYAN; IWHITE }

P*  -> Paper (background) colours to console with ansi codes { PGREY; PBLACK; PRED; PGREEN; PYELLOW; PBLUE; PMAGENTA; PCYAN; PWHITE }

# Contribution

If you want to contribute, contact <metfar@gmail.com>

Thanks a lot! w



