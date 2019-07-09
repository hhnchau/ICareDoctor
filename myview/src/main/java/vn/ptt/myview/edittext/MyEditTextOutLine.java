package vn.ptt.myview.edittext;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import vn.ptt.myview.R;
import vn.ptt.myview.autocomplete.MyAutoCompleteTextView;

public class MyEditTextOutLine extends LinearLayout {
    private TextInputLayout textInputLayout;
    private MyAutoCompleteTextView myAutoCompleteTextView;
    private CharSequence hint, text;
    private int inputType;
    private int textSize;
    private ColorStateList textColor;

    public MyEditTextOutLine(Context context) {
        super(context);
        create();
    }

    public MyEditTextOutLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
        create();
    }

    public MyEditTextOutLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
        create();
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyEditTextOutLine);
        hint = typedArray.getString(R.styleable.MyEditTextOutLine_android_hint);
        text = typedArray.getString(R.styleable.MyEditTextOutLine_android_text);
        textSize = typedArray.getDimensionPixelSize(R.styleable.MyEditTextOutLine_android_textSize, 0);
        textColor = typedArray.getColorStateList(R.styleable.MyEditTextOutLine_android_textColor);
        inputType = typedArray.getInt(R.styleable.MyEditTextOutLine_android_inputType, EditorInfo.TYPE_NULL);
        typedArray.recycle();
    }

    private void create() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.edittext_outline, this);

            textInputLayout = findViewById(R.id.textInputLayout);
            myAutoCompleteTextView = findViewById(R.id.myAutoCompleteTextView);
            if (textSize > 0)
                myAutoCompleteTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            if (textColor != null) myAutoCompleteTextView.setTextColor(textColor);
            if (!TextUtils.isEmpty(hint)) textInputLayout.setHint(hint);
            if (!TextUtils.isEmpty(text)) myAutoCompleteTextView.setText(text);
            if (inputType != EditorInfo.TYPE_NULL) myAutoCompleteTextView.setInputType(inputType);
        }
    }

    public void setError(CharSequence s) {
        if (s == null) textInputLayout.setError(" ");
        else textInputLayout.setError(s);
    }

    public void setBoxStrokeRadius(int radius) {
        float r = getResources().getDimension(radius);
        textInputLayout.setBoxCornerRadii(r, r, r, r);
    }

    public void setBoxStrokeColor(int boxStrokeColor) {
        textInputLayout.setBoxStrokeColor(ContextCompat.getColor(getContext(), boxStrokeColor));
    }

    public void setDefaultStrokeColor(int strokeColor) {
        try {
            Field field = TextInputLayout.class.getDeclaredField("defaultStrokeColor");
            field.setAccessible(true);
            field.set(textInputLayout, ContextCompat.getColor(getContext(), strokeColor));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // ignore
        }
    }

    public void setEnable(boolean enable) {
        textInputLayout.setEnabled(enable);
        myAutoCompleteTextView.setEnabled(enable);
    }

    public MyAutoCompleteTextView getMyAutoCompleteTextView() {
        return myAutoCompleteTextView;
    }
}
