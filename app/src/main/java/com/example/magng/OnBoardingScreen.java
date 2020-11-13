package com.example.magng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingScreen extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private ImageButton imageButton;
    private ImageView imageView;
    private LinearLayout layoutOnboardingIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);
        imageButton = findViewById(R.id.imageButton);
        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);

        setupOnboardingItems();
        setUpOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        //button to navigate the slides and then open the sign in page activity
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        // onboard screen details
        OnboardingItem onboardingMessage = new OnboardingItem();
        onboardingMessage.setTextDescription("Share your magazines, with friends and family, see what others are reading.");
        onboardingMessage.setImage(R.drawable.onboard_image);

        // onboard Magazine screen
        OnboardingItem onboardMagazine = new OnboardingItem();
        onboardMagazine.setTextDescription("Magazines at your finger tips. Easily access your magazines anywhere any time across all devices.");
        onboardMagazine.setImage(R.drawable.message_icon);

        OnboardingItem onboardingNotification = new OnboardingItem();
        onboardingNotification.setTextDescription("Explore and enjoy thousands of magazines. Read your magazines online or download them for offline reading.");
        onboardingNotification.setImage(R.drawable.lens_icon);

        onboardingItems.add(onboardingMessage);
        onboardingItems.add(onboardingNotification);
        onboardingItems.add(onboardMagazine);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setUpOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.onboarding_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);

        }
    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_inactive)
                );
            }
        }
    }
}