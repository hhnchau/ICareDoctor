package vn.ptt.calendar;

import android.support.annotation.NonNull;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String ddMMyyyyHHmm = "dd/MM/yyyy HH:mm";
    public static final String yyyyMMddTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";

    public static String getCurrentMonth(Calendar calendar, int monthIndex) {
        final DateFormatSymbols dateFormat = new DateFormatSymbols(Locale.getDefault());
        calendar.add(Calendar.MONTH, monthIndex);
        return dateFormat.getMonths()[calendar.get(Calendar.MONTH)];
    }

    public static String getCurrentMonth(int monthIndex) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        final DateFormatSymbols dateFormat = new DateFormatSymbols(Locale.getDefault());
        calendar.add(Calendar.MONTH, monthIndex);
        return dateFormat.getMonths()[calendar.get(Calendar.MONTH)];
    }

    public static String getCurrentYear() {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getMonth(Calendar calendar) {
        final DateFormatSymbols dateFormat = new DateFormatSymbols(Locale.getDefault());
        calendar.add(Calendar.MONTH, 0);
        return dateFormat.getMonths()[calendar.get(Calendar.MONTH)];
    }

    public static String getYear(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static int getMonthOffset(Calendar currentCalendar, int firstDayOfWeek) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        calendar.setTime(currentCalendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayWeekPosition = calendar.getFirstDayOfWeek();
        int dayPosition = calendar.get(Calendar.DAY_OF_WEEK);

        if (firstDayWeekPosition == 1) {
            return dayPosition - 1;
        } else {
            if (dayPosition == 1) {
                return 6;
            } else {
                return dayPosition - 2;
            }
        }
    }

    public static int getWeekIndex(int weekIndex, Calendar calendar) {
        int firstDayWeekPosition = calendar.getFirstDayOfWeek();
        if (firstDayWeekPosition == 1) {
            return weekIndex;
        } else {
            if (weekIndex == 1) {
                return 7;
            } else {
                return weekIndex - 1;
            }
        }
    }

    public static boolean isSameMonth(Calendar c1, Calendar c2) {
        return !(c1 == null || c2 == null) &&
                (c1.get(Calendar.ERA) == c2.get(Calendar.ERA) &&
                        (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) &&
                        (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)));
    }

    public static boolean isSameMonth(Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null)
            throw new IllegalArgumentException("The dates must not be null");
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) &&
                (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)));
    }

    public static String dateConvert(String date, String requestFormat, String responseFormat) {
        if (date != null) {
            SimpleDateFormat response = new SimpleDateFormat(responseFormat, Locale.getDefault());
            SimpleDateFormat request = new SimpleDateFormat(requestFormat, Locale.getDefault());

            try {
                Date d = request.parse(date);
                return response.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static Calendar dateStringConvert(String date, String requestFormat) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(requestFormat, Locale.getDefault());
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String getCurrentDate(String responseFormat) {
        Calendar calendar = Calendar.getInstance();
        return dateFormat(calendar, responseFormat);
    }

    public static String dateFormat(Calendar calendar, @NonNull String responseFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(responseFormat, Locale.getDefault());

        return sdf.format(calendar.getTime());
    }

    public static Date string2Date(String date, String requestFormat) {
        SimpleDateFormat format = new SimpleDateFormat(requestFormat, Locale.getDefault());
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String date2String(String requestFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(requestFormat, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);

    }
}
