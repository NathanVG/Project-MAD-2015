package be.howest.nmct.projectmad;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.howest.nmct.projectmad.admin.KSA;
import be.howest.nmct.projectmad.data.KSALocaties;


/**
 * A simple {@link Fragment} subclass.
 */
public class KsaDetailFragment extends Fragment {

    private static final String EXTRA_NAAM = "nmct.howest.be.projectmad.NAAM";

    private TextView textView_naam;
    private TextView textView_adres;
    private TextView textView_stad;
    public KSA ksa;
    private Button toonKaart;

    private onKSADetailsListener onKSADetailsListener;

    public interface onKSADetailsListener{
        public void onShowKSA(String naam);
    }


    public KsaDetailFragment() {
        // Required empty public constructor
    }

    public static KsaDetailFragment newInstance(String sKSAnaam){
        KsaDetailFragment fragment = new KsaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAAM, sKSAnaam);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onKSADetailsListener = (onKSADetailsListener) activity;
        } catch (ClassCastException ex){
            throw new ClassCastException(activity.toString() + " implement interface OnShopDetailsFragmentListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ksa_detail, container, false);

        textView_naam = (TextView) v.findViewById(R.id.txtKsaNaam);
        textView_adres = (TextView) v.findViewById(R.id.txbStraatNr);
        textView_stad = (TextView) v.findViewById(R.id.txbZipCity);

        String naam = getArguments().getString(EXTRA_NAAM);
        ksa = KSALocaties.getKSA(naam);

        textView_naam.setText(ksa.getNaam());
        textView_adres.setText(ksa.getAdres());
        textView_stad.setText(ksa.getGemeente());

        toonKaart = (Button) v.findViewById(R.id.btnMap);
        toonKaart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMap();
            }
        });


        return v;
    }

    private void ShowMap(){
        if(onKSADetailsListener != null)
        {
            onKSADetailsListener.onShowKSA(ksa.getNaam());
        }
    }


}
