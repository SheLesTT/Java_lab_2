
package lab_7;


public class CarService {
    
     public CarService(){
        
    }
    
    public void repair(Breakage x){
        if(x instanceof Car){
        Car car = (Car)x;
        car.setStatus(true);
    }
        else{
            System.out.println("This is not a computer");
        }
    }
}

