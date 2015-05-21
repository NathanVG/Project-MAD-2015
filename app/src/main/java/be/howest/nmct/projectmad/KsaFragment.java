package be.howest.nmct.projectmad;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import be.howest.nmct.projectmad.loader.KSAInfo;
import be.howest.nmct.projectmad.loader.KSALoader;

public class KsaFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private KSAAdapter mKsaAdapter;
    private OnKsaFragmentListener mListener;

    public KsaFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] columns = new String[]{
                KSAInfo.KSAColumns.COLUMN_KSA_NAAM,
        };
        int[] textView_ids = new int[]{
                R.id.txbksaNaam
        };
        mKsaAdapter = new KSAAdapter(getActivity(), R.layout.row_ksa, null, columns, textView_ids, 0);
        setListAdapter(mKsaAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        return new KSALoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        mKsaAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){mKsaAdapter.swapCursor(null);}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor c = (Cursor) mKsaAdapter.getItem(position);
        String selectedKSAnaam = c.getString(c.getColumnIndex(KSAInfo.KSAColumns.COLUMN_KSA_NAAM));
        mListener.demandKsaDetail(selectedKSAnaam);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnKsaFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnKsaFragmentListener");
        }
    }


    public interface  OnKsaFragmentListener {
        public void demandKsaDetail(String sNaamKsa);
    }


    class KSAAdapter extends SimpleCursorAdapter {
        public KSAAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags)
        {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor)
        {
            super.bindView(view, context, cursor);



            int colnr = cursor.getColumnIndex(KSAInfo.KSAColumns.COLUMN_KSA_NAAM);
            TextView txbNaam = (TextView) view.findViewById(R.id.txbksaNaam);
        }
    }


}

