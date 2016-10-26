package ifpe.surpriseme.fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;
import ifpe.surpriseme.adapter.CustomAdapter;

public class CategoriesFragment extends Fragment implements Activity {
    ListView lv;
    Category[] categoriesItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_categories);
        lv = (ListView) findViewById(R.id.listView1);
        categoriesItens = new Category[5];
        categoriesItens[0] = new Category("Praia", "Paraisos brasileiros", 0);
        categoriesItens[1] = new Category("Praia 2", "Paraisos brasileiros", 1);
        categoriesItens[2] = new Category("Praia 3", "Paraisos brasileiros", 0);
        categoriesItens[3] = new Category("Praia 4", "Paraisos brasileiros", 1);
        categoriesItens[4] = new Category("Praia 5", "Paraisos brasileiros", 0);
        CustomAdapter adapter = new CustomAdapter(this, categoriesItens);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category_main, menu);
        return true;
    }
}
