
package lab_7;

public class Computer implements  Breakage{
    
    private boolean status = true;
    private String name;
    
    public Computer(String name){
        this.name = name;
    }
    
    public boolean isBroken(){
        return status;
    }
    public void setStatus(boolean x){
        status = x;
    }
    public String getName(){
        return name;
    }
    @Override
    public void breaker(){
        status = false;
    }
}
