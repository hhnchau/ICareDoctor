package vn.ptt.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private List<Banner> lst;

    BannerAdapter(List<Banner> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Banner banner = lst.get(position);
        View v = LayoutInflater.from(container.getContext()).inflate(banner.getLayoutResId(), container, false);

        TextView txt = v.findViewById(R.id.txt);
        txt.setText(banner.getTitle());

        ImageView img = v.findViewById(R.id.img);
        img.setImageResource(banner.getImageResId());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) onClickListener.onListener();
            }
        });

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    void setOnClickListener(BannerListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private BannerListener onClickListener;


}