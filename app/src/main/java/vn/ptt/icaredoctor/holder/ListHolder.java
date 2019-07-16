package vn.ptt.icaredoctor.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.adapter.Anim;
import vn.ptt.adapter.BaseObject;
import vn.ptt.adapter.ChildAdapter;
import vn.ptt.adapter.Parent;
import vn.ptt.adapter.ParentAdapter;
import vn.ptt.icaredoctor.R;
import vn.ptt.model.history.HistoryClinicDomain;
import vn.ptt.model.outclinic.OutDiagnoseDomain;
import vn.ptt.model.outclinic.OutDrugOrderDomain;
import vn.ptt.model.outclinic.OutServiceOrderDomain;
import vn.ptt.myview.textview.MyTextView;

public class ListHolder extends RecyclerView.ViewHolder {
    private RecyclerView rcv;

    public ListHolder(@NonNull View itemView) {
        super(itemView);
        rcv = itemView.findViewById(R.id.rcvParent);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
    }

    public void onBindDiagnose(Context context, BaseObject baseObject) {
        HistoryClinicDomain historyClinicDomain = (HistoryClinicDomain) baseObject.getData();
        Parent<OutDiagnoseDomain> parent = new Parent<>();
        parent.setParentName(context.getString(R.string.txt_diagnose));
        parent.setLstChild(historyClinicDomain.getLstdiagnose());
        List<Parent> lst = new ArrayList<>();
        lst.add(parent);
        ParentAdapter adapter = getParentAdapter(baseObject, lst);
        rcv.setAdapter(adapter);
    }

    public void onBindDrug(Context context, BaseObject baseObject) {
        HistoryClinicDomain historyClinicDomain = (HistoryClinicDomain) baseObject.getData();
        Parent<OutDrugOrderDomain> parent = new Parent<>();
        parent.setParentName(context.getString(R.string.txt_drug));
        parent.setLstChild(historyClinicDomain.getLstdrugorder());
        List<Parent> lst = new ArrayList<>();
        lst.add(parent);
        ParentAdapter adapter = getParentAdapter(baseObject, lst);
        rcv.setAdapter(adapter);
    }

    public void onBindService(Context context, BaseObject baseObject) {
        HistoryClinicDomain historyClinicDomain = (HistoryClinicDomain) baseObject.getData();
        Parent<OutServiceOrderDomain> parent = new Parent<>();
        parent.setParentName(context.getString(R.string.txt_service));
        parent.setLstChild(historyClinicDomain.getLstserviceorder());
        List<Parent> lst = new ArrayList<>();
        lst.add(parent);
        ParentAdapter adapter = getParentAdapter(baseObject, lst);
        rcv.setAdapter(adapter);
    }

