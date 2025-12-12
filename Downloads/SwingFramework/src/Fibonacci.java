/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aayusharijal
 */
public class Fibonacci {
    static int n1 = 0, n2 = 1, n3 = 0;
    
    static void fibo(int count)
    {
        if(count>0)
        {
            n3 = n1 +n2;
            n1 = n2;
            n2 = n3;
            System.out.print(" " +n3);
            fibo(count -1);
        }
    }
public static void main(String[] args)
{
    int count = 10;
    System.out.print(n1 + " " +n2);
    fibo(count-2);
}
}
