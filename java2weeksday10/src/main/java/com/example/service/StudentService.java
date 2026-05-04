package com.example.service;
import java.util.List;

import com.example.dao.StudentDAO;
import com.example.model.Student;

public class StudentService {
    //Gọi file
    private final StudentDAO stDAO = new StudentDAO();
    //Validate
    public void validateId(String id){
        if(id == null || id.trim().isEmpty())
            throw new IllegalArgumentException("ID khong duoc de trong!!!");
        if(!id.trim().matches("^SV\\d{3}$"))
            throw new IllegalArgumentException("ID phai co dang SVxxx (x la chu so, tru 000)!!!");
        if(id.trim().equals("SV000"))
            throw new IllegalArgumentException("ID SV000 khong ton tai!!!");
    }
    public void validateName(String name){
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Ho va ten khong duoc de trong!!!");
        if(!name.trim().matches("^[A-Za-zÀ-ỹ '.-]+$"))
            throw new IllegalArgumentException("Ho va ten bao gom cac chu cai, dau cach ( ), nhay don ('), cham (.) va gach ngang (-)!!!");
    }
    public void validateAge(int age){
        if(age < 18)
            throw new IllegalArgumentException("Tuoi phai lon hoac bang 18!!!");
    }
    public void validateEmail(String email){
        if(email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email khong duoc de trong!!!");
        //(?!...) không được phép, .* mọi vị trí, //.//. là .. = không được có ".." trong chuỗi
        if(!email.trim().matches("^(?!.*\\.\\.)[\\w'.%+-]+@(gmail|email)\\.com$"))
            throw new IllegalArgumentException("Email khong hop le!!!\n- Email khong duoc chua dau cham lien tiep (..)!!!\n- Bat dau bang cac chu cai, chu so va cac dau cau _ ' . % + -!!!\n- Ket thuc bang @gmail.com hoac @email.com!!!");
    }    
    //Check
    public void checkIdExists(String id){
        if(findStudentId(id) == null){
            throw new IllegalArgumentException("Chua co id " + id);
        }
    }
    public void checkIdNotExists(String id){
        if(findStudentId(id) != null){
            throw new IllegalArgumentException("Da ton tai id " + id);
        }
    }
    public void checkEmailNotExists(String email){
        if(findStudentEmail(email) != null){
            throw new IllegalArgumentException("Email da ton tai!");
        }
    }
    public void checkEmailForUpdate(String email, String currentId){
        Student s = findStudentEmail(email);
        if(s != null && !s.getId().equals(currentId)){
            throw new IllegalArgumentException("Email da ton tai!");
        }
    }
    //Gọi hàm bên DAO
    public int addStudent(String id, String name, int age, String email){
        //Insert 
        return stDAO.insertStudent(id, name, age, email);
    }
    public List<Student> getAllStudents(){
        //Select
        return stDAO.selectAllStudents();
    }
    public int setStudent(String id, String name, int age, String email){
        //Update 
        return stDAO.updateStudent(id, name, age, email);
    }
    public int eraseStudent(String id){        
        //Delete
        return stDAO.deleteStudent(id);
    }
    public Student findStudentId(String id){
        return stDAO.findStudentById(id);
    }
    public Student findStudentEmail(String email){
        return stDAO.findStudentByEmail(email);
    }   
}
