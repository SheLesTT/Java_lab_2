
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@XmlRootElement
public class Reactor {

    public  String type;
    public float burnup;
    private float kpd;
    private float enrichment;
    private float termal_capacity;
    private float electrical_capacity;
    private float life_time;
    private float first_load;

    private String download_source;

    public String getDownload_source() {
        return download_source;
    }

    public void setDownload_source(String download_source) {
        this.download_source = download_source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBurnup() {
        return burnup;
    }

    public void setBurnup(float burnup) {
        this.burnup = burnup;
    }

    public float getKpd() {
        return kpd;
    }

    public void setKpd(float kpd) {
        this.kpd = kpd;
    }

    public float getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(float enrichment) {
        this.enrichment = enrichment;
    }

    public float getTermal_capacity() {
        return termal_capacity;
    }

    public void setTermal_capacity(float termal_capacity) {
        this.termal_capacity = termal_capacity;
    }

    public float getElectrical_capacity() {
        return electrical_capacity;
    }

    public void setElectrical_capacity(float electrical_capacity) {
        this.electrical_capacity = electrical_capacity;
    }

    public float getLife_time() {
        return life_time;
    }

    public void setLife_time(float life_time) {
        this.life_time = life_time;
    }

    public float getFirst_load() {
        return first_load;
    }

    public void setFirst_load(float first_load) {
        this.first_load = first_load;
    }

    public DefaultMutableTreeNode createNode() throws IllegalAccessException{
        DefaultMutableTreeNode main = new DefaultMutableTreeNode(type);
        Field [] fields = Reactor.class.getDeclaredFields();
        System.out.println(fields.length);
        for(Field field: fields){

            String key = field.getName();
            Object value = field.get(this).toString();
            String key_value_str = key + ": " + value;
            System.out.println(key_value_str);
            main.add(new DefaultMutableTreeNode(key_value_str));


        }
        return main;
    }


}
