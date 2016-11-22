package ifpe.surpriseme.Model;

public class Category
{
    private String Name;
    private boolean isSelect;

    public Category(){
        super();
    }

    public Category(String name, Boolean isSelect) {
        this.Name = name;
        this.isSelect = isSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}