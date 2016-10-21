package ifpe.surpriseme.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;

public class CategoriesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewCategoriesFragment = inflater.inflate(R.layout.fragment_categories,container,false);
        ListView categoriesListView = (ListView) viewCategoriesFragment.findViewById(R.id.categoriesView); //esse método se encontra na classe Activity, e no estamos herdando ela

        List<String> categoryListModel = searchCategories();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categoryListModel);
        categoriesListView.setAdapter(arrayAdapter);

        return viewCategoriesFragment;
    }

    private List<String> searchCategories() {

//        List<Category> categoryListModel = new ArrayList<>();
//        Category category1 = new Category("Animais", "Animais brincando");
//        Category category2 = new Category("Céu", "Céu azul");
//        Category category3 = new Category("Praia", "Paraíso");
//
//        categoryListModel.add(category1);
//        categoryListModel.add(category2);
//        categoryListModel.add(category3);

        List<String> categoryListModel = new ArrayList<>();
        categoryListModel.add("Animais");
        categoryListModel.add("Praia");
        categoryListModel.add("Montanhas");


        return categoryListModel;
    }
}
