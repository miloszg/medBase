package calendar_sample;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import java.util.Date;

public class SampleDecorator implements CalendarCellDecorator {
    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        String dateString = Integer.toString((int) date.getTime());

        SpannableString string = new SpannableString(dateString + "\n twoj stary jak cie robil");
        string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        cellView.getDayOfMonthTextView().setText(string);
    }
}