package criminalintent.android.bignerdranch.com.criminalintent;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Stefan on 03-Mar-16.
 */
public class Crime {
    private String mTitle;
    private UUID mId;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime() {
        this(UUID.randomUUID());
        //mId = UUID.randomUUID();
        //mDate = new Date();
    }
    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }//само getter пошто е read-only

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
