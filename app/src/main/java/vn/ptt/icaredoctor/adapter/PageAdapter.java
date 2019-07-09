package vn.ptt.icaredoctor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.ptt.icaredoctor.R;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.MyViewHolder> {
    private List<Object> lst;

    public PageAdapter(List<Object> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.txt.setText(lst.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt;

        MyViewHolder(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.tvName);
        }
    }
}

