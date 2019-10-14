package calendar_sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DefaultDayViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pl.milosz.medbase.R;

import static android.widget.Toast.LENGTH_SHORT;

public class SampleTimesSquareActivity extends Activity {
    private static final String TAG = "SampleTimesSquareActivi";
    private CalendarPickerView calendar;
    private final Set<Button> modeButtons = new LinkedHashSet<Button>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_calendar_picker);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        calendar = findViewById(R.id.calendar_view);
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(SelectionMode.SINGLE) //
                .withSelectedDate(new Date());

        initButtonListeners(nextYear, lastYear);
    }

    private void initButtonListeners(final Calendar nextYear, final Calendar lastYear) {
        final Button highlight = findViewById(R.id.button_highlight);
        final Button decorator = findViewById(R.id.button_decorator);
        modeButtons.addAll(Arrays.asList(decorator));

        highlight.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                setButtonsEnabled(highlight);

                Calendar c = Calendar.getInstance();
                c.setTime(new Date());

                calendar.setCustomDayView(new DefaultDayViewAdapter());
                calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
                calendar.init(getDateWithYear(2018), getDateWithYear(2020))
                        .inMode(SelectionMode.SINGLE)
                        .withSelectedDate(c.getTime());

                calendar.highlightDates(getHighlightedDaysForMonth(
                        c.get(Calendar.MONTH) - 1,
                        c.get(Calendar.MONTH),
                        c.get(Calendar.MONTH) + 1));
            }
        });

        decorator.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                setButtonsEnabled(decorator);
                calendar.setCustomDayView(new DefaultDayViewAdapter());
                calendar.setDecorators(Arrays.<CalendarCellDecorator>asList(new SampleDecorator()));
                calendar.init(lastYear.getTime(), nextYear.getTime()) //
                        .inMode(SelectionMode.SINGLE) //
                        .withSelectedDate(new Date());
            }
        });

        findViewById(R.id.done_button).setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                Log.d(TAG, "Selected time in millis: " + calendar.getSelectedDate().getTime());
                String toast = "Selected: " + calendar.getSelectedDate().getTime();
                Toast.makeText(SampleTimesSquareActivity.this, toast, LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonsEnabled(Button currentButton) {
        for (Button modeButton : modeButtons) {
            modeButton.setEnabled(modeButton != currentButton);
        }
    }

    private Date getDateWithYear(int year) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date getDateWithYearAndMonthForDay(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private List<Date> getHighlightedDaysForMonth(int... month) {
        List<Date> dateList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        for (int i = 0; i < month.length; i++) {
            for (int j = 0; j < 25; j++) {
                dateList.add(getDateWithYearAndMonthForDay(c.get(Calendar.YEAR), i, j));
            }
        }
        return dateList;
    }
}