package criminalintent.android.bignerdranch.com.criminalintent.database;

/**
 * Created by Stefan on 30-Mar-16.
 */
public class CrimeDbSchema {
    public static final class CrimeTable {//шема за табелата
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
