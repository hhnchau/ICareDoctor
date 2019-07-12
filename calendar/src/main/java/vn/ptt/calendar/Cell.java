package vn.ptt.calendar;

import android.content.Context;
import android.util.AttributeSet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Cell extends android.support.v7.widget.AppCompatTextView {
    private Date date;

    public Cell(Context context) {
        super(context);
    }

    public Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Cell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void create(Date date) {
        this.date = date;

        final SimpleDateFormat df = new SimpleDateFormat("d", Locale.getDefault());
        int day = Integer.parseInt(df.format(date));
        setText(String.valueOf(day));
    }

    public Date getDate() {
        return date;
    }
}
