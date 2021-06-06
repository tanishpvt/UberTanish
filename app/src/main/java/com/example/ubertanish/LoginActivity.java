package com.example.ubertanish;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.llphone)
    LinearLayout llphone;

    @BindView(R.id.ivUberLogo)
    ImageView uber;

    @BindView(R.id.tvMoving)
    TextView tvMoving;

    @BindView(R.id.tvPhoneNo)
    TextView tvPhoneNo;

    @BindView(R.id.llInfo)
    LinearLayout llInfo;

    @BindView(R.id.ivFlag)
    ImageView ivFlag;

    @BindView(R.id.tvCode)
    TextView tvCode;

    @BindView(R.id.ivback)
    ImageView ivBack;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setupWindowAnimations();
        }
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        uber.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (0.65 * height)));
        ivBack.setImageAlpha(0);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupWindowAnimations() {

        ChangeBounds exitTransition = new ChangeBounds();
        exitTransition.setDuration(1000);
        exitTransition.addListener(new Transition.TransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {

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
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementExitTransition(exitTransition);
        }

        ChangeBounds reenterTransition = new ChangeBounds();
        reenterTransition.setDuration(1000);
        Transition.TransitionListener reenterListener = null;
        reenterTransition.addListener(reenterListener);
        reenterTransition.setInterpolator(new DecelerateInterpolator(4));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementReenterTransition(reenterTransition);
        }
       reenterListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(ObjectAnimator.ofFloat(tvMoving, "alpha", 0f, 1f));
                animatorSet.setDuration(800);
                animatorSet.start();
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

                tvMoving.setAlpha(1);
            }
        };
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.llphone, R.id.ivFlag, R.id.tvPhoneNo})
    void startTransition() {

        Intent intent = new Intent(LoginActivity.this, LoginWithPhone.class);
        Pair[] pairs=new Pair[5];
        pairs[0] =new  Pair<View, String>((View) ivBack, getString(R.string.transition_arrow));
        pairs[1]  = new  Pair<View, String>((View) ivFlag, getString(R.string.transition_ivFlag));
        pairs[2]  = new  Pair<View, String>((View) tvCode, getString(R.string.transition_tvCode));
        pairs[3]  = new  Pair<View, String>((View) tvPhoneNo, getString(R.string.transition_tvPhoneNo));
        pairs[4]  = new  Pair<View, String>((View) llphone, getString(R.string.transition_llPhone));
        ActivityOptions options= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
        }
        startActivity(intent, options.toBundle());


    }

}
