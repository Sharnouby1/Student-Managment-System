package model;

import database.StudentsDatabase;
import java.util.*;

public class Student {

    private String id;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private float gpa;

    //Constructor
    public Student(String id, String fullName, int age, String gender, String department, float gpa) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = validateGpa(gpa);
    }
    public Student() {}


    public float validateGpa(float gpa){
        if(gpa < 0.0f || gpa > 4.0f){
            return 0.0f;
        }
        return gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String StudentRepresentation() {
        return (id + "," + fullName + "," + age + "," + gender + "," + department + "," + gpa);
    }


    public Student[] viewAllStudents() {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        ArrayList<Student> students = db.returnAllRecords();
        Student[] arr = new Student[students.size()];
        return students.toArray(arr);
    }


    public void updateStudentById(String key) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        Student s = db.getRecord(key);

        if (s == null) {
            return;
        }

        db.saveToFile();
    }


    public void searchStudentById(String id) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        Student s = db.getRecord(id);

    }

    public void searchStudentByName(String fullName) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        Student s = db.getRecord(fullName);

    }


    public boolean addStudent(Student student) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        if (db.contains(student.getId())) {
            return false;
        }

        db.insertRecord(student);
        return true;
    }

    public boolean deleteStudentById(String key) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        if (!db.contains(key)) {
            return false;
        }

        db.deleteRecord(key);
        db.saveToFile();

        return true;
    }

    public boolean deleteStudentByName(String name) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        if (!db.contains(name)) {
            return false;
        }

        db.deleteRecord(name);
        db.saveToFile();
        return true;
    }


}
