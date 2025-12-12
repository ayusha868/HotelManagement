/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingframework;
import java.util.LinkedList;


public class SelectionSort {
    
    public static void SelectionSortByAge(LinkedList<StudentModel> list){
            int size = list.size();
    
            for (int step = 0; step<size-1; step++)
            {
                int min_value = step;
            
            for (int i = step+1; i<size; i++)
            {
               if (list.get(i).getAge() < list.get(min_value).getAge())
               {
                   min_value = i;
               }
                       
            }//Store the student at the current position
            StudentModel temp = list.get(step);
            //Move the smallest (or required) student into the current position
            list.set(step, list.get(min_value));
            //Put the original student (stored or in temp) into the main position
            list.set(min_value, temp);
            }
}
    
    public static void SelectionSortByName(LinkedList<StudentModel> list)
    {
        int size =  list.size();
        
        for (int step = 0; step<size-1; step++)
            {
                int min_value = step;
            
            for (int i = step+1; i<size; i++)
            {
               if (list.get(i).getFullName().compareToIgnoreCase(list.get(min_value).getFullName())<0)
               {
                   min_value = i;
               }
            StudentModel temp = list.get(step);
            //Move the smallest (or required) student into the current position
            list.set(step, list.get(min_value));
            //Put the original student (stored or in temp) into the main position
            list.set(min_value, temp);
    }
}
}
