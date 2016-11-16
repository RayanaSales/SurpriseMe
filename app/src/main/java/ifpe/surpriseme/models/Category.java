package ifpe.surpriseme.models;

public class Category {
    private String Name;
    private String description;
    private boolean isSelect;

    public Category(String name, String description) {
        this.Name = name;
        this.description = description;
    }


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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