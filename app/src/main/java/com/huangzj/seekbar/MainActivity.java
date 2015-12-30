package com.huangzj.seekbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

/**
 * Created by huangzj on 2015/12/28.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    TextView valueTv;
    TextView leftTv;
    int marginLeft;

    private void initView() {
        leftTv = (TextView) findViewById(R.id.min_value_tv);
        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seek_bar);
        valueTv = (TextView) findViewById(R.id.show_value_tv);

        mySeekBar.setProgressDrawable(getResources().getDrawable(
                R.drawable.seekbar_progress_bg));
        mySeekBar.setThumb(getResources().getDrawable(R.drawable.shool_adjust_button));

        mySeekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                valueTv.setText(progress + "");
            }
        });
        mySeekBar.setOnDrawListener(new SeekBar.OnDrawListener() {

            @Override
            public void drawText(float centerX, float centerY, int progress) {
                if (marginLeft == 0) {
                    marginLeft = leftTv.getWidth();
                }

                int offset = valueTv.getWidth() / 2;
                int left = (int) (centerX - offset + marginLeft);
                RelativeLayout.LayoutParams btnLp = (RelativeLayout.LayoutParams) valueTv
                        .getLayoutParams();
                btnLp.setMargins(left, 0, 0, 0);
                valueTv.requestLayout();

            }
        });

        mySeekBar.setProgress(50);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
