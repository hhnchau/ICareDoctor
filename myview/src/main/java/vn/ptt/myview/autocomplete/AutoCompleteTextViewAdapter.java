package vn.ptt.myview.autocomplete;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.ptt.myview.R;


public class AutoCompleteTextViewAdapter extends ArrayAdapter<Object> {
    private List<Object> lists;
    private String chr = "";

    public AutoCompleteTextViewAdapter(@NonNull Context context, @NonNull List<Object> lists) {
        super(context, 0, lists);
        this.lists = new ArrayList<>(lists);
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocomplete_item, parent, false);
        }

        TextView txt = convertView.findViewById(R.id.tvName);

        final Object complete = getItem(position);

        if (complete != null) {
            txt.setText(spannable(complete.toString(), chr));
        }

        return convertView;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Object> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(lists);
            } else {
                chr = constraint.toString().toLowerCase();

                for (Object item : lists) {
                    if (item.toString().toLowerCase().contains(chr)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Object) resultValue).toString();
        }
    };

    public void setItems(List<Object> lists) {
        this.lists = lists;
    }

    private SpannableString spannable(String s, String character) {
        if (s != null && !s.equals("") && character != null && !character.equals("") && character.length() <= s.length()) {
            SpannableString spannableString = new SpannableString(s);
            int start = s.toLowerCase().indexOf(character.toLowerCase());
            int end = start + character.length();
            if (start > -1)
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);
            return spannableString;
        }

        if (s == null) {
            return SpannableString.valueOf("");
        }

        return SpannableString.valueOf(s);
    }
}
