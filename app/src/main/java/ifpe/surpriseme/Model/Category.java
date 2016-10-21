package ifpe.surpriseme.Model;

public class Category {
    private String Name;
    private String description;

    public Category(String name, String description) {
        this.Name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}