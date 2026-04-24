/*
- Dùng chung → static
- Riêng từng object → không static
- Chỉ dùng class con để list lưu
- void trong interface không nên có tham số
 */
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
Bài tập: Quản lý thư viện
Yêu cầu:
1. Quản lý Book (id, title, author, isBorrowed).
2. Abstract: LibraryItem (id, title, showInfo).
3. Interface: Borrowable (borrow, returnItem).
4. Book: extends LibraryItem, implements Borrowable.
5. Dùng ArrayList lưu danh sách.
6. LibraryManager: CRUD + mượn/trả + tìm kiếm.
7. Lưu/đọc JSON.
Mục tiêu:
Abstract + Interface
OOP cơ bản
CRUD ArrayList
JSON I/O
*/
package com.example;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Main {
    //NAME_FILE
    private static final String NAME_FILE = "books.json";
    //ObjectMapper objMapper
    private static ObjectMapper objMapper = new ObjectMapper();
    //Save
    private static void saveToFile(List<Book> bookList){
        try{
            objMapper.writerWithDefaultPrettyPrinter().writeValue(new File(NAME_FILE), bookList);
        }catch(IOException e){
            System.err.println("Loi ghi/doc file: " + e.getMessage());
        }
    }
    //Read
    private static List<Book> readToFile(){
        try{
            File file = new File(NAME_FILE);
            if(file.exists())
                return objMapper.readValue(file, new TypeReference<List<Book>>(){});
        }catch(IOException e){
            System.err.println("Loi ghi/doc file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    //abstract class LibraryItem
    public static abstract class LibraryItem{
        //Khai báo biến
        protected String id;
        protected String title;
        //Contructor
        public LibraryItem(){}
        public LibraryItem(String id, String title){
            validateId(id.toUpperCase());
            validateTile(title);
            this.id = id.toUpperCase();
            this.title = title;
        }
        //Getter
        public String getId(){return id;}
        public String getTitle(){return title;}
        //Setter
        public void setId(String id){
            validateId(id.toUpperCase());
            this.id = id.toUpperCase();
        }
        public void setTitle(String title){
            validateTile(title);
            this.title = title;
        }
        //Validate
        private void validateId(String id){
            if(id == null || id.isEmpty())
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(id.equals("B000"))
                throw new IllegalArgumentException("ID B000 khong ton tai!!!");
            else if(!id.matches("^B\\d{3}$"))
                throw new IllegalArgumentException("ID bat dau bang B va ket thuc bang 3 chu so, tru 000!!!");
        }
        private void validateTile(String title){
            if(title == null || title.isEmpty())
                throw new IllegalArgumentException("Tieu de khong duoc de trong!!!");
            else if(!title.matches("[\\wÀ-ỹ\\p{Punct} ]+$"))
                throw new IllegalArgumentException("Tieu de bao gom cac chu cai, dau cau va dau cach ( )!!!");
        }
        //String showInfo
        public String showInfo(){
            return "ID sach: " + getId() + "\nTieu de: " + getTitle();
        }
    }
    //class Book
    public static class Book extends LibraryItem implements Borrowable{
        //Khai báo biến
        private String author;
        private boolean isBorrowed = false;
        //Contructor
        public Book(){}
        public Book(String id, String title, String author, boolean isBorrowed){
            super(id,title);
            validateAuthor(author);
            this.author = author;
            this.isBorrowed = isBorrowed;
        }
        //Getter
        public String getAuthor(){return author;}
        public boolean getIsBorrowed(){return isBorrowed;}
        //Setter
        public void setAuthor(String author){
            validateAuthor(author);
            this.author = author;
        }
        public void setIsBorrowed(boolean isBorrowed){
            this.isBorrowed = isBorrowed;
        }
        //Validate
        private void validateAuthor(String author){
            if(author == null || author.isEmpty())
                throw new IllegalArgumentException("Ten tac gia khong duoc de trong!!!");
            else if(!author.matches("[A-Za-zÀ-ỹ '.-]+$"))
                throw new IllegalArgumentException("Ten tac gia bao gom cac chu cai, dau cach ( ), nhay don ('), cham (.) va gach ngang (-)!!!");
        }
        //Override
        @Override
        public void borrow(){
            if(!isBorrowed){
                this.isBorrowed = true;
                System.out.println("Muon thanh cong.");
            }else System.out.println("Sach da duoc muon.");
        }
        @Override
        public void returnItem(){
            if(isBorrowed){
                this.isBorrowed = false;
                System.out.println("Da tra sach thanh cong.");
            }else System.out.println("Sach da duoc tra.");
        }
        @Override
        public String showInfo(){
            return super.showInfo() + "\nTen tac gia: " + getAuthor() + "\nTrinh trang: " + (getIsBorrowed()==true?"Da duoc muon":"Van con");
        }
    }
    //interface Borrowable
    interface Borrowable{
        void borrow();
        void returnItem();
    }
    public static class LibraryManager{
        private final List<Book> bookList;
        public LibraryManager() {
            this.bookList = readToFile();
        }   
        //Kiểm tra title
        public Book findExactTitle(String titleStr){
            return bookList.stream().filter(b -> b.getTitle().toUpperCase().equals(titleStr.toUpperCase().trim())).findFirst().orElse(null);
        }
        //Tìm kiếm
        public Book findBookById(String idStr){
            return bookList.stream().filter(b -> b.getId().equals(idStr.toUpperCase().trim())).findFirst().orElse(null);
        }
        public List<Book> findBookByTitle(String titleStr){
            return bookList.stream().filter(b -> b.getTitle().toUpperCase().trim().contains(titleStr.toUpperCase().trim())).toList();
        }
        //Nhập
        public String inputID(Scanner sc){
            while(true){
                try {
                    System.out.print("Nhap ID sach: ");
                    String id = sc.nextLine().toUpperCase().trim();
                    Book temp = new Book();
                    temp.setId(id);
                    return id;
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        public String inputTitle(Scanner sc){
            while(true){
                try {
                    System.out.print("Nhap tieu de: ");
                    String title = sc.nextLine().trim();
                    Book temp = new Book();
                    temp.setTitle(title);
                    return title;
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        public String inputAuthor(Scanner sc){
            while(true){
                try {
                    System.out.print("Nhap ten tac gia: ");
                    String author = sc.nextLine().trim();
                    Book temp = new Book();
                    temp.setAuthor(author);
                    return author;
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        //Thêm 
        public void addBook(Scanner sc){
            boolean add = true;
            while(add == true){
                try {
                    //Nhập
                    String id = inputID(sc);
                    Book found = findBookById(id);
                    if(found != null)
                        throw new IllegalArgumentException("Da co ID " + id + ". Vui long nhap lai!!!");
                    else while(true){
                            String title = inputTitle(sc);
                            Book foundExactTitle = findExactTitle(title);
                            //Kiểm tra xem đã có title này chưa
                            if(foundExactTitle != null){
                                String idString = foundExactTitle.getId();
                                System.out.println("Sach co ID la " + idString + " da co tieu de nay!!!");    
                                continue;
                            }else{
                                //Nhập xong title thì nhập thông tin còn lại
                                String author = inputAuthor(sc);
                                //Thêm
                                Book temp = new Book(id, title, author, false);
                                bookList.add(temp);
                                saveToFile(bookList);
                                System.out.println("Da them.");
                            }
                            //Tiếp tục?
                            if(!yesOrNo(sc)){
                                add = false;
                                break;
                            }
                        }    
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        //Hiển thị tất cả
        public void showInfoAll(Scanner sc){
            int i = 1;
            for(Book temp : bookList)
            {
                System.out.println("--------Thong tin quyen sach thu " + i + "--------");
                System.out.println(temp.showInfo());
                i++;
            }
            System.out.println("Nhap Enter de tiep tuc...");
            sc.nextLine();
        }
        //Sửa
        public void updateBook(Scanner sc){
            while(true){
                try {
                    //Nhập
                    String id = inputID(sc);
                    Book found = findBookById(id);
                    if(found == null)
                        throw new IllegalArgumentException("Chua co ID " + id + ". Vui long nhap lai!!!");
                    else{
                        String title;
                        //Nhập title
                        while(true){
                            title = inputTitle(sc);
                            Book foundExactTitle = findExactTitle(title);
                            //Kiểm tra xem đã có title này chưa
                            if(foundExactTitle != null && !foundExactTitle.getId().equals(id)){
                                String idString = foundExactTitle.getId();
                                System.out.println("Sach co ID la " + idString + " da co tieu de nay. Vui long nhap lai!!!");    
                            }else break;
                        }
                        //Nhập xong title thì nhập thông tin còn lại
                        String author = inputAuthor(sc);
                        //Sửa
                        found.setTitle(title);
                        found.setAuthor(author);
                        saveToFile(bookList);
                        System.out.println("Da sua.");
                        //Tiếp tục?
                        if(!yesOrNo(sc)) break;
                    }
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        //Xóa
        public void deleteBook(Scanner sc){
            while(true){
                //Nhập
                String id = inputID(sc);
                Book found = findBookById(id);
                if(found == null)
                    System.out.println("Chua co ID " + id + "!!!");
                else{
                    //Xóa
                    bookList.remove(found);
                    saveToFile(bookList);
                    System.out.println("Da xoa.");
                }
                //Tiếp tục?
                if(!yesOrNo(sc)) break;
            }
        }
        //Mượn sách
        public void borrowBook(Scanner sc){
            while(true){
                //Nhập
                String id = inputID(sc);
                Book found = findBookById(id);
                if(found == null)
                    System.out.println("Chua co ID " + id + "!!!");
                else{                        
                    //Mượn                       
                    found.borrow();
                    saveToFile(bookList);
                    System.out.println("Da luu.");
                } 
                //Tiếp tục?
                if(!yesOrNo(sc)) break;
            }
        }
        //Trả sách
        public  void returnItemBook(Scanner sc){
            while(true){
                //Nhập
                String id = inputID(sc);
                Book found = findBookById(id);
                if(found == null)
                    System.out.println("Chua co ID " + id + "!!!");
                else{                        
                    //Trả                    
                    found.returnItem();
                    saveToFile(bookList);
                    System.out.println("Da luu.");
                } 
                //Tiếp tục?
                if(!yesOrNo(sc)) break;
            }
        }
        //Tìm sách
        public void findBook(Scanner sc){
            while(true){
                try {
                    System.out.println("--------Tim kiem sach--------");
                    System.out.println("1. Tim theo ID sach");
                    System.out.println("2. Tim theo tieu de sach");  
                    System.out.println("0. Thoat");
                    System.out.print("Chon chuc nang so: ");
                    int choose = Integer.parseInt(sc.nextLine().trim());
                    switch(choose){
                        case 1 -> {
                            while(true){
                                System.out.println("1. Tim theo ID sach");
                                //Nhập
                                String id = inputID(sc);
                                Book found = findBookById(id);
                                if(found == null)
                                    System.out.println("Chua co ID " + id + "!!!");
                                else{
                                    System.out.println("Da tim thay.");
                                    System.out.println(found.showInfo()); 
                                } 
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }
                        }
                        case 2 -> {
                            while(true){
                                System.out.println("2. Tim theo tieu de sach");
                                //Nhập
                                String title = inputTitle(sc);
                                List<Book> found = findBookByTitle(title);
                                if(found.isEmpty())
                                    System.out.println("Chua co tieu de " + title + "!!!");
                                else{
                                    int i = 1;
                                    System.out.println("Da tim thay.");
                                    for(Book temp : found){
                                        System.out.println("--------Thong tin quyen sach thu " + i + "--------");
                                        System.out.println(temp.showInfo()); 
                                        i++;
                                    }
                                } 
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }

                        }
                        case 3 -> {return;}
                        default -> throw new IllegalArgumentException("Hay nhap so tu 0 den 2!!!");
                    }
                }catch(NumberFormatException n){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        //Tiếp tục hay dừng?
        public boolean yesOrNo(Scanner sc){
            while(true){ 
                System.out.print("Tiep tuc? Tra loi (y/n): ");
                String answer = sc.nextLine().toUpperCase().trim();
                switch(answer){
                    case "Y" -> {return true;}
                    case "N" -> {return false;}
                    default -> System.out.println("Chi nhap Y hoac N!!!");
                }
            }
        }
    }
    //printMenu
    public static void printMenu(){
        System.out.println("--------Menu Functions--------");
        System.out.println("1. Nhap thong tin sach moi");
        System.out.println("2. Hien thi tat ca sach");
        System.out.println("3. Cap nhat thong tin sach");
        System.out.println("4. Xoa sach");
        System.out.println("5. Muon sach");
        System.out.println("6. Tra sach");
        System.out.println("7. Tim kiem sach");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            LibraryManager libraryManager = new LibraryManager();
            while(true){
                try {
                    printMenu();
                    int choose = Integer.parseInt(sc.nextLine().trim());
                    switch(choose){
                        case 1 -> {
                            System.out.println("1. Nhap thong tin sach moi");
                            libraryManager.addBook(sc);
                        }
                        case 2 -> {
                            System.out.println("2. Hien thi tat ca sach");
                            libraryManager.showInfoAll(sc);
                        }
                        case 3 -> {
                            System.out.println("3. Cap nhat thong tin sach");
                            libraryManager.updateBook(sc);
                        }
                        case 4 -> {
                            System.out.println("4. Xoa sach");
                            libraryManager.deleteBook(sc);
                        }
                        case 5 -> {
                            System.out.println("5. Muon sach");
                            libraryManager.borrowBook(sc);
                        }
                        case 6 -> {
                            System.out.println("6. Tra sach");
                            libraryManager.returnItemBook(sc);
                        }
                        case 7 -> {
                            System.out.println("7. Tim kiem sach");
                            libraryManager.findBook(sc);
                        }
                        case 0 -> {return;}
                        default -> throw new IllegalArgumentException("Hay nhap so tu 0 den 7!!!");
                    }
                }catch(NumberFormatException n){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}