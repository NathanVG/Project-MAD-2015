package be.howest.nmct.projectmad;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import be.howest.nmct.projectmad.data.KSALocaties;


public class MainActivity extends Activity implements KsaFragment.OnKsaFragmentListener, KsaDetailFragment.onKSADetailsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new KsaFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    public void demandKsaDetail(String sNaamKSA) {
        showKSADetailFragment(sNaamKSA);
    }

    private void showKSADetailFragment (String sNaamKSA) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KsaDetailFragment fragment = KsaDetailFragment.newInstance(sNaamKSA);
        fragmentTransaction.replace(R.id.container, fragment, "KsaDetailFragment");
        fragmentTransaction.addToBackStack("show_new_detail");
        fragmentTransaction.commit();
    }

    @Override
    public void onShowKSA (String naam) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KSAKaartFragment ShopMapFragment = be.howest.nmct.projectmad.KSAKaartFragment.newKSAKaartFragment(naam);
        fragmentTransaction.replace(R.id.container, ShopMapFragment);
        fragmentTransaction.addToBackStack("ShopDetailFragment");
        fragmentTransaction.commit();
    }
}
