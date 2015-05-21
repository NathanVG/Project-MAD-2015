package be.howest.nmct.projectmad.loader;

import android.provider.BaseColumns;

/**
 * Created by Nathan on 21/05/15.
 */
public final class KSAInfo {

    public interface KSAColumns extends BaseColumns{
        public static final String COLUMN_KSA_NAAM = "KSA_naam";
        public static final String COLUMN_KSA_ADRES = "KSA_adres";
    }
}
