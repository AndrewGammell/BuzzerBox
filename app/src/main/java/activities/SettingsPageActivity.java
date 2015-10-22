package activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import holder.DataHolder;
import io.buzzerbox.app.R;

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

    private class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
//        return SettingsFragment.newInstance(DataHolder.getDataHolder()
//                                  .getListOfBoxes()
//                                  .get(i)
//                                  .getSettings());
            return new Fragment();

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
            getSupportActionBar().setTitle(
                    DataHolder.getDataHolder()
                            .getListOfBoxes()
                            .get(i)
                            .getType());
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
