package com.serveroverload.emittor_key;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PagerActivity extends AppCompatActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	ImageButton mNextBtn;
	Button mSkipBtn, mFinishBtn;

	ImageView zero, one, two;
	ImageView[] indicators;

	int lastLeftValue = 0;

	CoordinatorLayout mCoordinator;

	static final String TAG = "PagerActivity";

	int page = 0; // to track page position

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			getWindow().setStatusBarColor(
					ContextCompat.getColor(this, R.color.black_trans80));
		}

		setContentView(R.layout.activity_pager);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
			mNextBtn.setImageDrawable(Utils.tintMyDrawable(
					ContextCompat.getDrawable(this, R.drawable.ic_launcher),
					Color.WHITE));

		mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
		mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);

		zero = (ImageView) findViewById(R.id.intro_indicator_0);
		one = (ImageView) findViewById(R.id.intro_indicator_1);
		two = (ImageView) findViewById(R.id.intro_indicator_2);

		mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);

		indicators = new ImageView[] { zero, one, two };

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setCurrentItem(page);
		updateIndicators(page);

		final int color1 = ContextCompat.getColor(this, R.color.cyan);
		final int color2 = ContextCompat.getColor(this, R.color.orange);
		final int color3 = ContextCompat.getColor(this, R.color.green);

		final int[] colorList = new int[] { color1, color2, color3 };

		final ArgbEvaluator evaluator = new ArgbEvaluator();

		mViewPager
				.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {

						/*
						 * color update
						 */
						int colorUpdate = (Integer) evaluator.evaluate(
								positionOffset, colorList[position],
								colorList[position == 2 ? position
										: position + 1]);
						mViewPager.setBackgroundColor(colorUpdate);

					}

					@Override
					public void onPageSelected(int position) {

						page = position;

						updateIndicators(page);

						switch (position) {
						case 0:
							mViewPager.setBackgroundColor(color1);
							break;
						case 1:
							mViewPager.setBackgroundColor(color2);
							break;
						case 2:
							mViewPager.setBackgroundColor(color3);
							break;
						}

						mNextBtn.setVisibility(position == 2 ? View.GONE
								: View.VISIBLE);
						mFinishBtn.setVisibility(position == 2 ? View.VISIBLE
								: View.GONE);

					}

					@Override
					public void onPageScrollStateChanged(int state) {

					}
				});

		mNextBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				page += 1;
				mViewPager.setCurrentItem(page, true);
			}
		});

		mSkipBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				finish();
			}
		});

		mFinishBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				// update 1st time pref

			}
		});

	}

	void updateIndicators(int position) {
		for (int i = 0; i < indicators.length; i++) {
			indicators[i]
					.setBackgroundResource(i == position ? R.drawable.indicator_selected
							: R.drawable.indicator_unselected);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home_navigator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return KeyBoardActivity.newInstance(position);

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "SECTION 1";
			case 1:
				return "SECTION 2";
			case 2:
				return "SECTION 3";
			}
			return null;
		}

	}

}
