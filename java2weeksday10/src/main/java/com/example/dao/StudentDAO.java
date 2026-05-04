package com.example.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;
public class StudentDAO {
    //Connect DB
    private final String url = "jdbc:mysql://localhost:3306/student_management";
    private final String user = "root";
    private final String password = "Dao@20/08/2002";
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        }catch(SQLException e){
            throw new RuntimeException("Loi ket noi Database!!!\n", e);
        }
    }
    //Gọi file
    //Insert 
    public int insertStudent(String id, String name, int age, String email){
        String sql = "INSERT INTO student (id, name, age, email) VALUES (?, ?, ?, ?)";
        try(Connection con = getConnection(); 
        PreparedStatement prep = con.prepareStatement(sql)){
            //Gán giá trị
            prep.setString(1, id);
            prep.setString(2, name);
            prep.setInt(3, age);
            prep.setString(4, email);
            //Thực thi sql
            return prep.executeUpdate();
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
        return 0;
    }
    //Select
    public List<Student> selectAllStudents(){
        String sql = "SELECT id, name, age, email FROM student";
        List<Student> studentList = new ArrayList<>(); 
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery()){
            //Thêm vào studentArrayList
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                studentList.add(student);
            }
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
        return studentList;
    }
    //Update
    public int updateStudent(String id, String name, int age, String email){
        String sql = "UPDATE student SET name = ?, age = ?, email = ? WHERE id = ?";
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql)){
            //Gán giá trị
            prep.setString(1, name);
            prep.setInt(2, age);
            prep.setString(3, email);
            prep.setString(4, id);
            //Thực thi sql
            return prep.executeUpdate();
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
        return 0;
    }
    //Delete
    public int deleteStudent(String id){
        String sql = "DELETE FROM student WHERE id = ?";
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql)){
            //Gán giá trị
            prep.setString(1, id);
            //Thực thi sql
            return prep.executeUpdate();
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
        return 0;
    }
    //Tìm student theo id
    public Student findStudentById(String id){
        String sql = "SELECT id, name, age, email FROM student WHERE id = ?";
        Student student = null;
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql)){
            prep.setString(1, id);
            try(ResultSet rs = prep.executeQuery()){
                if(rs.next()){
                    student = new Student();
                    student.setId(rs.getString("id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));
                    student.setEmail(rs.getString("email"));
                }
            }
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
       return student;
    }
    //Tìm student theo email
    public Student findStudentByEmail(String email){
        String sql = "SELECT id, name, age, email FROM student WHERE email = ?";
        Student student = null;
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql)){
            prep.setString(1, email);
            try(ResultSet rs = prep.executeQuery()){
                if(rs.next()){
                    student = new Student();
                    student.setId(rs.getString("id"));
                    student.setName(rs.getString("name"));
                    student.setAge(rs.getInt("age"));
                    student.setEmail(rs.getString("email"));
                }
            }
        }catch(SQLException e){
            System.err.println("Loi sql!!!\n" + e.getMessage());
        }
       return student;
    }
}
