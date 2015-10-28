package activities;

import SQLLite.SettingsDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import fragments.SettingsFragment;
import holder.DataHolder;
import io.buzzerbox.app.R;
import persistence.DataPersister;

/**
 * Created by Devstream on 22/10/2015.
 */
public class SettingsPageActivity extends AppCompatActivity {

    private final String CALL_POSITION = "CALL_POSITION";

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_layout);

        position = getIntent().getIntExtra(CALL_POSITION,0);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        mViewPager.setCurrentItem(position);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
//        return SettingsFragment.newInstance(DataHolder.getDataHolder()
//                .getListOfBoxes()
//                .get(i)
//                .getSettings());
            Log.d("S","DataHolder Settings List "+ DataHolder.getDataHolder().getSettingsList().size());

            return SettingsFragment.newInstance(DataHolder.getDataHolder().getSettingsList().get(i));

        }

        @Override
        public int getCount() {
            return DataHolder.getDataHolder().getListOfBoxes().size();
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            position = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        new SettingsDatabase(getBaseContext()).runBackGroundSaver();
//    }

    //     uses the DataPerisiter class to save the User when this Activity is Destroyed.
//    DataHolder needs to be saved;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataPersister.saveUser(this);
        new SettingsDatabase(this).runBackGroundSaver();
    }
}
