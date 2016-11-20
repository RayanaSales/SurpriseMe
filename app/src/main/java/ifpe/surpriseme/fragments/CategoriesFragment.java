package ifpe.surpriseme.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.adapters.CategoriaAdapter;
import ifpe.surpriseme.database.DatabaseHelper;
import ifpe.surpriseme.database.DatabaseSchemaHelper;
import ifpe.surpriseme.database.ManagerDatabase;

public class CategoriesFragment extends Fragment {
    ListView lv;
    ArrayList<Category> lista_categoria;
    CategoriaAdapter categoria_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        lv = (ListView) v.findViewById(R.id.listview_categorias);
        listAllCategories();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista_categoria = new ArrayList<Category>();
    }

    public void listAllCategories() {
        if (lista_categoria == null)
            lista_categoria = new ArrayList<Category>();

        if(lv == null)
            lv = (ListView) getView().findViewById(R.id.listview_categorias);

        if (!lista_categoria.isEmpty()) {
            lista_categoria.removeAll(lista_categoria);
        }

        String[] columns = {DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME, DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE};
        ManagerDatabase md = new ManagerDatabase(getActivity(), DatabaseSchemaHelper.Category.TABLE_NAME, columns);

        while (md.c.moveToNext()) {
            String category_name = md.c.getString(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CATEGORY_NAME));
            Boolean category_boolean = md.c.getInt(md.c.getColumnIndex(DatabaseSchemaHelper.Category.COLUMN_NAME_CHANGE_ISACTIVE)) > 0;
            Category category = new Category(category_name, category_boolean);
            lista_categoria.add(category);
        }

        if (lv.getAdapter() == null) {
            categoria_adapter = new CategoriaAdapter(lista_categoria, getActivity());
            lv.setAdapter(categoria_adapter);
        } else {
            categoria_adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        listAllCategories();
    }
}
