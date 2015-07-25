package betweenbits.rar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import betweenbits.rar.domain.Item;
import betweenbits.rar.R;

/**
 * Created by bruno v0id on 7/6/15.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> lista;

    public CustomAdapter(Context context, ArrayList<Item> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item item = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.row, null);

        TextView textTitle = (TextView) layout.findViewById(R.id.textTitleFolder);
        textTitle.setText(item.getTitulo());

        return layout;
    }
}
