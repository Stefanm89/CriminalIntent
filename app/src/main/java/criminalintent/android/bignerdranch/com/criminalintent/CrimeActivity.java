/*
package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    //public static final String EXTRA_CRIME_ID = "crimeId";
    private static final String EXTRA_CRIME_ID = "crimeId";

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);//го добива crimeId од CrimeListFragment
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);//да може да го земе тоа што ќе стигне, it will pass in the UUID it retrieved from its extra (EXTRA_CRIME_ID)
        return CrimeFragment.newInstance(crimeId);
    }
}

*/
