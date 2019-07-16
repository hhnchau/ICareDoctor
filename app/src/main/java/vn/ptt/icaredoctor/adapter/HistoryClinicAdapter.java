package vn.ptt.icaredoctor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.adapter.BaseAdapter;
import vn.ptt.adapter.BaseObject;
import vn.ptt.adapter.BaseType;
import vn.ptt.calendar.CalendarUtils;
import vn.ptt.icaredoctor.R;
import vn.ptt.icaredoctor.holder.BiologicalHolder;
import vn.ptt.icaredoctor.holder.ListHolder;
import vn.ptt.model.history.HistoryClinicDomain;

public class HistoryClinicAdapter extends RecyclerView.Adapter<HistoryClinicAdapter.MyViewHolder> {
    private List<HistoryClinicDomain> lists;
    private Context context;

    public HistoryClinicAdapter(List<HistoryClinicDomain> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        this.context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        if (parent != null) {

            holder.tvName.setText(parent.getReason());
            holder.tvDate.setText(CalendarUtils.dateConvert(parent.getDateexa(), CalendarUtils.yyyyMMddTHHmmss, CalendarUtils.ddMMyyyy));

            final List<BaseObject> lstChild = new ArrayList<>();
            holder.rcvChild.setHasFixedSize(true);
            holder.rcvChild.setLayoutManager(new LinearLayoutManager(context));
            final BaseAdapter multiViewAdapter = new BaseAdapter(lstChild) {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(List<BaseObject> lst, @NonNull ViewGroup viewGroup, int i) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(lst.get(i).getResId(), viewGroup, false);
                    BaseType type = BaseType.values()[lst.get(i).getType().getType()];
                    switch (type) {
                        default:
                            return null;
                        case TYPE1:
                            return new BiologicalHolder(view);
                        case TYPE2:
                        case TYPE3:
                        case TYPE4:
                            return new ListHolder(view);
                    }
                }

                @Override
                public void onBindViewHolder(List<BaseObject> lst, @NonNull RecyclerView.ViewHolder holder, int i) {
                    BaseType type = BaseType.values()[lst.get(i).getType().getType()];
                    switch (type) {
                        case TYPE1:
                            ((BiologicalHolder) holder).onBind((HistoryClinicDomain) lst.get(i).getData());
                            break;
                        case TYPE2:
                            ((ListHolder) holder).onBindDiagnose(context, lst.get(holder.getAdapterPosition()));
                            break;
                        case TYPE3:
                            ((ListHolder) holder).onBindDrug(context, lst.get(holder.getAdapterPosition()));
                            break;
                        case TYPE4:
                            ((ListHolder) holder).onBindService(context, lst.get(holder.getAdapterPosition()));
                            break;
                    }
                }
            };
            holder.rcvChild.setAdapter(multiViewAdapter);


            //Set Expand
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    RotateAnimation arrowAnimation;
                    if (!parent.isExpandable()) {
                        lstChild.addAll(getChildData(parent));
                        multiViewAdapter.notifyDataSetChanged();
                        arrowAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    } else {
                        lstChild.clear();
                        multiViewAdapter.notifyDataSetChanged();
                        arrowAnimation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    }
                    parent.setExpandable(!parent.isExpandable());
                    arrowAnimation.setFillAfter(true);
                    arrowAnimation.setDuration((long) 300);
                    holder.icArrow.startAnimation(arrowAnimation);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        HistoryClinicDomain parent = lists.get(holder.getAdapterPosition());
        parent.setExpandable(false);
        super.onViewDetachedFromWindow(holder);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private RecyclerView rcvChild;
        private ImageView icArrow;

        private TextView tvName, tvDate;


        MyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            rcvChild = itemView.findViewById(R.id.rcvChild);
            icArrow = itemView.findViewById(R.id.ic_expand);


            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public void setItems(List<HistoryClinicDomain> lists) {
        this.lists = lists;
    }


    private List<BaseObject> getChildData(HistoryClinicDomain data) {
        BaseObject<HistoryClinicDomain> biological = new BaseObject<>();
        biological.setType(BaseType.setType(BaseType.TYPE1.getType()));
        biological.setData(data);
        biological.setResId(R.layout.item_biological);

        BaseObject<HistoryClinicDomain> outDiagnose = new BaseObject<>();
        outDiagnose.setType(BaseType.setType(BaseType.TYPE2.getType()));
        outDiagnose.setData(data);
        outDiagnose.setResId(R.layout.item_list);

        BaseObject<HistoryClinicDomain> outDrugOrder = new BaseObject<>();
        outDrugOrder.setType(BaseType.setType(BaseType.TYPE3.getType()));
        outDrugOrder.setData(data);
        outDrugOrder.setResId(R.layout.item_list);

        BaseObject<HistoryClinicDomain> outServiceOrder = new BaseObject<>();
        outServiceOrder.setType(BaseType.setType(BaseType.TYPE4.getType()));
        outServiceOrder.setData(data);
        outServiceOrder.setResId(R.layout.item_list);

        List<BaseObject> lstChild = new ArrayList<>();
        lstChild.add(biological);
        lstChild.add(outDiagnose);
        lstChild.add(outDrugOrder);
        lstChild.add(outServiceOrder);

        return lstChild;
    }
}
