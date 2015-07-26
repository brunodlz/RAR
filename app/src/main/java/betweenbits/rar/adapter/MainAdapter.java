package betweenbits.rar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import betweenbits.rar.R;

/**
 * Created by bruno v0id on 7/6/15.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.AdapterHolder> implements View.OnClickListener{

    private Callback callback;
    private ArrayList<String> mItens;

    public MainAdapter(Callback callback) {
        this.callback = callback;
        mItens = new ArrayList<>();
    }

    public void add(String item) {
        mItens.add(item);
        notifyItemInserted(mItens.size()-1);
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        holder.view.setOnClickListener(this);
        holder.title.setText(mItens.get(position));
        holder.icon.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class AdapterHolder extends RecyclerView.ViewHolder {

        final View view;
        final TextView title;
        final View icon;

        public AdapterHolder(View itemView) {
            super(itemView);
            view  = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            icon  = itemView.findViewById(R.id.icon);
        }
    }
}
