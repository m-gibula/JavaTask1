# PasteBin

Simple web application which let you share data.

## Goal

Your have to write PasteBin application.
 
 **You are allowed to modify only files in `src/main` directory**

Write two Servlets and Filter. 

'AuthFilter' should:
* if cookie exists:
    * if name and value is valid - accept
    * else - nothing
* else:
    * if parameter `pw` don't exist - print `Missing password`
    * if parameter value not equal to `passwd` - print `Wrong password`
    * else - create valid Cookie and accept

First 'SimpleServlet' in doGet() method should print 'form' named <form> with asked elements:
* 'title' named, 'text' type `input`
* 'content' named `textarea`
* 'submit' named, 'submit' type `input`,

on doPost() should resend request to doPost() in 'PasteServlet'.


'PasteServlet' doPost() should : 
- generate key for identify 'paste'
- take parameters and put them to `Map<>` with generate KEY (you are allowed to use Paste class)
- put Map to application context
- print `<a>` to a 'paste' which contain pair 'attrName = KEY'


'PasteServlet' doGet() should:
- take Map from application context
- take parameter 'attrName'
- print concatenate 'title' and 'content' values


<font color="green"> Some green text </font>

## To verify your solution

    mvn test
    
or 

    mvn clean test

## To deploy application

    mvn clean install
    
or

    mvn clean install -D skipTests