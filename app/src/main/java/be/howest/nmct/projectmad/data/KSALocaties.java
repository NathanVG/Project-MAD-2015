package be.howest.nmct.projectmad.data;

import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.projectmad.admin.KSA;

/**
 * Created by Nathan on 21/05/15.
 */
public final class KSALocaties {

    private static List<KSA> ksa;

    static{
        ksa = new ArrayList<KSA>();
        KSA k1 = new KSA("KSA Rumbeke", "Kerkplein 50", "8800 Rumbeke");
        KSA k2 = new KSA("KSA Roeselare", "Zuidstraat 27", "8800 Roeselare");
        KSA k3 = new KSA("KSA Beveren", "Izegemseaardeweg 1", "8800 Roeselare");
        KSA k4 = new KSA("KSA Izegem", "Schoolstraat 6", "8870 Izegem");
        KSA k5 = new KSA("KSA Oekene", "Sint-Eloois-Winkelsestraat 74", "8800 Roeselare");
        KSA k6 = new KSA("KSA Moorslede", "Iepersestraat 52", "8890 Moorslede");
        KSA k7 = new KSA("KSA Pittem", "Koolskampstraat 1", "8740 Pittem");
        KSA k8 = new KSA("KSA KSA Sint-Elooi", "Sint-Eloois-Winkel", "8880 Ledegem");
        KSA k9 = new KSA("KSA Torhout", "Bruggestraat 25", "8820 Torhout");
        KSA k10 = new KSA("KSA Tielt", "Ieperstraat 80", "8700 Tielt");

        ksa.add(k1);
        ksa.add(k2);
        ksa.add(k3);
        ksa.add(k4);
        ksa.add(k5);
        ksa.add(k6);
        ksa.add(k7);
        ksa.add(k8);
        ksa.add(k9);
        ksa.add(k10);
    }

    public static List<KSA> getKsas(){return ksa;}
    public static KSA getKSA(String sNaamKSA){
        for(KSA ksa : getKsas()){
            if(ksa.getNaam().equals(sNaamKSA))
                return ksa;
        }

        return null;
    }
}
