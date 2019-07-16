package vn.ptt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class ParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Parent> lst;

    public ParentAdapter(List<Parent> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return onCreateViewHolder(lst, viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        onBindViewHolder(context,lst, holder, i);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(List<Parent> lst, ViewGroup viewGroup, int p);

    public abstract void onBindViewHolder(Context context,List<Parent> lst, RecyclerView.ViewHolder holder, int p);
}
