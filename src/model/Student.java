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

        Scanner input = new Scanner(System.in);

        System.out.print("Enter new full name: ");
        s.fullName = input.nextLine();

        System.out.print("Enter new age: ");
        s.age = input.nextInt();
        input.nextLine();

        System.out.print("Enter new gender: ");
        s.gender = input.nextLine();

        System.out.print("Enter new department: ");
        s.department = input.nextLine();


        float newGpa;
        while(true){
            try {
                System.out.print("Enter new GPA (0.0 to 4.0): ");
                newGpa = input.nextFloat();

                if(newGpa < 0.0 || newGpa > 4.0){
                    System.out.println("Invalid GPA! Try again.");
                    continue;
                }
                break;
            } catch(Exception e) {
                System.out.println("Please enter a valid number!");
                input.nextLine();
            }
        }
        s.gpa = newGpa;

        db.saveToFile();
        System.out.println("Student updated successfully!");
    }


    public void searchStudentById(String password) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        Student s = db.getRecord(password);

        if (s == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println("FOUND:");
            System.out.println("ID: " + s.id);
            System.out.println("Name: " + s.fullName);
            System.out.println("Age: " + s.age);
            System.out.println("Gender: " + s.gender);
            System.out.println("Department: " + s.department);
            System.out.println("GPA: " + s.gpa);
        }
    }

    public void searchStudentByName(String fullName) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        Student s = db.getRecord(fullName);

        if (s == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println("FOUND:");
            System.out.println("ID: " + s.id);
            System.out.println("Name: " + s.fullName);
            System.out.println("Age: " + s.age);
            System.out.println("Gender: " + s.gender);
            System.out.println("Department: " + s.department);
            System.out.println("GPA: " + s.gpa);
        }
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
            System.out.println("Student not found!");
            return false;
        }

        db.deleteRecord(key);
        db.saveToFile();

        System.out.println("Student deleted successfully!");
        return true;
    }

    public boolean deleteStudentByName(String fullName) {
        StudentsDatabase db = new StudentsDatabase("Students.txt");
        db.readFromFile();

        if (!db.contains(fullName)) {
            System.out.println("Student not found!");
            return false;
        }

        db.deleteRecord(fullName);
        db.saveToFile();

        System.out.println("Student deleted successfully!");
        return true;
    }


}
