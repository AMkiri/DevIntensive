package devintensive.softdesign.com.devintensive.data.managers;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import devintensive.softdesign.com.devintensive.utils.ConstantManager;
import devintensive.softdesign.com.devintensive.utils.DevintensiveApp;

/**
 * Created by Алишка on 30.06.2016.
 */
public class PreferencesManager {
    private SharedPreferences mSharedPreferences;

    private static final String[] USER_FIELDS = {ConstantManager.USER_PHONE_KEY,
                                                 ConstantManager.USER_MAIL_KEY,
                                                 ConstantManager.USER_VK_KEY,
                                                 ConstantManager.USER_GIT_KEY,
                                                 ConstantManager.USER_ABOUT_KEY};

    public PreferencesManager(){
        mSharedPreferences = DevintensiveApp.getSharedPreferences();
    }

    public void saveUserProfileData(List<String> userFields){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (int i = 0; i < USER_FIELDS.length; i++) {
            editor.putString(USER_FIELDS[i],userFields.get(i));
        }
        editor.apply();
    }

    public List<String> loadUserProfileData(){
        List<String> userFields = new ArrayList<>();
        for (int i = 0; i < USER_FIELDS.length; i++){
            userFields.add(mSharedPreferences.getString(USER_FIELDS[i], "null"));
        }
        return userFields;
    }
}
