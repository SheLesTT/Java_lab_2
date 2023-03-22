
package lab_7;


public class Lab_7 {

    public static void main(String[] args) {
        
         Car BMW = new Car("BMW");
         Computer linux = new Computer("linux");
         
         System.out.println(BMW.isBroken());
         System.out.println(linux.isBroken());
         
         BMW.breaker();
         linux.breaker();
          System.out.println(BMW.isBroken());
         System.out.println(linux.isBroken());
         
         ComputerService com = new ComputerService();
         CarService cars =new CarService();
         
         com.repair(linux);
         cars.repair(BMW);
         
         System.out.println(BMW.isBroken());
         System.out.println(linux.isBroken());
    }
    
}
