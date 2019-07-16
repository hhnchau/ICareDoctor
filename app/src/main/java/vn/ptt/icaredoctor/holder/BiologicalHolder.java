package vn.ptt.icaredoctor.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import vn.ptt.icaredoctor.R;
import vn.ptt.model.history.HistoryClinicDomain;
import vn.ptt.myview.textview.MyTextView;

public class BiologicalHolder extends RecyclerView.ViewHolder {
    private MyTextView tvCircuit, tvBloodMax, tvBloodMin, tvTemperature, tvHeartbeat, tvWeight;

    public BiologicalHolder(@NonNull View itemView) {
        super(itemView);
        tvCircuit = itemView.findViewById(R.id.tvCircuits);
        tvBloodMax = itemView.findViewById(R.id.tvBloodMax);
        tvBloodMin = itemView.findViewById(R.id.tvBloodMin);
        tvTemperature = itemView.findViewById(R.id.tvTemperature);
        tvHeartbeat = itemView.findViewById(R.id.tvHeartbeat);
        tvWeight = itemView.findViewById(R.id.tvWeights);
    }

    public void onBind(HistoryClinicDomain historyClinicDomain) {

        tvCircuit.setText(historyClinicDomain.getCircui());
        tvBloodMax.setText(String.valueOf(historyClinicDomain.getBlomax()));
        tvBloodMin.setText(String.valueOf(historyClinicDomain.getBlomin()));
        tvTemperature.setText(String.valueOf(historyClinicDomain.getTemper()));
        tvHeartbeat.setText(String.valueOf(historyClinicDomain.getHeartb()));
        tvWeight.setText(String.valueOf(historyClinicDomain.getWeight()));

    }
}
