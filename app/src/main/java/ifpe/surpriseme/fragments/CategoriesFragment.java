package ifpe.surpriseme.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.NumberKeyListener;
import android.util.Log;
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

public class CategoriesFragment extends Fragment implements CategoriaAdapter.CategoriesListener {


    ListView lv;
    CategoriaAdapter categoria_adapter;
    ArrayList<Category> lista_categoria;
    public static final String NEW_CATEGORY = "NEW_CATEGORY";
    public static final String UPDATE_LIST = "ifpe.surpriseme.UPDATE_LIST";

    BroadcastReceiver receiverUpdateList = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Category category = (Category) intent.getSerializableExtra(NEW_CATEGORY);
            lista_categoria.add(category);
            categoria_adapter.notifyDataSetChanged();
        }
    };

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
        registerUpdateListReceiver();
    }

    public void listAllCategories() {

        lista_categoria = CategoryRepository.getCategoryRepository().list(getActivity(), false);

        if (lv == null)
            lv = (ListView) getView().findViewById(R.id.listview_categorias);

        if (lv.getAdapter() == null) {
            categoria_adapter = new CategoriaAdapter(lista_categoria, getActivity(), this);
            lv.setAdapter(categoria_adapter);
        } else {
            categoria_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterUpdateListReceiver();
    }

    @Override
    public void onClickItem(CheckBox checkBox, int index) {

        Category categoria = lista_categoria.get(index);
        categoria.setSelect(checkBox.isChecked());

        long rows = CategoryRepository.getCategoryRepository().atualizar(getContext(), categoria);
        Log.i(getTag(), "Linhas " + rows);

    }

    /**
     *
     */
    public void registerUpdateListReceiver() {
        IntentFilter intentFilter = new IntentFilter(UPDATE_LIST);
        if (getActivity() != null) {
            getActivity().registerReceiver(receiverUpdateList, intentFilter);
        }
    }

    /**
     *
     */
    public void unregisterUpdateListReceiver() {
        if (getActivity() != null) {
            getActivity().unregisterReceiver(receiverUpdateList);
        }
    }

}