    private ParentAdapter getParentAdapter(final BaseObject baseObject, final List<Parent> lst) {
        return new ParentAdapter(lst) {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(List<Parent> lst, @NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_parent, viewGroup, false);
                return new ParentHolder(view);
            }

            @Override
            public void onBindViewHolder(Context context, List<Parent> lst, @NonNull RecyclerView.ViewHolder holder, int i) {
                switch (baseObject.getType()) {
                    case TYPE2:
                        ((ParentHolder) holder).bindDiagnose(lst.get(holder.getAdapterPosition()));
                        break;
                    case TYPE3:
                        ((ParentHolder) holder).bindDrug(lst.get(holder.getAdapterPosition()));
                        break;
                    case TYPE4:
                        ((ParentHolder) holder).bindService(lst.get(holder.getAdapterPosition()));
                        break;
                }
            }
        };
    }

    class ParentHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcvChild;
        private ChildAdapter childAdapter;
        private CardView cv;
        private ImageView arrow;
        private TextView parentName;

        ParentHolder(@NonNull View itemView) {
            super(itemView);
            rcvChild = itemView.findViewById(R.id.rcvChild);
            cv = itemView.findViewById(R.id.cardView);
            arrow = itemView.findViewById(R.id.arrow);
            parentName = itemView.findViewById(R.id.tvName);

            rcvChild.setHasFixedSize(true);
            rcvChild.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

        void bindDiagnose(final Parent parent) {

            parentName.setText(parent.getParentName());

            final List<OutDiagnoseDomain> lstChild = new ArrayList<>();
            childAdapter = getChildAdapterDiagnose(lstChild);

            rcvChild.setAdapter(childAdapter);

            cv.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onClick(View v) {
                    if (parent.hasChild()) {
                        if (!parent.isExpandable()) {
                            lstChild.addAll(parent.getLstChild());
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        } else {
                            lstChild.clear();
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        }
                        parent.setExpandable(!parent.isExpandable());
                    }
                }
            });
        }

        void bindDrug(final Parent parent) {

            parentName.setText(parent.getParentName());

            final List<OutDrugOrderDomain> lstChild = new ArrayList<>();
            childAdapter = getChildAdapterDrug(lstChild);

            rcvChild.setAdapter(childAdapter);

            cv.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onClick(View v) {
                    if (parent.hasChild()) {
                        if (!parent.isExpandable()) {
                            lstChild.addAll(parent.getLstChild());
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        } else {
                            lstChild.clear();
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        }
                        parent.setExpandable(!parent.isExpandable());
                    }
                }
            });
        }

        void bindService(final Parent parent) {

            parentName.setText(parent.getParentName());

            final List<OutServiceOrderDomain> lstChild = new ArrayList<>();
            childAdapter = getChildAdapterService(lstChild);

            rcvChild.setAdapter(childAdapter);

            cv.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onClick(View v) {
                    if (parent.hasChild()) {
                        if (!parent.isExpandable()) {
                            lstChild.addAll(parent.getLstChild());
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        } else {
                            lstChild.clear();
                            childAdapter.notifyDataSetChanged();
                            Anim.getInstance().rotate(arrow);
                        }
                        parent.setExpandable(!parent.isExpandable());
                    }
                }
            });
        }
    }

    private ChildAdapter getChildAdapterDiagnose(final List<OutDiagnoseDomain> lst) {
        return new ChildAdapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_child, viewGroup, false);
                return new ChildHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
                ((ChildHolder) holder).onDiagnose(lst.get(holder.getAdapterPosition()));
            }

            @Override
            public int getItemCount() {
                return lst.size();
            }
        };
    }

    private ChildAdapter getChildAdapterDrug(final List<OutDrugOrderDomain> lst) {
        return new ChildAdapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_child_drug, viewGroup, false);
                return new ChildHolderDrug(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
                ((ChildHolderDrug) holder).onDrug(lst.get(holder.getAdapterPosition()));
            }

            @Override
            public int getItemCount() {
                return lst.size();
            }
        };
    }

    private ChildAdapter getChildAdapterService(final List<OutServiceOrderDomain> lst) {
        return new ChildAdapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_child, viewGroup, false);
                return new ChildHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
                ((ChildHolder) holder).onService(lst.get(holder.getAdapterPosition()));
            }

            @Override
            public int getItemCount() {
                return lst.size();
            }
        };
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ChildHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.child);
        }

        void onDiagnose(OutDiagnoseDomain outDiagnoseDomain) {
            textView.setText(outDiagnoseDomain.getNameicdx());
            if (outDiagnoseDomain.getPrimary()==1)
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.red));
        }

        void onService(OutServiceOrderDomain outServiceOrderDomain) {
            textView.setText(outServiceOrderDomain.getServiceName());
        }
    }

    class ChildHolderDrug extends RecyclerView.ViewHolder {
        private MyTextView tvDrugName, tvNumber, tvUnit, tvUse;

        ChildHolderDrug(@NonNull View itemView) {
            super(itemView);
            tvDrugName = itemView.findViewById(R.id.tvDrugName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvUse = itemView.findViewById(R.id.tvUse);
        }

        void onDrug(OutDrugOrderDomain outDrugOrderDomain) {
            tvDrugName.setText(outDrugOrderDomain.getDrugName());
            tvNumber.setText(String.valueOf((int)outDrugOrderDomain.getQty()));
            tvUnit.setText(outDrugOrderDomain.getNameunit());
            tvUse.setText(outDrugOrderDomain.getUsage());
        }
    }
}
