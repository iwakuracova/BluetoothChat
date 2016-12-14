package com.example.android.bluetoothchat;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ShowGraphActivity extends Activity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_graph);

        //add ActionBar "Back to Home"
        android.app.ActionBar actionBar  = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLineChart = (LineChart) findViewById(R.id.chart1);

        //initialize LineChart
        initChart();

        //add data
        mLineChart.setData(createLineData());

        //refresh the chart after creating the data object
        mLineChart.invalidate();


    }

    //setting LineData
    private LineData createLineData(){
        ArrayList<Entry> valsComp1 = new ArrayList<>();
        ArrayList<Entry> valsComp2 = new ArrayList<>();
        List<Entry> entries = new ArrayList<>();

        Entry c1e1 = new Entry(0f, 100000);
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1f, 140000);
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(2f, 180000);
        valsComp1.add(c1e3);

        Entry c2e1 = new Entry(0f,130000);
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(1f,115000);
        valsComp2.add(c2e2);
        Entry c2e3 = new Entry(2f, 100000);
        valsComp2.add(c2e3);


        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        List<ILineDataSet> datasets = new ArrayList<ILineDataSet>();
        datasets.add(setComp1);
        datasets.add(setComp2);

        LineData linedata = new LineData(datasets);
        return linedata;

    }

    private void initChart(){
        //no description text
        mLineChart.setNoDataText("You need to provide data for the chart");

        //enable touch gestures
        mLineChart.setTouchEnabled(true);

        //enable scaling and gragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);

        //if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        //set an alternativr bacground color
        mLineChart.setBackgroundColor(Color.LTGRAY);

        LineData data = new LineData();
        data.setValueTextColor(Color.BLACK);

        //add empty data
        mLineChart.setData(data);

        //  ラインの凡例の設定
        Legend l = mLineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.BLACK);

        XAxis xl = mLineChart.getXAxis();
        xl.setTextColor(Color.BLACK);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);

    }

    //set ActionBar "Back to Home"
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
