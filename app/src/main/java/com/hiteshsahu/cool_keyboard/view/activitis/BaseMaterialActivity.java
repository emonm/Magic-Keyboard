package com.hiteshsahu.cool_keyboard.view.activitis;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

import com.hiteshsahu.cool_keyboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Hitesh.Sahu on 5/26/2017.
 * <p>
 * Base Activity  class which Enter and Exit with Circular Reveal animation
 * and bind views with ButterKnife
 */

public abstract class BaseMaterialActivity extends AppCompatActivity {

    @BindView(R.id.app_root)
    View appRoot;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        unbinder = ButterKnife.bind(this);

        if (appRoot != null) {
            doCircularReveal();
        }

        setUpToolBar();

        setUpLayout();

    }

    private void doCircularReveal() {

        appRoot.setVisibility(View.INVISIBLE);

        getWindow().getDecorView().findViewById(android.R.id.content)
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext()
                        , getRevealBgColor()));

        ViewTreeObserver viewTreeObserver = appRoot.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    circularRevealView(appRoot);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        appRoot.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        appRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract int getActivityLayout();

    protected abstract int getRevealBgColor();

    protected abstract void setUpToolBar();

    protected abstract void setUpLayout();

    @Override
    public void onBackPressed() {
        animateExitScreen();
    }

    /*
     Animate Circular Exit activity
     */
    public void animateExitScreen() {

        //Slide out toolbar to Distract user
        if (null != toolbar) {
            toolbar.animate().y(-500f).setDuration(500);
        }

        //Circular exit Animation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = exitReveal(appRoot);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    supportFinishAfterTransition();
                }
            });
            anim.start();
        } else {
            finish();
        }
    }

    public static void circularRevealView(View revealLayout) {

        int cx = revealLayout.getWidth() / 2;
        int cy = revealLayout.getHeight() / 2;

        float finalRadius = Math.max(revealLayout.getWidth(), revealLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0, finalRadius);

            circularReveal.setDuration(1000);

            // make the view visible and start the animation
            revealLayout.setVisibility(View.VISIBLE);

            circularReveal.start();
        } else {
            revealLayout.setVisibility(View.VISIBLE);
        }
    }

    public static Animator exitReveal(final View myView) {
        // previously visible view

        // get the center for the clipping circle
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;


        // get the initial radius for the clipping circle
        int initialRadius = myView.getWidth() / 2;

        // create the animation (the final radius is zero)
        Animator anim =
                null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });
        }

        //  anim.setDuration(800);

        // start the animation
        return anim;
    }
}
