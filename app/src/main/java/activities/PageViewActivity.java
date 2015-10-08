package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import fragments.LogViewFragment;
import fragments.OverviewFragment;
import io.buzzerbox.app.R;
import persistence.DataPersister;
import util.MessageTools;
import util.Utility;

import java.util.Locale;

/**
 * Created by Devstream on 06/10/2015.
 */
public class PageViewActivity extends AppCompatActivity implements OverviewFragment.Callback
        , LogViewFragment.Callback {
    private static final String OBJECT_KEY = "OBJECT";
    private static final String BUNDLE_KEY = "BUNDLE";
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;


    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_layout);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        getSupportActionBar().setTitle(mSectionsPagerAdapter.getPageTitle(0));
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataPersister.saveUser(this);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("PAGE","getItem()");
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = OverviewFragment.newInstance();

                    break;
                case 1:
                    fragment = LogViewFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("PAGE","getIPageTitle()");
            switch (position) {
                case 0:
                    return getResources().getString(R.string.title_overview);
                case 1:
                    return getResources().getString(R.string.title_log);
            }
            return "null";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Utility.logout(this);
                MessageTools.showShortToast(this, "logged out");
                goToLogin();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private class PageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            getSupportActionBar().setTitle(mSectionsPagerAdapter.getPageTitle(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}