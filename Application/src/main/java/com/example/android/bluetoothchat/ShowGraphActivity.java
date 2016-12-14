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

import com.example.android.common.logger.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


        /*
        String result = new String(dList);
        System.out.println("byte配列からStringに変換した結果 ： " + result);
        */
    }

    //setting LineData
    private LineData createLineData(){
        ArrayList<Entry> valsComp1 = new ArrayList<>();
        ArrayList<Entry> valsComp2 = new ArrayList<>();
        ArrayList<Entry> valsComp3 = new ArrayList<>();
        ArrayList<Entry> valsComp4 = new ArrayList<>();
        ArrayList<Entry> valsComp5 = new ArrayList<>();
        List<Entry> entries = new ArrayList<>();

        List<String> recList = sampleFileInput();
        int cnt = 1;
        for(String rec : recList){
            String rec2 = rec.replaceAll("/*", "");
            String [] dataList = rec2.split(",");
            System.out.println(dataList[2]);
            valsComp1.add(new Entry(cnt, Integer.parseInt(dataList[2])));
            valsComp2.add(new Entry(cnt, Integer.parseInt(dataList[3])));
            valsComp3.add(new Entry(cnt, Integer.parseInt(dataList[4])));
            valsComp4.add(new Entry(cnt, Integer.parseInt(dataList[5])));
            valsComp5.add(new Entry(cnt, Integer.parseInt(dataList[6])));
            cnt++;
        }



        /*
        Entry c1e1 = new Entry(0f, 100000);
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1f, 150000);
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(2f, 180000);
        valsComp1.add(c1e3);
        Entry c2e1 = new Entry(0f,130000);
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(1f,115000);
        valsComp2.add(c2e2);
        Entry c2e3 = new Entry(2f, 100000);
        valsComp2.add(c2e3);
        */

//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        LineDataSet setComp1 = new LineDataSet(valsComp1, "MAX気温");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(Color.GREEN);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "MIN気温");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(Color.MAGENTA);
        LineDataSet setComp3 = new LineDataSet(valsComp3, "AVE気温");
        setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp3.setColor(Color.WHITE);
        LineDataSet setComp4 = new LineDataSet(valsComp4, "MAX湿度");
        setComp4.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp4.setColor(Color.YELLOW);
        LineDataSet setComp5 = new LineDataSet(valsComp5, "MIN湿度");
        setComp5.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp5.setColor(Color.CYAN);
/*
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
*/
        List<ILineDataSet> datasets = new ArrayList<ILineDataSet>();
        datasets.add(setComp1);
        datasets.add(setComp2);
        datasets.add(setComp3);
        datasets.add(setComp4);
        datasets.add(setComp5);
//        datasets.add(setComp2);

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
    /**
     * http://techbooster.org/android/application/1629/
     */
    private List<String> sampleFileInput() {

        InputStream in;
        BufferedWriter bw;
        String lineBuffer;
        String filePath = "/data/data/" + this.getApplicationContext().getPackageName() + "/files/" + Constants.LOCAL_FILE;
        List<String> result = new ArrayList<String>();
        try {
            // in = this.mContext.openFileInput(filePath);

            File f = new File(filePath);
            // in = new FileInputStream(f);

            if (f.exists()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));

                    String str = br.readLine();
                    result.add(str);
                    while (str != null) {
                        System.out.println(str);
                        result.add(str);
                        str = br.readLine();
                    }

                    br.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }
                return result;
                /*
                BufferedReader reader= new BufferedReader(new InputStreamReader(in,"UTF-8"));
                while( (lineBuffer = reader.readLine()) != null ){
                    Log.d("FileAccess",lineBuffer);
                }
                */
                /*
                int size = (int) f.length();
                byte bytes[] = new byte[size];
                byte tmpBuff[] = new byte[size];
                FileInputStream fis= new FileInputStream(f);
                try {
                    int read = fis.read(bytes, 0, size);
                    if (read < size) {
                        int remain = size - read;
                        while (remain > 0) {
                            read = fis.read(tmpBuff, 0, remain);
                            System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                            remain -= read;
                        }
                    }
                }  catch (IOException e){
                    throw e;
                } finally {
                    fis.close();
                }
                return bytes;
                */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
