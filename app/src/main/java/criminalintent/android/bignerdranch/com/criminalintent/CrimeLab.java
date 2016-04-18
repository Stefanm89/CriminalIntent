package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import criminalintent.android.bignerdranch.com.criminalintent.database.CrimeBaseHelper;
import criminalintent.android.bignerdranch.com.criminalintent.database.CrimeCursorWrapper;
import criminalintent.android.bignerdranch.com.criminalintent.database.CrimeDbSchema;
import criminalintent.android.bignerdranch.com.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by Stefan on 05-Mar-16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //private ArrayList<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
        //mCrimes = new ArrayList<>();

        /*for (int i = 0; i < 100; i++ ){//да генерира 100 crimes
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);//секое второ
            mCrimes.add(crime);
        }*/
    }

    public void addCrime(Crime c) {
        //mCrimes.add(c);
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);//1st argument е во која табела сакаш да ги ставиш, 3rd argument е data што сакаш да ја ставиш во табелата.
    }

    public void deleteCrime(Crime d) {
        //mCrimes.remove(d);
    }

    public List<Crime> getCrimes() {//getter за mCrimes
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();//to pull data out of a cursor
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();//advance a new row
            }

        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
        /*for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;*/
        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Cols.UUID + " = ?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});//1st - која табела, 2nd - data, 3rd - which row gets updated, 4th - values for that row.
    }

    private static ContentValues getContentValues(Crime crime) {//Writes and updates to databases
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());//CrimeTable.Cols.UUID е key, crime.getId() е value
        values.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return values;
    }

    //private Cursor queryCrimes(String whereClause, String[] whereArgs) {//to read from a database
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,//null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);

    }
}
