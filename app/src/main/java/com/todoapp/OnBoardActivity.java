package com.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoardActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_on_board, container, false);
            int number = getArguments().getInt(ARG_SECTION_NUMBER);
            TextView textView = rootView.findViewById(R.id.textTitle);
            ImageView imageView = rootView.findViewById(R.id.imageView);
            Button button = rootView.findViewById(R.id.button);
            switch (number) {
                case 0:
                    textView.setText("Привет");
                    imageView.setImageResource(R.drawable.smile1);
                    rootView.setBackgroundResource(R.color.fragment1);
                    button.setVisibility(View.GONE);
                    break;
                case 1:
                    textView.setText("Как дела?");
                    imageView.setImageResource(R.drawable.smile2);
                    rootView.setBackgroundResource(R.color.fragment2);
                    button.setVisibility(View.GONE);
                    break;
                case 2:
                    textView.setText("Что делаешь?");
                    imageView.setImageResource(R.drawable.smile3);
                    rootView.setBackgroundResource(R.color.fragment3);
                    break;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveState();
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                }
            });

            return rootView;
        }

        private void saveState() {
            SharedPreferences preferences = getContext().getSharedPreferences("settings", MODE_PRIVATE);
            preferences.edit().putBoolean("shown", true).apply();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
