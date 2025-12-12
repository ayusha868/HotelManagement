/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aayusharijal
 */
import java.util.Scanner;
public class Factorial {
     static int factorial(int n)
    {
        if(n == 1)
        {
            return 1;
        }
        else
        {
            return(n*factorial(n-1));
        }
    }
public static void main(String[] args)
{
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int num = sc.nextInt();
    int result = factorial(num);
    System.out.println("Factorial of " +num+  " is:" + result);
}
}
