package vn.ptt.icaredoctor.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.ptt.icaredoctor.R;

public class ViewHolderPage extends RecyclerView.ViewHolder {
    private TextView txt;
    public ViewHolderPage(@NonNull View itemView) {
        super(itemView);
        txt = itemView.findViewById(R.id.tvName);
    }

    public void onBind() {
        txt.setText("AAAA");
    }
}
