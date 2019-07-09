package vn.ptt.icaredoctor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.holder.ViewHolderPage;
import vn.ptt.icaredoctor.holder.ViewHolderType;

public class AdapterBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Map<String, Object>> lst;

    public AdapterBase(List<Map<String, Object>> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            default:
            case ViewHolderType.PAGE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_page, viewGroup, false);
                return new ViewHolderPage(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        switch (i) {
            case ViewHolderType.PAGE:
                ((ViewHolderPage) holder).onBind();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case ViewHolderType.PAGE:
                return ViewHolderType.PAGE;
            default:
                return -1;
        }
    }
}
