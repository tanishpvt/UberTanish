package com.example.ubertanish;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jorgecastilloprz.FABProgressCircle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Gravity.LEFT;

public class LoginWithPhone extends AppCompatActivity {

    @BindView(R.id.ivback)
    ImageView ivBack;

    @BindView(R.id.etPhoneNo)
    EditText etPhoneNo;

    @BindView(R.id.tvMoving)
    TextView tvMoving;

    @BindView(R.id.ivFlag)
    ImageView ivFlag;

    @BindView(R.id.tvCode)
    TextView tvCode;

    @BindView(R.id.fabProgressCircle)
    FABProgressCircle fabProgressCircle;

    @BindView(R.id.rootFrame)
    FrameLayout rootFrame;

    @BindView(R.id.llphone)
    LinearLayout llPhone;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone);
        ButterKnife.bind(this);
        setupWindowAnimations();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupWindowAnimations() {

        ChangeBounds enterTransition = new ChangeBounds();
        enterTransition.setDuration(1000);
        enterTransition.setInterpolator(new DecelerateInterpolator(4));
        Transition.TransitionListener enterTransitionListener= new Transition.TransitionListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTransitionStart(Transition transition) {
                ivBack.setImageAlpha(0);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTransitionEnd(Transition transition) {

                ivBack.setImageAlpha(255);
                Animation animation = AnimationUtils.loadAnimation(LoginWithPhone.this, R.anim.slide_right);
                ivBack.startAnimation(animation);

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };

        ;
        enterTransition.addListener(enterTransitionListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(enterTransition);
        }

        ChangeBounds returnTransition = new ChangeBounds();
        returnTransition.setDuration(1000);
        Transition.TransitionListener returnTransitionListener = new Transition.TransitionListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTransitionStart(Transition transition) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etPhoneNo.getWindowToken(), 0);
                tvMoving.setText(null);
                tvMoving.setHint(getString(R.string.enter_no));
                ivFlag.setImageAlpha(0);
                tvCode.setAlpha(0);
                etPhoneNo.setVisibility(View.GONE);
                etPhoneNo.setCursorVisible(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    etPhoneNo.setBackground(null);
                }
                Animation animation = AnimationUtils.loadAnimation(LoginWithPhone.this, R.anim.slide_left);
                ivBack.startAnimation(animation);
            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };


        ;
        returnTransition.addListener(returnTransitionListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementReturnTransition(returnTransition);
        }

        Slide exitSlide = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exitSlide = new Slide(LEFT);
        }
        exitSlide.setDuration(1000);
        Transition.TransitionListener exitTransitionListener=new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                rootFrame.setAlpha(1f);
                fabProgressCircle.hide();
                llPhone.setBackgroundColor(Color.parseColor("#EFEFEF"));
            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };

        ;
        exitSlide.addListener(exitTransitionListener);
        exitSlide.addTarget(R.id.llphone);
        exitSlide.setInterpolator(new DecelerateInterpolator());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(exitSlide);
        }

        Slide reenterSlide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            reenterSlide = new Slide(LEFT);
        }
        reenterSlide.setDuration(1000);
        Transition.TransitionListener reenterTransitionListener= new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

                llPhone.setBackgroundColor(Color.parseColor("#FFFFFF"));
                etPhoneNo.setCursorVisible(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };;
        reenterSlide.addListener(reenterTransitionListener);
        reenterSlide.setInterpolator(new DecelerateInterpolator(2));
        reenterSlide.addTarget(R.id.llphone);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setReenterTransition(reenterSlide);
        }



    }


    @OnClick(R.id.fabProgressCircle)
    void nextPager() {

        etPhoneNo.setCursorVisible(false);
        rootFrame.setAlpha(0.4f);
        fabProgressCircle.show();



        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {

                Intent intent = new Intent(LoginWithPhone.this, PasswordActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LoginWithPhone.this);
                startActivity(intent, options.toBundle());
            }
        }, 1000);
    }

    @OnClick(R.id.ivback)
    void startReturnTransition() {
        super.onBackPressed();
    }
}
