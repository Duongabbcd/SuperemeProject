package com.example.superemeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {
    private ImageView iconImage ;
    private LinearLayout linearLayout ;
    private Button register ;
    private Button login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        linearLayout = findViewById(R.id.linear_layout);
        iconImage = findViewById(R.id.icon_image) ;
        register = findViewById(R.id.Register);
        login=findViewById(R.id.Login) ;

        linearLayout.animate().alpha(0f).setDuration(1);

  TranslateAnimation animation = new TranslateAnimation(0,0,0,-1600) ;
        animation.setDuration(2000);
       animation.setFillAfter(false);
        animation.setAnimationListener(new StartActivity.MyAnimationListener());

       iconImage.setAnimation(animation);

      register.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
              startActivity(new Intent(StartActivity.this,RegisterActivity.class).
                      addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
          }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,LoginActivity.class).
                     addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
           }
        });
    }

    private class MyAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

       }

       @Override
       public void onAnimationEnd(Animation animation) {
           iconImage.clearAnimation();
            iconImage.setVisibility(View.INVISIBLE);
           linearLayout.animate().alpha(1f).setDuration(1600);
       }

       @Override
       public void onAnimationRepeat(Animation animation) {

        }
   }
   protected void onStart(){
        super.onStart();

       if (FirebaseAuth.getInstance().getCurrentUser() != null) {
           startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
       }
   }
}