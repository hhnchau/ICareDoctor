package vn.ptt.banner;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyBanner extends FrameLayout {
    private ViewPager viewpager;
    private BannerAdapter adapter;
    private int currentPage = 0;

    public MyBanner(@NonNull Context context) {
        super(context);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void create(List<Banner> lst, final BannerListener listener) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner, this, true);

        viewpager = view.findViewById(R.id.viewpager);
        adapter = new BannerAdapter(lst);
        viewpager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewpager, true);

        changeDotIndicator(tabLayout);

        adapter.setOnClickListener(new BannerListener() {
            @Override
            public void onListener() {
                if (listener != null) listener.onListener();
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                int current = viewpager.getCurrentItem();
                if (current == adapter.getCount() - 1 || currentPage == 0) {
                    int previousState = currentPage;
                    currentPage = i;
                    if (previousState == 1 && currentPage == 0) {
                        viewpager.setCurrentItem(current == 0 ? adapter.getCount() - 1 : 0);
                    }
                }
            }
        });

        startAutoScroll();
    }

    private void changeDotIndicator(TabLayout tabLayout) {
        ViewGroup.LayoutParams params = tabLayout.getLayoutParams();
        params.height = (int) getResources().getDimension(R.dimen.dp_1);
        params.width = (int) getResources().getDimension(R.dimen.dp_50);
        tabLayout.setLayoutParams(params);
    }

    private void startAutoScroll() {
        Timer timer = new Timer();
        final Handler handler = new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == adapter.getCount()) {
                            currentPage = 0;
                        }
                        viewpager.setCurrentItem(currentPage, true);
                        currentPage += 1;
                    }
                });
            }
        }, 500, 3 * 1000);
    }

}