/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aayusharijal
 */


public class StudentModel {
    
    private String lmuId;
    private String fullName;
    private String program;
    private String contact;
    private int age;

    // Default constructor (optional but useful)
    public StudentModel() { }

    // Parameterized constructor
    public StudentModel(String lmuId, String fullName, String program, String contact, int age) {
        this.lmuId = lmuId;
        this.fullName = fullName;
        this.program = program;
        this.contact = contact;
        this.age = age;
    }

    // Getter methods
    public String getLmuId() {
        return lmuId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProgram() {
        return program;
    }

    public String getContact() {
        return contact;
    }

    public int getAge() {
        return age;
    }

    // Setter methods
    public void setLmuId(String lmuId) {
        this.lmuId = lmuId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
