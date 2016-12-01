package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.adapters.CategoriaAdapter;
import ifpe.surpriseme.repositories.CategoryRepository;

public class CategoriesFragment extends Fragment {
    ListView lv;
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
    }

    public void listAllCategories() {

        ArrayList<Category> lista_categoria = CategoryRepository.getCategoryRepository().list(getActivity(), false);

        if(lv == null)
            lv = (ListView) getView().findViewById(R.id.listview_categorias);

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
