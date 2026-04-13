import java.util.ArrayList;
import java.util.Scanner;

public class Java2weeksday4{
    //abstract class Person
    public static abstract class Person{
        private String id;
        private String name;
        //contructor
        public Person(String id, String name){
            this.id = id;
            this.name = name;
        }
        //getter
        public String getID(){return id;}
        public String getName(){return name;}
        //abstract String displayInfo()
        public abstract String displayInfo();
        //validateName
        protected void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty())
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[A-Za-zÀ-ỹ ']+$"))
                throw new IllegalArgumentException("Ten bao gom chu cai, dau cach ( ) va dau nhay don (')!!!");
        }
    }
    //class Student extends Person
    public static class Student extends Person{
        private int age;
        private double gpa;
        //Contructor
        public Student(String idStr, String nameStr, int ageInt, double gpaDou){
            super(idStr, nameStr); 
            validateName(nameStr);
            validateID(idStr);
            setAGE(ageInt);
            setGPA(gpaDou);
        }
        //Getter
        public int getAGE(){return age;}
        public double getGPA(){return gpa;}
        //Setter
        public void setAGE(int ageInt){
            validateAGE(ageInt);
            this.age = ageInt;
        }
        public void setGPA(double gpaDou){
            validateGPA(gpaDou);
            this.gpa = gpaDou;
        }
        //validate
        public void validateID(String idStr){
            if(idStr == null || idStr.isEmpty())
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("SV000"))
                throw new IllegalArgumentException("SV000 khong ton tai!!!");
            else if(!idStr.matches("^SV\\d{3}$"))
                throw new IllegalArgumentException("ID sai dinh dang. Dinh dang dung vd: SV001,...!!!");
        }
        public void validateAGE(int ageInt){
            if(ageInt < 18)
                throw new IllegalArgumentException("Tuoi phai lon hon hoac bang 18!!!");
        }
        public void validateGPA(double gpaDou){
            if(gpaDou < 0 || gpaDou > 4)
                throw new IllegalArgumentException("GPA phai nam trong khoang tu 0 den 4!!!");
        }
        //Override displayInfo()
        @Override
        public String displayInfo(){
            return "ID: " + getID() + "\nName: " + getName() + "\nTuoi: " + age + "\nGPA: " + gpa;
        }
    }
    //class Teacher extends Person
    public static class Teacher extends Person{
        private String subject;
        private double salary;
        //Contructor
        public Teacher(String idStr, String nameStr, String subjectStr, double salaryDou){
            super(idStr, nameStr); 
            validateID(idStr);
            validateName(nameStr);
            setSubject(subjectStr);
            setSalary(salaryDou);
        }
        //Getter
        public String getSubject(){return subject;}
        public double getSalary(){return salary;}
        //Setter
        public void setSubject(String subjectStr){
            validateSubject(subjectStr);
            this.subject = subjectStr;
        }
        public void setSalary(double salaryDou){
            validateSalary(salaryDou);
            this.salary= salaryDou;
        }
        //validate
        public void validateID(String idStr){
            if(idStr == null || idStr.isEmpty())
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("GV000"))
                throw new IllegalArgumentException("GV000 khong ton tai!!!");
            else if(!idStr.matches("^GV\\d{3}$"))
                throw new IllegalArgumentException("ID sai dinh dang. Dinh dang dung vd: GV001,...!!!");
        }
        public void validateSubject(String subjectStr){
            if(subjectStr == null || subjectStr.isEmpty())
                throw new IllegalArgumentException("Mon hoc khong duoc de trong!!!");
            else if(!subjectStr.matches("^[A-Za-zÀ-ỹ ]+$"))
                throw new IllegalArgumentException("Ten bao gom chu cai va dau cach ( )!!!");
        }
        public void validateSalary(double salaryDou){
            if(salaryDou <= 0)
                throw new IllegalArgumentException("Tien luong phai lon hon 0!!!");
        }
        //Override displayInfo()
        @Override
        public String displayInfo(){
            return "ID: " + getID() + "\nName: " + getName() + "\nMon hoc: " + subject + "\nTien luong: " + salary;
        }
    }
    //interface Imanager
    interface Imanager{
        void add(Person p);
        void displayInfoAll();
        void displayStudent();
        void displayTeacher();
        void findInfoByID(String idStr);
        void deleteByID(String idStr);
        double avgGPAstudent();
        String displayInfoByMaxGPA();
    }
    //class Manager implements Imanager
    public static class Manager implements Imanager{
        ArrayList<Person> personList = new ArrayList<>();
        @Override
        public void add(Person p){
            for(Person person : personList)
                if(p.getID().equals(person.getID()))
                    throw new IllegalArgumentException("ID nay da co trong danh sach!!!");  
            personList.add(p);
        }
        @Override
        public void displayInfoAll(){
            int i = 0;
            for(Person p : personList){
                i++;
                System.out.printf("----Thong tin ca nhan thu %d----\n",i);
                System.out.println(p.displayInfo());
            }
            if(i == 0)
                System.err.println("Danh sach khong co gi!!!");
        }
        @Override
        public void displayStudent(){
            int i = 0;
            for(Person p : personList)
                if(p instanceof Student st){
                    i++;
                    System.out.printf("----Thong tin sinh vien thu %d----\n",i);
                    System.out.println(st.displayInfo());
                }
            if(i == 0)
                System.out.println("Danh sach sinh vien khong co gi!!!");
        }
        @Override
        public void displayTeacher(){
            int i = 0;
            for(Person p : personList)
                if(p instanceof Teacher te){
                    i++;
                    System.out.printf("----Thong tin giao vien thu %d----\n",i);
                    System.out.println(te.displayInfo());
                }
            if(i == 0)
                System.out.println("Danh sach giao vien khong co gi!!!");
        }
        @Override
        public void findInfoByID(String idStr){
            for(Person person : personList)
                if(person.getID().equals(idStr)){
                    System.out.println("Da tim thay!!!");
                    System.out.println(person.displayInfo());
                    return;
                }     
            throw new IllegalArgumentException("ID nay khong co trong danh sach!!!");    
        }
        @Override
        public void deleteByID(String idStr){
            for(int i = 0; i < personList.size(); i++)
                if(personList.get(i).getID().equals(idStr)){
                    personList.remove(i);
                    return;
                }
            throw new IllegalArgumentException("ID nay khong co trong danh sach!!!");    
        }
        @Override
        public double avgGPAstudent(){
            int count = 0;
            double sumGPA = 0;
            for(Person person : personList){
                if(person instanceof Student st){
                    count++;
                    sumGPA += st.getGPA();
                }
            }
            if(count == 0)
                throw new IllegalArgumentException("Danh sach sinh vien khong co gi!!!");    
            else return sumGPA/count;
        }
        @Override
        public String displayInfoByMaxGPA(){
            double maxGPA = -1;
            Student maxStudent = null;
            for(Person p : personList){
                if(p instanceof Student st)
                    if(maxGPA < st.getGPA()){
                        maxGPA = st.getGPA();
                        maxStudent = st;
                    }
            }
            if(maxStudent == null)
                return "Danh sach sinh vien khong co gi!!!";  
            else return "----Thong tin sinh vien co GPA cao nhat----\n" + maxStudent.displayInfo();
        }
    }
    //printMenu
    public static void printMenu(){
        System.out.println("--------Menu Chuc Nang--------");
        System.out.println("1. Them Person");
        System.out.println("2. Hien thi toan bo danh sach");
        System.out.println("3. Hien thi rieng");
        System.out.println("4. Tim Person theo id");
        System.out.println("5. Xoa Person theo id");
        System.out.println("6. Tinh GPA trung binh cua Student");
        System.out.println("7. Tim Student co GPA cao nhat");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //printMenuType
    public static void printMenuType(String text){
        System.out.printf("--------Menu Chuc Nang: %s--------\n",text);
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //Main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            Manager manager = new Manager();
            boolean repeat = true;
            while(true){
                try{
                    printMenu();
                    int chooseMenu = Integer.parseInt(sc.nextLine().trim());
                    switch(chooseMenu){
                        case 1 -> {
                            while(repeat == true){
                                try{
                                    printMenuType("Them Person");
                                    int chooseMenuType = Integer.parseInt(sc.nextLine().trim());
                                    switch(chooseMenuType){
                                        case 1 -> {
                                            Student student = null;
                                            while(true){
                                                try{
                                                    System.out.print("Nhap ID: ");
                                                    String idStr = sc.nextLine().toUpperCase().trim();
                                                    System.out.print("Nhap ho va ten: ");
                                                    String nameStr = sc.nextLine().trim();
                                                    System.out.print("Nhap tuoi: ");
                                                    int ageInt = Integer.parseInt(sc.nextLine().trim());
                                                    System.out.print("Nhap GPA: ");
                                                    double gpaDou = Double.parseDouble(sc.nextLine().trim());
                                                    student = new Student(idStr, nameStr, ageInt, gpaDou);
                                                    break;
                                                }catch(NumberFormatException e){
                                                    System.err.println("Nhap sai kieu du lieu!");
                                                }catch(IllegalArgumentException i){
                                                    System.err.println(i.getMessage());
                                                } 
                                            }
                                            manager.add(student);
                                            System.out.println("Da them!!!");
                                        }
                                        case 2 -> {
                                            Teacher teacher = null;
                                            while(true){
                                                try{
                                                    System.out.print("Nhap ID: ");
                                                    String idStr = sc.nextLine().toUpperCase().trim();
                                                    System.out.print("Nhap ho va ten: ");
                                                    String nameStr = sc.nextLine().trim();
                                                    System.out.print("Nhap mon hoc: ");
                                                    String subjectStr = sc.nextLine().toUpperCase().trim();
                                                    System.out.print("Nhap tien luong: ");
                                                    double salaryDou = Double.parseDouble(sc.nextLine().trim());
                                                    teacher = new Teacher(idStr, nameStr, subjectStr, salaryDou);
                                                    break;
                                                }catch(NumberFormatException e){
                                                    System.err.println("Nhap sai kieu du lieu!");
                                                }catch(IllegalArgumentException i){
                                                    System.err.println(i.getMessage());
                                                } 
                                            }
                                            manager.add(teacher);
                                            System.out.println("Da them!!!");
                                        }
                                        case 0 -> {repeat = false;}
                                        default -> throw  new IllegalArgumentException("Phai nhap so tu 0 den 2!!!");
                                    }
                                }catch(NumberFormatException i){
                                    System.err.println("Phai nhap so!!!");
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            repeat = true;
                        }
                        case 2 -> { manager.displayInfoAll();}
                        case 3 -> {
                            while(repeat == true){
                                try{
                                    printMenuType("Hien thi rieng");
                                    int chooseMenuType = Integer.parseInt(sc.nextLine().trim());
                                    switch(chooseMenuType){
                                        case 1 -> {manager.displayStudent();}
                                        case 2 -> {manager.displayTeacher();}
                                        case 0 -> {repeat = false;}
                                        default -> throw  new IllegalArgumentException("Phai nhap so tu 0 den 2!!!");
                                    }
                                }catch(NumberFormatException i){
                                    System.err.println("Phai nhap so!!!");
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            repeat = true;
                        }
                        case 4 -> {
                            try {
                                System.out.print("Tim Person co id: ");
                                String idStr = sc.nextLine().toUpperCase().trim();
                                manager.findInfoByID(idStr);
                            }catch(IllegalArgumentException i){
                                System.err.println(i.getMessage());
                            }
                        }
                        case 5 -> {
                            try {
                                System.out.print("Xoa Person co id: ");
                                String idStr = sc.nextLine().toUpperCase().trim();
                                manager.deleteByID(idStr);
                                System.out.println("Da xoa xong!!!");
                            }catch(IllegalArgumentException i){
                                System.err.println(i.getMessage());
                            }
                        }
                        case 6 -> {
                            try{
                                System.out.print("GPA trung binh cua Student: ");
                                System.out.println(manager.avgGPAstudent());
                            }catch(IllegalArgumentException i){
                                System.err.println(i.getMessage());
                            }
                        }
                        case 7 -> {System.out.println(manager.displayInfoByMaxGPA());}
                        case 0 -> {return;}
                        default -> throw  new IllegalArgumentException("Phai nhap so tu 0 den 7!!!");
                    }
                }catch(NumberFormatException i){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException i){
                    System.err.println(i.getMessage());
                }
            }
        }
    }
} 