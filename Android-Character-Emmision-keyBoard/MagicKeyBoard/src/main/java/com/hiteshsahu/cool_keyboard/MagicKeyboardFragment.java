package com.hiteshsahu.cool_keyboard;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.plattysoft.leonids.ParticleSystem;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hiteshsahu.cool_keyboard.HomeActivity.ACCELERATION;
import static com.hiteshsahu.cool_keyboard.HomeActivity.ROTATION_SPEED;

public class MagicKeyboardFragment extends Fragment implements TextWatcher {
    private final static int INT_VALUE_OF_A = 97; //int value of A
    private final static int INT_VALUE_OF_Z = INT_VALUE_OF_A + 26; //int value of Z
    private static final String ARG_SECTION_NUMBER = "FragNumber";
    private String inputStringText;
    //Map of Key board Character and associated Drawable ID to emit on tap of that char
    //for keyboard drawables
    public static HashMap<Character, Integer> keyParticleMap;
    //for  chat bubble drawables
    public static HashMap<Character, Integer> bubbleParticleMap;
    //for star war drawables
    public static HashMap<Character, Integer> starWarParticleMap;
    //Media player for tap sound
    private MediaPlayer typeSoundPlayer;
    private View rootView;
    private int fragmentPosition;

    //input field
    @BindView(R.id.edit)
    EditText inputEditText;
    //cover image
    @BindView(R.id.background_img)
    ImageView bgCover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.demo_fragment,
                container, false);
        ButterKnife.bind(this, rootView);
        fragmentPosition = getArguments().getInt(ARG_SECTION_NUMBER);
        initParticleMap();
        switch (fragmentPosition) {
            case 0:
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.type_sound);
                inputEditText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                        "Adler.ttf"));
                Glide.with(getActivity()).load(R.drawable.typewriter).into(bgCover);
                break;
            case 1:
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.laser);
                inputEditText.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),
                        "Starjout.ttf"));
                Glide.with(getActivity()).load(R.drawable.vader).into(bgCover);
                break;
            case 2:
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.type_sound);
                Glide.with(getActivity()).load(R.drawable.kitten).into(bgCover);
                break;
        }

        inputEditText.addTextChangedListener(MagicKeyboardFragment.this);
        inputEditText.requestFocus();
        return rootView;
    }

    private void makeTapSound() {
        if (typeSoundPlayer.isPlaying()) {
            typeSoundPlayer.stop();
            typeSoundPlayer.release();
            if (fragmentPosition == 0) {
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.type_sound);
            } else if (fragmentPosition == 1) {
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.laser);
            } else {
                typeSoundPlayer = MediaPlayer.create(getActivity(), R.raw.type_sound);
            }
        }
        typeSoundPlayer.start();
    }

    private void initParticleMap() {
        //Map for Keyboard Key drawables
        keyParticleMap = new HashMap<>();
        //Map for Bubble Key drawables
        bubbleParticleMap = new HashMap<>();
        //Map for StarWar Key drawables
        starWarParticleMap = new HashMap<>();

        char charValue;

        for (int i = INT_VALUE_OF_A; i < INT_VALUE_OF_Z; i++) {
            //Char to Emit
            charValue = (char) i;

            //Map drawable id with Char Value
            keyParticleMap.put(
                    charValue,
                    getResources().getIdentifier(String.valueOf(charValue),
                            "drawable", //Drawable ID
                            getActivity().getPackageName()));

            bubbleParticleMap.put(
                    charValue,
                    getResources().getIdentifier(
                            String.valueOf(charValue) + "256", //Drawable ID
                            "drawable",
                            getActivity().getPackageName()));

            starWarParticleMap.put(
                    charValue, //Char
                    getResources().getIdentifier(
                            String.valueOf(charValue) + "_sw",  //Drawable ID
                            "drawable",
                            getActivity().getPackageName()));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence inputCharSequence, int start, int before, int count) {

        makeTapSound();

        String inputCharacter = "";

        if (count > before) {
            // text added
            inputCharacter = inputCharSequence.toString()
                    .subSequence(before, count)
                    .toString();
        } else {
            // text Removed
            if (count > 0) {
                inputCharacter = inputStringText
                        .subSequence(count, before)
                        .toString();
            }
        }

        //Store for future refrence
        inputStringText = inputCharSequence.toString();

        if (fragmentPosition == 0) {
            if (!inputCharacter.isEmpty())
                emitKeyBoardParticle(inputCharacter.toLowerCase().charAt(0));
        } else if (fragmentPosition == 1) {
            if (!inputCharacter.isEmpty())
                emitStarWarParticle(inputCharacter.toLowerCase().charAt(0));
        } else {
            if (!inputCharacter.isEmpty())
                emitChatBubbleParticle(inputCharacter.toLowerCase().charAt(0));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    /**
     * @param charToEmit
     */
    private void emitKeyBoardParticle(char charToEmit) {
        Integer drawableRedId = keyParticleMap.get(charToEmit);
        if (null != drawableRedId) {

            final int viewID = getResources()
                    .getIdentifier(
                            String.valueOf(charToEmit), "id",
                            getActivity().getPackageName());

            new ParticleSystem(getActivity(), 1, drawableRedId, 5000)
                    .setAcceleration(ACCELERATION, 270)
                    .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                    .setRotationSpeed(ROTATION_SPEED)
                    .setFadeOut(200, new AccelerateInterpolator())
                    .emitWithGravity(rootView.findViewById(viewID),
                            Gravity.TOP,
                            1,
                            1000);

        }
    }

    /**
     * @param charToEmit
     */
    private void emitChatBubbleParticle(char charToEmit) {

        if (null != bubbleParticleMap.get(charToEmit)) {

            final int viewID = getResources().getIdentifier(
                    String.valueOf(charToEmit), "id",
                    getActivity().getPackageName());

            new ParticleSystem(getActivity(), 1,
                    bubbleParticleMap.get(charToEmit), 5000)
                    .setAcceleration(ACCELERATION, 270)
                    .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                    .setRotationSpeed(ROTATION_SPEED)
                    .setFadeOut(200, new AccelerateInterpolator())
                    .emitWithGravity(rootView.findViewById(viewID),
                            Gravity.TOP,
                            1,
                            1000);

        }
    }

    /**
     * @param charToEmit
     */
    private void emitStarWarParticle(char charToEmit) {

        if (null != starWarParticleMap.get(charToEmit)) {

            final int viewID = getResources()
                    .getIdentifier(
                            String.valueOf(charToEmit),
                            "id",
                            getActivity().getPackageName());

            new ParticleSystem(getActivity(), 1,
                    starWarParticleMap.get(charToEmit), 5000)
                    .setAcceleration(ACCELERATION, 270)
                    .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                    .setRotationSpeed(ROTATION_SPEED)
                    .setFadeOut(200, new AccelerateInterpolator())
                    .emitWithGravity(rootView.findViewById(viewID),
                            Gravity.TOP,
                            1,
                            1000);

        }
    }

    public MagicKeyboardFragment() {
    }

    public static MagicKeyboardFragment newInstance(int sectionNumber) {
        MagicKeyboardFragment fragment = new MagicKeyboardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
