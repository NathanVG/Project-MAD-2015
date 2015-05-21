package be.howest.nmct.projectmad.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

import be.howest.nmct.projectmad.admin.KSA;
import be.howest.nmct.projectmad.data.KSALocaties;

/**
 * Created by Nathan on 21/05/15.
 */
public class KSALoader extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;

    private final String[] mColumnNames = new String[]{
            BaseColumns._ID,
            KSAInfo.KSAColumns.COLUMN_KSA_NAAM,
            KSAInfo.KSAColumns.COLUMN_KSA_ADRES
    };

    private static Object lock =  new Object();

    public KSALoader (Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        if (mCursor == null) {
            loadCursor();
        }

        return mCursor;
    }

    private void loadCursor() {

        synchronized (lock) {
            if (mCursor != null) return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;

            for (KSA ksa : KSALocaties.getKsas()) {
                MatrixCursor.RowBuilder row = cursor.newRow();
                row.add(id);
                row.add(ksa.getNaam());
                id++;
            }
            mCursor = cursor;
        }
    }




}
