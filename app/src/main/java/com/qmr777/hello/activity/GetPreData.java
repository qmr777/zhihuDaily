package com.qmr777.hello.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.qmr777.hello.R;

public class GetPreData extends AppCompatActivity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pre_data);
        calendarView = (CalendarView) findViewById(R.id.cv_choose_date);
        calendarView.setMaxDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String m = month+"";
                String d = dayOfMonth+"";
                if(month<10){
                    m = "0"+m;
                }
                if(dayOfMonth<10)
                    d = "0"+d;
                String dateTime = year+m+d;
                Intent intent = new Intent();
                intent.putExtra("date",dateTime);
                Log.d("helllo",dateTime);
                setResult(0,intent);
                finish();
            }
        });
    }
}
