package com.serveroverload.emittor_key;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.plattysoft.leonids.ParticleSystem;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

public class StarWarKeyBoardActivity extends AppCompatActivity {

	public String input;
	private boolean fontToggle;

	private float ACCELERATION = AppConstants.ACCELERATION_DEFAULT;
	private int ROTATION_SPEED = AppConstants.ROTATION_SPEED_DEFAULT;
	HashMap<Character, Integer> keyParticleMap;
	HashMap<Character, Integer> starWarParticleMap;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_keyboard_physics_sw);

		mp = MediaPlayer.create(this, R.raw.laser);
		//
		// ImageView im = (ImageView) findViewById(R.id.background);
		//
		// Glide.with(KeyBoardActivity.this).load("")
		// .placeholder(R.drawable.typing_keyboard).into(im);

		FABRevealLayout fabRevealLayout = (FABRevealLayout) findViewById(R.id.fab_reveal_layout);
		configureFABReveal(fabRevealLayout);

		initKeyParticleMap();

		setUpLayout();
	}

	private void setUpLayout() {
		SeekBar rotationBar = (SeekBar) findViewById(R.id.rotn);
		SeekBar accelarationBar = (SeekBar) findViewById(R.id.acc);
		EditText outEditText = (EditText) findViewById(R.id.edit);

		findViewById(R.id.toggle_font_root).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (fontToggle) {

							// Lazy initilization
							initKeyParticleMap();

							// Free up memory
							starWarParticleMap.clear();

							((TextView) findViewById(R.id.font_now))
									.setText("Keyboard Key");

						} else {

							// Lazy initilization
							initStarWarParticleMap();

							// Free up memory
							keyParticleMap.clear();

							((TextView) findViewById(R.id.font_now))
									.setText("Speech Bubble");
						}

						fontToggle = !fontToggle;

					}
				});

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(outEditText, InputMethodManager.SHOW_IMPLICIT);

		outEditText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					// do your stuff here
				}
				return false;
			}

		});

		rotationBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				ROTATION_SPEED = progress;

			}
		});

		accelarationBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {

						ACCELERATION = ((float) progress) / (-10000);

					}
				});

		Typeface font = Typeface.createFromAsset(getAssets(), "Starjout.ttf");
		outEditText.setTypeface(font);

		outEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				if (mp.isPlaying()) {
					mp.stop();
					mp.release();
					mp = MediaPlayer.create(StarWarKeyBoardActivity.this,
							R.raw.laser);
				}
				mp.start();

				String temp = "";

				if (count > before) {

					// text added
					temp = s.toString().subSequence(before, count).toString();

					input = s.toString();
				} else {

					// backpress
					if (count > 0) {
						temp = input.subSequence(count, before).toString();
						input = s.toString();
					}
				}

				if (fontToggle) {

					if (null != temp && !temp.isEmpty())
						generateBubbleParticles(temp);

				} else {
					if (null != temp && !temp.isEmpty())
						generateStarWarPerticles(temp);
				}

			}

		});
	}

	private void generateBubbleParticles(String temp) {

		if (null != starWarParticleMap.get(temp.toLowerCase().charAt(0))) {

			final int envId = getResources().getIdentifier(
					String.valueOf(temp.toLowerCase().charAt(0)), "id",
					getPackageName());

			new ParticleSystem(StarWarKeyBoardActivity.this, 1,
					starWarParticleMap.get(temp.toLowerCase().charAt(0)), 5000)
					.setAcceleration(ACCELERATION, 270)
					.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
					.setRotationSpeed(ROTATION_SPEED)
					.setFadeOut(200, new AccelerateInterpolator())
					.emitWithGravity(findViewById(envId), Gravity.TOP, 1, 1000);

		}
	}

	private void generateStarWarPerticles(String temp) {

		if (null != keyParticleMap.get(temp.toLowerCase().charAt(0))) {

			final int envId = getResources().getIdentifier(
					String.valueOf(temp.toLowerCase().charAt(0)), "id",
					getPackageName());

			new ParticleSystem(StarWarKeyBoardActivity.this, 1,
					keyParticleMap.get(temp.toLowerCase().charAt(0)), 5000)
					.setAcceleration(ACCELERATION, 270)
					.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
					.setRotationSpeed(ROTATION_SPEED)
					.setFadeOut(200, new AccelerateInterpolator())
					.emitWithGravity(findViewById(envId), Gravity.TOP, 1, 1000);

		}
	}

	private void initKeyParticleMap() {

		keyParticleMap = new HashMap<Character, Integer>();

		char charValue;

		for (int i = AppConstants.INT_VALUE_OF_A; i < AppConstants.INT_VALUE_OF_Z; i++) {
			charValue = (char) i;

			keyParticleMap.put(
					charValue,
					getResources().getIdentifier(String.valueOf(charValue),
							"drawable", getPackageName()));

		}
	}

	private void initStarWarParticleMap() {

		starWarParticleMap = new HashMap<Character, Integer>();

		char charValue;

		for (int i = AppConstants.INT_VALUE_OF_A; i < AppConstants.INT_VALUE_OF_Z; i++) {

			charValue = (char) i;

			starWarParticleMap.put(
					charValue,
					getResources().getIdentifier(
							String.valueOf(charValue) + "_sw", "drawable",
							getPackageName()));

		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			if (hasFocus) {
				getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
								| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
								| View.SYSTEM_UI_FLAG_FULLSCREEN
								| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			}
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

	private void configureFABReveal(FABRevealLayout fabRevealLayout) {
		fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
			@Override
			public void onMainViewAppeared(FABRevealLayout fabRevealLayout,
					View mainView) {
			}

			@Override
			public void onSecondaryViewAppeared(
					final FABRevealLayout fabRevealLayout, View secondaryView) {
				prepareBackTransition(fabRevealLayout);
			}
		});
	}

	private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				fabRevealLayout.revealMainView();
			}
		}, 2000);
	}

}
