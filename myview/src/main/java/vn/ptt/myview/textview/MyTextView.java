package vn.ptt.myview.textview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.ptt.myview.R;

public class MyTextView extends LinearLayout {
    private TextView tvHint, tvText;
    private CharSequence hint, text;
    private int textSize;
    private ColorStateList textColor;

    public MyTextView(Context context) {
        super(context);
        create();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
        create();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
        create();
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
        hint = typedArray.getString(R.styleable.MyTextView_android_hint);
        text = typedArray.getString(R.styleable.MyTextView_android_text);
        textSize = typedArray.getDimensionPixelSize(R.styleable.MyTextView_android_textSize, 0);
        textColor = typedArray.getColorStateList(R.styleable.MyTextView_android_textColor);
        typedArray.recycle();
    }

    private void create() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.textview, this);

            tvHint = findViewById(R.id.hint);
            tvText = findViewById(R.id.text);
            if (textSize > 0)
                tvText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            if (textColor != null) tvText.setTextColor(textColor);
            if (!TextUtils.isEmpty(hint)) tvHint.setText(hint);
            if (!TextUtils.isEmpty(text)) tvText.setText(text);
        }
    }

    public void setMyTextView(@NonNull CharSequence hint, @NonNull CharSequence text) {
        tvHint.setText(hint);
        tvText.setText(text);
    }

    public void setText(@NonNull CharSequence text) {
        tvText.setText(text);
    }

    public void setHint(@NonNull CharSequence hint) {
        tvHint.setText(hint);
    }

    public View getMyTextView() {
        return this;
    }

    public View getTextView() {
        return tvText;
    }
}
