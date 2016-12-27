package ifpe.surpriseme.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import ifpe.surpriseme.Model.CategoriaHolder;
import ifpe.surpriseme.Model.Category;
import ifpe.surpriseme.R;

public class CategoriaAdapter extends ArrayAdapter<Category> {

    private List<Category> lista_categorias;
    private Context context;
    private CategoriesListener categoriesListener;

    public CategoriaAdapter(List<Category> lista_categorias, Context context, CategoriesListener categoriesListener)
    {
        super(context, R.layout.categories_checkbox, lista_categorias);
        this.lista_categorias = lista_categorias;
        this.context = context;
        this.categoriesListener = categoriesListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        CategoriaHolder holder = new CategoriaHolder();

        if(convertView == null)
        {
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflate.inflate(R.layout.categories_checkbox, null);

            holder.categoria = (TextView) v.findViewById(R.id.categoria);
            holder.descricao_categoria = (TextView) v.findViewById(R.id.descricao_categoria);
            holder.checkBox = (CheckBox) v.findViewById(R.id.chk_box);

            holder.checkBox.setOnClickListener(onClickCheckbox(position));

            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    int pos = lv.getPositionForView(buttonView);
//                    if(pos != ListView.INVALID_POSITION)
//                    {
//                        Category c = lista_categoria.get(pos);
//                        c.setSelect(isChecked);
//
//                    }
                }
            });

//            holder.checkBox.setOnCheckedChangeListener((CategoriesFragment) context);

        }
        else
        {
            holder = (CategoriaHolder) v.getTag();
        }

        Category c = lista_categorias.get(position);

        if(holder != null){
            holder.categoria.setText(c.getName());
            //holder.descricao_categoria.setText(c.getDescription());
            holder.checkBox.setChecked(c.isSelect());
            holder.checkBox.setTag(c);
        }

        int background1 = Color.parseColor("#59A985");
        int background2 = Color.parseColor("#E6D3A7");

        if(position % 2 == 0){
          v.setBackgroundColor(background1);
        }
        else {
            v.setBackgroundColor(background2);
        }

        return v;
    }

    public View.OnClickListener onClickCheckbox(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( categoriesListener != null ) {
                    categoriesListener.onClickItem((CheckBox) v, position);
                }
            }
        };
    }

    public interface CategoriesListener {
        void onClickItem(CheckBox checkBox, int index);
    }


}
