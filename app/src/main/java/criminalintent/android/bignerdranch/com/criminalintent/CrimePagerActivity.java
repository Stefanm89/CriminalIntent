package criminalintent.android.bignerdranch.com.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Stefan on 24-Mar-16.
 */
public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID = "criminalintent.android.bignerdranch.com.criminalintent.crimeId";
    private ViewPager mViewPager;//мора да му се стави адаптер
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);//за да може да го земе тоа што ќе стигне во EXTRA_CRIME_ID

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {//му ставам adapter и со таб ми го отвара цел метод
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);//која position на АrrayList
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {//number of items in the ArrayList
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++){//за да ми го отвори тој Crime што сум го стиснал, а не да почне од #0
            if (mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
