package database;

import model.Student;

import java.sql.*;
import java.util.*;
import java.io.*;

public class StudentsDatabase extends AbstractDatabase<Student> {

    //Constructor
    public StudentsDatabase(String filename) {
        this.filename = filename;
    }

    //Methods
    @Override
    public void readFromFile() {
        try (Scanner sc = new Scanner(new File(this.filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                this.records.add(createRecordFrom(line));
            }
        } catch (Exception e) {
            System.out.println("Failed reading file " + this.filename + "!!");
        }
    }

    @Override
    public Student createRecordFrom(String line) {
        String[] parts = line.split(",");

        if(parts.length != 6){
            System.out.println("Incorrect line format!");
            return null;
        }

        return new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4],Float.parseFloat(parts[5]));
    }

    public ArrayList<Student> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for(int i = 0; i < this.records.size(); i++) {
            if(this.records.get(i).getId().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Student getRecord(String key){
        if(!this.contains(key)) {
            System.out.println("There is no student with the ID " + key + "!!");
            return null;
        }
        else {
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getId().equals(key)) {
                    return this.records.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void insertRecord(Student record){
        if(this.contains(record.getId())) {
            System.out.println("Record with key " + record.getId() + " already exists.");
            return;
        }
        records.add(record);
        saveToFile();
    }
    @Override
    public void deleteRecord(String key){
        if(!this.contains(key)) {
            System.out.println("There is no student with the ID " + key + "!!");
            return;
        }
        else{
            for (int i = 0; i < this.records.size(); i++) {
                if (this.records.get(i).getId().equals(key)) {
                    this.records.remove(i);
                    System.out.println("Student with the ID " + key + " has been deleted");
                    return;
                }
            }
        }

    }
    @Override
    public void saveToFile(){
        try {
            // SORT students by ID before saving
            Collections.sort(records, Comparator.comparing(Student::getId));

            File file = new File(this.filename);
            PrintWriter writer = new PrintWriter(new FileWriter(file, false));

            for (Student student : this.records) {
                writer.println(student.StudentRepresentation());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing data to file " + filename + "!!");
        }
    }


}
