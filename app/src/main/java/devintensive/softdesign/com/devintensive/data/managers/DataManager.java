package devintensive.softdesign.com.devintensive.data.managers;

/**
 * Created by Алишка on 30.06.2016.
 */
public class DataManager {
    private static DataManager INSTANCE = null;

    private PreferencesManager mPreferencesManager;

    private DataManager(){
        this.mPreferencesManager = new PreferencesManager();
    }

    public static DataManager gatInstance(){
        if (INSTANCE == null){
            INSTANCE = new DataManager();
        }

        return INSTANCE;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }
}
