package ifpe.surpriseme.Model;

import java.io.Serializable;

public class Category implements Serializable{
    private Long id;
    private String Name;
    private boolean isSelect;

    public Category() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}