package ifpe.surpriseme.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import java.util.ArrayList;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;

/**
 * Created by Rayana on 11/21/2016.
 */
public class CategoryRepository {

    private static CategoryRepository categoryRepository;

    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();

        return categoryRepository;
    }

    public ArrayList<Category> list(FragmentActivity activity, Boolean checked) {

        ArrayList<Category> lista_categoria = new ArrayList<Category>();

        String[] columns = {DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME,
                DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE,
                DatabaseSchemaHelper.Category._ID};
        ManagerDatabase md = new ManagerDatabase(activity, DatabaseSchemaHelper.Category.TABLE_NAME, columns);

        while (md.c.moveToNext()) {

            // Retorna apenas os selecionados
            if (checked) {
                Boolean category_boolean = md.c.getInt(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE)) > 0;
                if (category_boolean) {
                    String category_name = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME));
                    Long id = md.c.getLong(md.c.getColumnIndex(DatabaseSchemaHelper.Category._ID));
                    Category category = new Category(category_name, category_boolean);
                    category.setId(id);
                    lista_categoria.add(category);
                }
            } else // lista todas as categorias
            {
                Boolean category_boolean = md.c.getInt(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE)) > 0;
                String category_name = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME));
                Long id = md.c.getLong(md.c.getColumnIndex(DatabaseSchemaHelper.Category._ID));
                Category category = new Category(category_name, category_boolean);
                category.setId(id);
                lista_categoria.add(category);
            }
        }

        md.getSQLiteDatabase(activity).close();
        return lista_categoria;
    }

    public long atualizar(Context context, Category category) {
        ManagerDatabase md = new ManagerDatabase(context);

        ContentValues values = new ContentValues();
        values.put(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE, category.isSelect());
        String selectionClause = DatabaseSchemaHelper.Category._ID + " = ?";
        String[] selectionValues = {String.valueOf(category.getId())};
        long rows = md.ds.update(DatabaseSchemaHelper.Category.TABLE_NAME, values, selectionClause, selectionValues);
        return rows;
    }
}
