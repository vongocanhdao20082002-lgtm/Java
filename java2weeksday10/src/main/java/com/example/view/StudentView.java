package com.example.view;
import java.util.List;
import java.util.Scanner;

import com.example.model.Student;
public class StudentView{
    //printMenu
    public void printMenu(){
        System.out.println("--------Menu Functions--------");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Hien thi danh sach");
        System.out.println("3. Cap nhat sinh vien");
        System.out.println("4. Xoa sinh vien");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //Tiếp tục hay dừng?
    public boolean yesOrNo(String text, Scanner sc){
        while(true){ 
            System.out.print(text + "? Tra loi (Y/N): ");
            String answer = sc.nextLine().toUpperCase().trim();
            switch(answer){
                case "Y" -> {return true;}
                case "N" -> {return false;}
                default -> System.out.println("Chi nhap Y hoac N!!!");
            }
        }
    }
    //Nhập
    public String inputId(Scanner sc){
        System.out.print("Nhap ID: ");
        return sc.nextLine().toUpperCase().trim();
    }
    public String inputName(Scanner sc){
        System.out.print("Nhap ho va ten: ");
        return sc.nextLine().trim();
    }
    public int inputAge(Scanner sc){
        while(true){
            try {
                System.out.print("Nhap tuoi: ");
                return Integer.parseInt(sc.nextLine().trim());
            }catch(NumberFormatException n){
                System.err.println("Tuoi phai la so!!!");
            }
        }
    }
    public String inputEmail(Scanner sc){
        System.out.print("Nhap email: ");
        return  sc.nextLine().trim();
    }
    //Thông báo
    public void Notification(int rows, String text){
        if(rows > 0) System.out.println(text + " sinh vien thanh cong.");
        else System.out.println(text + " sinh vien that bai.");
    }
    //Hiện thông tin student
    public void showInfoAll(List<Student> studentsList){
        if(studentsList == null || studentsList.isEmpty()){
            System.out.println("Khong co sinh vien trong danh sach.");
        }else{
            //Hiển thị danh sach sinh vien
            int i = 1;
            for(Student student : studentsList){
                System.out.println("----Thong tin sinh vien thu " + i + "----");
                System.out.println(student.toString());
                i++;
            }
        }
    }
    public void showInfo(Student student){
        System.out.println("----Thong tin sinh vien----");
        System.out.println(student.toString());
    }
}