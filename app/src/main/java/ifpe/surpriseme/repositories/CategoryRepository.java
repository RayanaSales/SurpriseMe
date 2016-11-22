package ifpe.surpriseme.repositories;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;

/**
 * Created by Rayana on 11/21/2016.
 */
public class CategoryRepository {

    private static CategoryRepository categoryRepository;

    public static CategoryRepository getCategoryRepository(){
        if(categoryRepository == null)
            categoryRepository = new CategoryRepository();

        return categoryRepository;
    }

    public ArrayList<Category> list(FragmentActivity activity) {

        ArrayList<Category> lista_categoria = new ArrayList<Category>();

        String[] columns = {DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME, DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE};
        ManagerDatabase md = new ManagerDatabase(activity, DatabaseSchemaHelper.Category.TABLE_NAME, columns);

        while (md.c.moveToNext()) {
            String category_name = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME));
            Boolean category_boolean = md.c.getInt(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE)) > 0;
            Category category = new Category(category_name, category_boolean);
            lista_categoria.add(category);
        }

        return lista_categoria;
    }
}
