package com.serveroverload.emittor_key;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
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

public class KeyBoardActivity extends Activity /* implements OnClickListener */{

	private static final float ACCELERATION_DEFAULT = -0.00013f;
	private static final int ROTATION_SPEED_DEFAULT = 144;

	private float ACCELERATION = ACCELERATION_DEFAULT;
	private int ROTATION_SPEED = ROTATION_SPEED_DEFAULT;
	public String input;
	private boolean fontToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keyboard_physics);
		// findViewById(R.id.button1).setOnClickListener(this);

		EditText yourEditText = (EditText) findViewById(R.id.edit);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(yourEditText, InputMethodManager.SHOW_IMPLICIT);

		yourEditText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					// do your stuff here
				}
				return false;
			}

		});

		SeekBar rotationBar = (SeekBar) findViewById(R.id.rotn);
		SeekBar accelarationBar = (SeekBar) findViewById(R.id.acc);

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

				// Toast.makeText(getApplicationContext(), "rotn"+
				// progress+"/1000", 100).show();

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

						// Toast.makeText(getApplicationContext(), "rotn"+
						// ACCELERATION+"/-20", 100).show();

					}
				});

		findViewById(R.id.toggle_font_root).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (fontToggle) {

							((TextView) findViewById(R.id.font_now))
									.setText("Keyboard Key");
						} else {
							((TextView) findViewById(R.id.font_now))
									.setText("Speech Bubble");
						}

						fontToggle = !fontToggle;

					}
				});

		yourEditText.addTextChangedListener(new TextWatcher() {

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

					if (temp.equalsIgnoreCase("A")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().a256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.a),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("B")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().b256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.b),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("c")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().c256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.c),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("d")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().d256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.d),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("e")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().e256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.e),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("f")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().f256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.f),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("g")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().g256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.g),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("h")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().h256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.h),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("i")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().i256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.i),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("j")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().j256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.j),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("k")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().k256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.k),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("l")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().l256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.l),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("m")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().m256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.m),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("n")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().n256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.n),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("o")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().o256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.o),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("p")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().p256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.p),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("q")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().q256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.q),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("r")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().r256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.r),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("s")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().s256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.s),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("t")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().t256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.t),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("u")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().u256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.u),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("v")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().v256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.v),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("w")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().w256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.w),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("x")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().x256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.x),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("y")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().y256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.y),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("z")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().z256, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.z),
										Gravity.TOP, 1, 1000);
					}

				} else {

					if (temp.equalsIgnoreCase("A")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().a, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.a),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("B")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().b, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.b),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("c")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().c, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.c),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("d")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().d, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.d),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("e")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().e, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.e),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("f")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().f, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.f),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("g")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().g, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.g),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("h")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().h, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.h),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("i")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().i, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.i),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("j")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().j, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.j),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("k")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().k, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.k),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("l")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().l, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.l),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("m")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().m, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.m),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("n")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().n, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.n),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("o")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().o, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.o),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("p")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().p, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.p),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("q")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().q, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.q),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("r")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().r, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.r),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("s")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().s, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.s),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("t")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().t, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.t),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("u")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().u, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.u),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("v")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().v, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.v),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("w")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().w, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.w),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("x")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().x, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.x),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("y")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().y, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.y),
										Gravity.TOP, 1, 1000);
					} else if (temp.equalsIgnoreCase("z")) {
						new ParticleSystem(KeyBoardActivity.this, 1,
								new R.drawable().z, 5000)
								.setAcceleration(ACCELERATION, 270)
								.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
								.setRotationSpeed(ROTATION_SPEED)
								.setFadeOut(200, new AccelerateInterpolator())
								.emitWithGravity(findViewById(R.id.z),
										Gravity.TOP, 1, 1000);
					}
				}

			}
		});

	}

	// -----------------BEFORE-----------------
	// else if (temp.equalsIgnoreCase("B")) {
	// new ParticleSystem(KeyBoardActivity.this, 1, new R.drawable().b,
	// 5000).setAcceleration(-0.00013f, 270)
	// .setSpeedModuleAndAngleRange(0f, 1.0f, 180,
	// 360).setRotationSpeed(ROTATION_SPEED)
	// .setAcceleration(-0.000017f, 0).emit(findViewById(R.id.edit), 1, 1000);
	// }

	// @Override
	// public void onClick(View arg0) {
	//
	// new ParticleSystem(this, 5, R.drawable.a,
	// 5000).setSpeedModuleAndAngleRange(0f, 1.0f, 180, 270)
	// .setRotationSpeed(ROTATION_SPEED).setAcceleration(-0.000017f,
	// 270).emit(findViewById(R.id.edit), 8);
	// }
}
