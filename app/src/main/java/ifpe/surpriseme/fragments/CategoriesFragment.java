package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ifpe.surpriseme.CategoriaAdapter;
import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;

public class CategoriesFragment extends Fragment  {
    ListView lv;
    ArrayList<Category> lista_categoria;
    CategoriaAdapter categoria_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        lv = (ListView) v.findViewById(R.id.listview_categorias);
        displayListaCategorias();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private void displayListaCategorias()
    {
        lista_categoria = new ArrayList<Category>();
        lista_categoria.add(new Category("Praia", ""));
        lista_categoria.add(new Category("Moda", ""));
        lista_categoria.add(new Category("Carros", ""));
        lista_categoria.add(new Category("Paisagem", ""));
        lista_categoria.add(new Category("Game", " "));

        categoria_adapter = new CategoriaAdapter(lista_categoria, getActivity());
        lv.setAdapter(categoria_adapter);

    }
}
