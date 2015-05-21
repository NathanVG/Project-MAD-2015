package be.howest.nmct.projectmad;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import be.howest.nmct.projectmad.admin.KSA;
import be.howest.nmct.projectmad.data.KSALocaties;


/**
 * A simple {@link Fragment} subclass.
 */
public class KSAKaartFragment extends Fragment {

    public static final String EXTRA_NAAM = "ksanaam";
    public LocationManager locationManager;
    public KSA huidigeKSA;
    public List<KSA> ksaList;


    private MapView mapView;
    private GoogleMap googleMap;
    private double longitude;
    private double latitude;
    private String adresKSA;
    private LatLng CENTER = null;


    public KSAKaartFragment() {
        // Required empty public constructor
    }

    public static KSAKaartFragment newKSAKaartFragment(String naam){
        KSAKaartFragment kaartFragment =  new KSAKaartFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_NAAM, naam);
        kaartFragment.setArguments(args);
        return  kaartFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ksakaart, container, false);
        mapView = (MapView) v.findViewById(R.id.KSAkaart);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            huidigeKSA = KSALocaties.getKSA(getArguments().getString(EXTRA_NAAM));
            ksaList = KSALocaties.getKsas();
        }
    }

    public void setMapView(MapView mapView){
        this.mapView = mapView;

        try {
            MapsInitializer.initialize(getActivity());

            switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())){
                case ConnectionResult.SUCCESS:
                    if (mapView != null) {
                        locationManager = ((LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE));
                        Boolean network = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                        try {
                            Geocoder coder = new Geocoder(getActivity(), Locale.getDefault());
                            List<Address> addresses;

                            adresKSA = huidigeKSA.getAdres() + ", " + huidigeKSA.getGemeente();

                            addresses = coder.getFromLocationName(adresKSA, 1);

                            Address location = addresses.get(0);

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                        catch (Exception ex) {
                            System.out.println(ex);
                        }

                        googleMap = mapView.getMap();
                        googleMap.clear();
                        try{
                            googleMap.addMarker(new MarkerOptions()
                                    .position(CENTER)
                                    .title("" + huidigeKSA.getNaam())
                                    .snippet("" + adresKSA));

                        }
                        catch (Exception ex){
                            System.out.print(ex);
                            Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_SHORT).show();
                        }

                        googleMap.setIndoorEnabled(true);
                        googleMap.setMyLocationEnabled(true);
                        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));

                        if (CENTER != null) {
                            googleMap.animateCamera(CameraUpdateFactory.newLatLng(CENTER), 1750, null);
                        }

                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                    break;
                case ConnectionResult.SERVICE_MISSING:
                    System.out.println("SERVICE IS MISSING");
                    Toast.makeText(getActivity(), "Service not found", Toast.LENGTH_LONG).show();
                    break;
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                    System.out.println("SERVICE NEEDS UPDATE");
                    Toast.makeText(getActivity(), "Service update needed", Toast.LENGTH_LONG).show();
                    break;
                default:
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Toast.makeText(getActivity(), "UNKNOWN ERROR", Toast.LENGTH_LONG).show();
        }
    }
}

