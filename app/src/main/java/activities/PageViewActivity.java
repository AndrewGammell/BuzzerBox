package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import fragments.LogViewFragment;
import fragments.OverviewFragment;
import io.buzzerbox.app.R;
import persistence.DataPersister;
import util.MessageTools;
import util.Utility;


public class PageViewActivity extends AppCompatActivity implements OverviewFragment.Callback
        , LogViewFragment.Callback {
    //    private static final String OBJECT_KEY = "OBJECT";
//    private static final String BUNDLE_KEY = "BUNDLE";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private PageChangeListener mPageChangeListener = new PageChangeListener();
    private ActionBar mActionBar;
    private ActionBar.Tab overviewTab;
    private ActionBar.Tab logTab;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_layout);

        mActionBar = getSupportActionBar();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        createTabs();
    }

    private void createTabs() {
        mActionBar.setNavigationMode(mActionBar.NAVIGATION_MODE_TABS);


        overviewTab = mActionBar.newTab();
        overviewTab.setText("Overview");
        overviewTab.setTabListener(new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(0);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });

        logTab = mActionBar.newTab();
        logTab.setText("Log");
        logTab.setTabListener(new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(1);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        });

        mActionBar.addTab(overviewTab);
        mActionBar.addTab(logTab);
    }


    //     uses the DataPerisiter class to save the User when this Activity is stopped.
//    DataHolder needs to be saved;
    @Override
    protected void onStop() {
        super.onStop();
        DataPersister.saveUser(this);
//        DataPersister.saveDataHolder(this);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
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
            Log.d("PAGE", "getIPageTitle()");
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
            case R.id.action_logout:
                MessageTools.showShortToast(this, "logged out");
                goToLogin();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    calls the logout method in the Utility class then,
//    recalls the MainActivity with flags to prevent returning with back press.
    private void goToLogin() {
        Utility.logout(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //     Using onPageSelected to set the ActionBar title to the currently displayed fragment.
    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mActionBar.selectTab(overviewTab);
                    break;
                case 1:
                    mActionBar.selectTab(logTab);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

}