
import java.util.ArrayList;
import java.util.Scanner;


public class Java2weeksday2{
    public static class Student{
        //Thuộc tính
        private String mssv;
        private String name;
        private int age;
        //Constructor
        public Student(String mssv, String name, int age){
            this.mssv = mssv;
            this.name = name;
            this.age = age;
        }
        public Student(){}
        //Getter
        public String getMssv(){return this.mssv;}
        public String getName(){return this.name;}
        public int getAge(){return this.age;}
        //Setter
        public void setMssv(Scanner sc){
            while(true){
                System.out.print("Mssv: ");
                String mssvStr = sc.nextLine().toUpperCase().trim();
                //check mssv
                if(checkMssv(mssvStr)){
                    this.mssv = mssvStr;
                    return;
                }
            }
        }
        public void setName(Scanner sc){
            while(true){
                System.out.print("Ho va ten: ");
                String nameStr = sc.nextLine().trim();
                //Có số thì bug
                if(checkText(nameStr)==true){
                    this.name = nameStr;
                    return;
                }
            }
        }
        public void setAge(Scanner sc){
            while(true){ 
                try {
                    System.out.print("Tuoi: ");
                    int ageInt = Integer.parseInt(sc.nextLine().trim());
                    if( ageInt <= 0)
                        throw new IllegalArgumentException("Tuoi phai lon hon 0!!!");
                    else{
                        this.age = ageInt;
                        return;
                    }
                } catch(NumberFormatException e) {
                    System.err.println("Tuoi la so nguyen!!!");
                }catch(IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    //Check chỉ chữ - true
    public static boolean checkText(String text){
        try{
            if(text.isEmpty())
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            for(Character c : text.toCharArray())
                if(Character.isDigit(c))
                    throw new IllegalArgumentException("Ten khong co so!!!");
            return true;
            
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    //Check format mssv SV001...
    public static boolean checkMssv(String text){
        try{
            if(text.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(!text.startsWith("SV"))
                throw new IllegalArgumentException("Mssv phai bat dau bang SV...");
            else if(text.length() != 5)
                throw new IllegalArgumentException("Mssv chi co 5 ky tu!!!");
            else{
                char[] t = text.toCharArray(); 
                for(int i=2; i < t.length; i++){
                    if(!Character.isDigit(t[i]))
                        throw new IllegalArgumentException("3 ky tu cuoi phai la so!!!");
                }
                return true;
            }
        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public static void main(String[] agrs){
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<Student> studenArrayList = new ArrayList<>();
            Student st = new Student();
        }
        
    }
}