package plugin;
import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPlugin {

    protected HashMap<String, String> properties = new HashMap<String, String>();
    private String name = null;

    public AbstractPlugin(String name){
        this.name = name;
    }

    public Set<String> getProperties() {
        return properties.keySet();
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getProperty(String key) {
        return this.properties.get(key);
    }

    public String getName(){
        return name;
    }

}
