
public class Item {
    private String name;
    private String description;
    private boolean lightSource;

    public Item(String name, String description, boolean lightSource) {
        this.name = name;
        this.description = description;
        this.lightSource = lightSource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLightSource() {
        return lightSource;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", description=" + description + ", lightSource=" + lightSource + "]";
    }
    
}

