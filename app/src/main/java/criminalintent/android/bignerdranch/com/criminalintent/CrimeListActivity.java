package criminalintent.android.bignerdranch.com.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Stefan on 05-Mar-16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
