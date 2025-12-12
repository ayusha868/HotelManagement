/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aayusharijal
 */
import com.collegeapp.model.StudentModel;
import java.util.LinkedList;

public class Homepage extends JFrame {

    // Global Student List
    LinkedList<StudentModel> studentList = new LinkedList<>();

    public Homepage() {
        initComponents();
        prepareInitialData();
        loadStudentListToTable();
    }
}