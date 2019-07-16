package vn.ptt.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseObject> lst;

    public BaseAdapter(List<BaseObject> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return onCreateViewHolder(lst, viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        onBindViewHolder(lst, holder, i);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public int getItemViewType(int position) {
        BaseType type = BaseType.values()[lst.get(position).getType().getType()];
        return type.getType();
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(List<BaseObject> lst, @NonNull ViewGroup viewGroup, int i);

    public abstract void onBindViewHolder(List<BaseObject> lst, @NonNull RecyclerView.ViewHolder holder, int i);

}
