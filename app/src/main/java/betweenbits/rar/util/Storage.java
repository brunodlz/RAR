package betweenbits.rar.util;

import android.os.Environment;
import android.text.TextUtils;

/**
 * Created by brunov0id on 25/07/15.
 */
public class Storage {
    private static String sdcardPath = "";
    public static final String STORAGE_EMULATED = "storage/emulated/";
    public static final String STORAGE_SDCARD = "storage/sdcard";

    public static String getPath() {
        if (TextUtils.isEmpty(sdcardPath)) {
            sdcardPath = Environment.getExternalStorageDirectory().getPath();
        }
        if (sdcardPath.contains(STORAGE_EMULATED)) {
            sdcardPath = sdcardPath.replace(STORAGE_EMULATED, STORAGE_SDCARD);
        }
        return sdcardPath;
    }
}
