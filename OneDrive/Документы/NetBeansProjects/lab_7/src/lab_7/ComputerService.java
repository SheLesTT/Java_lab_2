
package lab_7;


public class ComputerService extends Serivce{
    
    public ComputerService(){
        
    }
    
    @Override
    public void repair(Breakage x){
        if(x instanceof Computer){
        Computer comp = (Computer)x;
        comp.setStatus(true);
    }
        else{
            System.out.println("This is not a car");
        }
    }
}
