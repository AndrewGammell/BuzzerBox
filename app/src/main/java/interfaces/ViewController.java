package interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by Devstream on 29/09/2015.
 */
public interface ViewController {

 void replaceWithFragment(Fragment frag);

 void addFragment(Fragment fragment);

 boolean isFragmentDisplayed();


}


