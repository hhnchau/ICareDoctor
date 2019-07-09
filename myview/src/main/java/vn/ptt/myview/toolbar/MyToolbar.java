package vn.ptt.myview.toolbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.ptt.myview.R;

public class MyToolbar extends LinearLayout implements View.OnClickListener {
    private TextView title;
    private ImageView iconL, iconR;

    public MyToolbar(Context context) {
        super(context);
        init();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.toolbar, this);
            title = findViewById(R.id.toolbarTitle);
            title.setOnClickListener(this);
            iconL = findViewById(R.id.toolbarLeft);
            iconL.setOnClickListener(this);
            iconR = findViewById(R.id.toolbarRight);
            iconR.setOnClickListener(this);
        }
    }

    public void setTitle(@NonNull String s) {
        title.setText(s);
    }

    public TextView getTitle() {
        return title;
    }

    public void setLeftIcon(int resId) {
        iconL.setImageResource(resId);
    }

    public ImageView getLeftIcon() {
        return iconL;
    }

    public void setRightIcon(int resId) {
        iconR.setImageResource(resId);
    }

    public ImageView getRightIcon() {
        return iconR;
    }

    public View getView() {
        return title;
    }

    public void setOnToolbarListener(OnToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    private OnToolbarListener toolbarListener;

    @Override
    public void onClick(View v) {
        if (toolbarListener != null)
            if (v.getId() == R.id.toolbarTitle)
                toolbarListener.onCenter();
            else if (v.getId() == R.id.toolbarLeft)
                toolbarListener.onLeft();
            else if (v.getId() == R.id.toolbarRight)
                toolbarListener.onRight();
    }

    public interface OnToolbarListener {
        void onLeft();

        void onCenter();

        void onRight();
    }
}

