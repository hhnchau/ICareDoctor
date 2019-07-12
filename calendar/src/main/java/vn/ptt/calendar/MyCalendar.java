package vn.ptt.calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyCalendar extends LinearLayout {
    private View view;
    private TextView tvMonth;
    private int currentMonth = 0;
    private Calendar calendar;
    private int mFirstDayOfWeek = Calendar.MONDAY;
    private String selectedDate = null;
    private boolean showDisableDate = false;
    private boolean follow = false;

    public MyCalendar(Context context) {
        super(context);
        init();
    }

    public MyCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.mycalendar, this, true);
        tvMonth = view.findViewById(R.id.tvMonth);
        ImageView icLeft = view.findViewById(R.id.icLeft);
        ImageView icRight = view.findViewById(R.id.icRight);

        calendar = Calendar.getInstance(Locale.getDefault());
        if (follow && selectedDate != null)
            calendar = CalendarUtils.dateStringConvert(selectedDate, CalendarUtils.ddMMyyyy);
        setDateOfWeek();

        icLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonth--;
                calendar = Calendar.getInstance(Locale.getDefault());
                calendar.add(Calendar.MONTH, currentMonth);
                handleCalendar();
            }
        });

        icRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMonth++;
                calendar = Calendar.getInstance(Locale.getDefault());
                calendar.add(Calendar.MONTH, currentMonth);
                handleCalendar();
            }
        });

        handleCalendar();
    }

    private void setDateOfWeek() {
        TextView dayOfWeek;
        final String[] weekDaysArray = getResources().getStringArray(R.array.weekdays);
        for (int i = 1; i < weekDaysArray.length; i++) {
            dayOfWeek = view.findViewWithTag(getContext().getString(R.string.dayOfWeek) + CalendarUtils.getWeekIndex(i, calendar));
            if (dayOfWeek == null) continue;
            dayOfWeek.setText(weekDaysArray[i]);
            dayOfWeek.setTextColor(getResources().getColor(R.color.org));
        }
    }

    private void handleCalendar() {
        String month = CalendarUtils.getCurrentMonth(calendar, currentMonth).toUpperCase(Locale.getDefault()) + " " + CalendarUtils.getYear(calendar);
        tvMonth.setText(month);

        Calendar mCalendar = Calendar.getInstance(getContext().getResources().getConfiguration().locale);
        mCalendar.setTime(calendar.getTime());
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mCalendar.setFirstDayOfWeek(mFirstDayOfWeek);
        int firstDayOfMonth = mCalendar.get(Calendar.DAY_OF_WEEK);

        int dayOfMonthIndex = CalendarUtils.getWeekIndex(firstDayOfMonth, mCalendar);
        int actualMaximum = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        final Calendar startCalendar = (Calendar) mCalendar.clone();
        startCalendar.add(Calendar.DATE, -(dayOfMonthIndex - 1));
        int monthEndIndex = 42 - (actualMaximum + dayOfMonthIndex - 1);

        Cell dayView;
        for (int i = 1; i < 43; i++) {
            dayView = view.findViewWithTag(getContext().getString(R.string.dayOfMonth) + i);
            if (dayView == null)
                continue;

            dayView.setOnClickListener(null);
            dayView.create(startCalendar.getTime());
            dayView.setVisibility(View.VISIBLE);

            if (CalendarUtils.isSameMonth(mCalendar, startCalendar)) {
                dayView.setOnClickListener(onClickListener);
                //dayView.setBackgroundColor(getContext().getResources().getColor(R.color.org));
                dayView.setTextColor(getContext().getResources().getColor(R.color.green));
            } else {
                dayView.setBackgroundResource(R.drawable.round);
                dayView.setTextColor(getContext().getResources().getColor(R.color.red));
                if (!showDisableDate)
                    dayView.setVisibility(View.INVISIBLE);
                else if (i >= 36 && ((float) monthEndIndex / 7.0f) >= 1) {
                    dayView.setVisibility(View.INVISIBLE);
                }
            }

            setCurrentDay(calendar.getTime());

            setSelectedDate(selectedDate);

            startCalendar.add(Calendar.DATE, 1);
            dayOfMonthIndex++;
        }
    }

    private void setSelectedDate(String selectDate) {
        if (selectDate == null) return;
        Calendar cal = CalendarUtils.dateStringConvert(selectDate, CalendarUtils.ddMMyyyy);
        if (cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            TextView dayView = (TextView) findViewByCalendar(getContext().getResources().getString(R.string.dayOfMonth), cal);
            if (dayView == null) return;
            dayView.setBackgroundResource(R.drawable.round);
        }
    }

    private void clearSelectedDate(String selectDate) {
        if (selectDate == null) return;
        Calendar cal = CalendarUtils.dateStringConvert(selectDate, CalendarUtils.ddMMyyyy);
        if (cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
            TextView dayView = (TextView) findViewByCalendar(getContext().getResources().getString(R.string.dayOfMonth), cal);
            if (dayView == null) return;
            dayView.setBackgroundResource(0);
        }
    }

    private void setCurrentDay(@NonNull Date todayDate) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(todayDate);
        if (CalendarUtils.isSameMonth(calendar)) {
            TextView dayView = (TextView) findViewByCalendar(getContext().getResources().getString(R.string.dayOfMonth), calendar);
            if (dayView == null) return;
            dayView.setTextColor(getContext().getResources().getColor(R.color.yellow));
        }
    }

    private View findViewByCalendar(String key, Calendar calendar) {
        int monthOffset = CalendarUtils.getMonthOffset(calendar, mFirstDayOfWeek);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        return view.findViewWithTag(key + (currentDay + monthOffset));
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {
                TextView textView = (TextView) v;

                Calendar cal = Calendar.getInstance();
                cal.setTime(calendar.getTime());
                cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(textView.getText().toString()));

                clearSelectedDate(selectedDate);
                selectedDate = CalendarUtils.dateFormat(cal, CalendarUtils.ddMMyyyy);
                setSelectedDate(selectedDate);

                if (onCalendarListener != null)
                    onCalendarListener.onDate(CalendarUtils.dateFormat(cal, CalendarUtils.ddMMyyyy));

            }
        }
    };

    public void showDisableDate(boolean show) {
        showDisableDate = show;
    }

    public void selected(String selected, boolean follow) {
        selectedDate = selected;
        this.follow = follow;
    }

    private OnCalendarListener onCalendarListener;

    public void setOnCalendarListener(OnCalendarListener onCalendarListener) {
        this.onCalendarListener = onCalendarListener;
    }

    public interface OnCalendarListener {
        void onDate(String s);
    }
}
