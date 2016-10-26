package ifpe.surpriseme.Model;

public class Category {
    private String Name;
    private String description;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    public Category(String name, String description, int value) {
        this.Name = name;
        this.description = description;
        this.value = value;
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

    public int getValue() {
        return this.value;
    }
}