package criminalintent.android.bignerdranch.com.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Stefan on 05-Mar-16.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();//за да ставиш fragment во activity мора да пратиш повик до FragmentManager.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);//fragment го поврзувам со fragment container-от.

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();//add fragment in the fragment list.
        }
    }
}
