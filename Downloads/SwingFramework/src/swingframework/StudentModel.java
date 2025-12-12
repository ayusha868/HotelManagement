/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingframework;

/**
 *
 * @author lenovo
 */
public class StudentModel {
    
    private String LmuID;
    private String FullName;
    private String Program;
    private String Contact;
    private int Age;
    
    public StudentModel(String LmuID, String FullName, String Program, String Contact, int  Age){
        this.LmuID = LmuID;
        this.FullName = FullName;
        this.Program = Program;
        this.Contact = Contact;
        this.Age = Age;
    }
    
    public String getLmuID(){
        return LmuID;
    }
    
    public void setLmuID(String LmuID){
        this.LmuID = LmuID;
    }
    
    public String getFullName(){
        return FullName;
    }
    
    public void setFullName(String FullName){
        this.FullName = FullName;
    }
    
    public String getProgram(){
        return Program;
    }
    
    public void setProgram(String Program){
        this.Program = Program;
    }
    
    public String getContact(){
        return Contact;
    }
    
    public void setContact(String Contact){
        this.Contact = Contact;
    }
    
    public int getAge(){
        return Age;
    }
    
    public void setAge(int Age){
        this.Age = Age;
    }
    
}
