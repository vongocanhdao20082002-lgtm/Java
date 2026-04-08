import java.util.ArrayList;
import java.util.Scanner;

public class Java2weeksday1
{
    //Class nhân viên
    public static  class Employees{
        //Thông tin nhân viên
        private String name;
        private int age;
        private double oneDaySalary, workDay;
        //Gán dữ liệu ngoài vào
        public Employees(String name, int age, double oneDaySalary, double workDay){
            this.name = name; 
            this.age = age;
            this.oneDaySalary = oneDaySalary;
            this.workDay = workDay;
        }
        //Tạo contruction mặc định để gọi
        public Employees(){}
        //Lấy dữ liệu ra
        public String getName(){
            return this.name;
        }
        public int getAge(){
            return this.age;
        }
        public double getOneDaySalary(){
            return this.oneDaySalary;
        }
        public double getWorkDay(){
            return this.workDay;
        }
        //Lương thực = lương 1 ngày × số ngày làm
        public double getRealSalary(){
            //Lương thực = lương 1 ngày × số ngày làm
            return this.oneDaySalary * this.workDay;
        }
        //Thêm dữ liệu vào
        public void setName(Scanner sc){
            while(true){
                try{
                    System.out.print("Nhap ho va ten: ");
                    String nameStr = sc.nextLine().trim();
                    if(nameStr.isEmpty())
                        throw new IllegalArgumentException("Khong duoc de trong!!!");
                    else if(!checkText(nameStr))
                        throw new IllegalArgumentException("Trong ten khong co so!!!");
                    else {this.name = nameStr; break;}
                }
                catch(IllegalArgumentException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }        
        public void setAge(Scanner sc){
            while(true){ 
                try{
                    System.out.print("Nhap tuoi: ");
                    int ageInt = Integer.parseInt(sc.nextLine());
                    if(ageInt<=0)
                        throw new IllegalArgumentException("Tuoi phai lon hon 0!!!");
                    this.age = ageInt;
                    break;
                }
                catch(NumberFormatException e){
                    System.err.println("Tuoi phai la so nguyen!!!");
                }
                catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        public void setOneDaySalary(Scanner sc){
            while(true){
                try{
                    System.out.print("Nhap luong mot ngay: ");
                    double oneDaySalaryDou = Double.parseDouble(sc.nextLine());
                    if(oneDaySalaryDou <= 0)
                        throw new IllegalArgumentException("Luong phai lon hon 0!!!");
                    this.oneDaySalary = oneDaySalaryDou;
                    break;
                }catch(NumberFormatException e)
                {
                    System.err.println("Luong mot ngay phai la so!!!");
                }
                catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }

            }
        }
        public void setWorkDay(Scanner sc){
            while(true){
                try{
                    System.out.print("Nhap so ngay lam: ");
                    double workDayDou = Double.parseDouble(sc.nextLine());
                    if(workDayDou <= 0)
                        throw new IllegalArgumentException("So ngay lam phai lon hon 0!!!");
                    this.workDay = workDayDou;
                    break;
                }catch(NumberFormatException e)
                {
                    System.err.println("So ngay phai nhap so!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        //Xếp loại theo lương thực
        public String rankRealSalary(){
            double realSalary = getRealSalary();
            if(realSalary >= 5000)
                return "Xuất sắc";
            else if(realSalary >= 3000)
                return "Tốt";
            else if(realSalary >= 1500)
                return "Trung bình";
            else return "Kém";
        }
        //Nhập nhân viên
        public void inputEmployees(Scanner sc){
            System.out.println("--------Nhap nhan vien--------");
            setName(sc);
            setAge(sc);
            setOneDaySalary(sc);
            setWorkDay(sc);
        }
    }
    //Check chỉ chữ - true
    public static boolean checkText(String text){
        for(char c : text.toCharArray()) {
            if(Character.isDigit(c))
                return false;
        }
        return true;
    }
    //Tiếp tục hay dừng chức năng
    public static boolean yesORno(Scanner sc){
        while(true){ 
            System.out.println("Ban co muon tiep tuc chu?");
            System.out.print("Tra loi yes/no (y/n): ");
            String answer = sc.nextLine().toLowerCase();
            switch(answer){
                case "y" -> {return true;}
                case "n" -> {return false;}
                default -> System.err.println("Chi duoc nhap y (yes) hoac n (no)!!!");
            }
        }
    }
    // //Hiển thị nhân viên
    public static  void outputEmployees(Employees emp, int i){
        System.out.printf("--------Nhan vien %d--------%n",i);
        System.out.printf("Ho va ten: %s.%n",emp.getName());
        System.out.printf("Tuoi: %d.%n",emp.getAge());
        System.out.printf("Luong thuc: %.2f.%n", emp.getRealSalary());
        System.out.printf("Xep loai: %s%n", emp.rankRealSalary());
    }
    //In menu
    public static void printMenu(){
        System.out.println("--------Menu Chuc Nang--------");
        System.out.println("1. Nhap nhan vien.");
        System.out.println("2. Hien thi danh sach.");
        System.out.println("0. Thoat.");
    }
    //main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<Employees> employees = new ArrayList<>();
            while(true)
            {
                try{
                    printMenu();
                    System.out.print("Chon chuc nang so: ");
                    int choose = Integer.parseInt(sc.nextLine());
                    switch(choose){
                        case 1 ->{
                            while(true){
                                Employees emp = new Employees();
                                emp.inputEmployees(sc);
                                employees.add(emp);
                                System.out.println("Da luu!");
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 2 -> {
                            int i = 1;
                            if(employees.isEmpty())
                                System.out.println("Chua co nhan vien nao.");
                            else{
                                System.out.println("Tong so nhan vien: " + employees.size());
                                for(Employees e : employees)
                                    outputEmployees(e,i++);
                            } 
                        }
                        case 0 -> {return;}
                        default -> System.err.println("Chuc nang khong hop le!!!");
                    }
                }catch(NumberFormatException e){
                    System.err.println("Hay nhap so tu 0 den 2 tuong ung voi chuc nang!!!");
                }
            }
        }
    }
}