package com.hiteshsahu.cool_keyboard.view.activitis;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hiteshsahu.cool_keyboard.R;
import com.hiteshsahu.cool_keyboard.view.adapter.SectionsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {
    private ImageView[] indicators;
    private int[] colorList;
    public static float ACCELERATION = -0.00013f;
    public static int ROTATION_SPEED = 144;
    private ArgbEvaluator evaluator;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    ViewPager demoPager;
    @BindView(R.id.rotnSeekBar)
    SeekBar rotationBar;
    @BindView(R.id.accSeekBar)
    SeekBar accelarationBar;
    @BindView(R.id.acc_Value)
    TextView accValue;
    @BindView(R.id.rotnValue)
    TextView rotationValue;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.demo_activity);
        ButterKnife.bind(this);
        evaluator = new ArgbEvaluator();
        indicators = new ImageView[]{
                (ImageView) findViewById(R.id.intro_indicator_0),
                (ImageView) findViewById(R.id.intro_indicator_1),
                (ImageView) findViewById(R.id.intro_indicator_2)};
        colorList = new int[]{
                ContextCompat.getColor(this, R.color.cyan),
                ContextCompat.getColor(this, R.color.orange),
                ContextCompat.getColor(this, R.color.green)};

        setUpLayout();
    }

    private void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i]
                    .setBackgroundResource(i == position ? R.drawable.indicator_selected
                            : R.drawable.indicator_unselected);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpLayout() {

        //Set Up Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        rotationBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                ROTATION_SPEED = progress;
                rotationValue.setText(getString(R.string.rpm) + ROTATION_SPEED);

            }
        });

        accelarationBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        ACCELERATION = ((float) progress) / (-10000);
                        accValue.setText(getString(R.string.gravity) + progress);

                    }
                });

        accValue.setText(getString(R.string.gravity) + accelarationBar.getProgress());
        rotationValue.setText(getString(R.string.rpm) + rotationBar.getProgress());
        demoPager.setAdapter(new SectionsPagerAdapter(
                getSupportFragmentManager()));
        tabLayout.setupWithViewPager(demoPager);
        demoPager
                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                        //update Page Color
                        int colorUpdate =
                                (Integer) evaluator.evaluate(
                                        positionOffset, colorList[position],
                                        colorList[position == 2 ? position
                                                : position + 1]);
                        demoPager.setBackgroundColor(colorUpdate);
                    }

                    @Override
                    public void onPageSelected(int position) {
                        //update Indicator
                        updateIndicators(position);
                        demoPager.setBackgroundColor(colorList[position]);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

        demoPager.setCurrentItem(0);
        updateIndicators(0);
    }
}
