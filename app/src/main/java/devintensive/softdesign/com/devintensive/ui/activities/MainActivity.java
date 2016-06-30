package devintensive.softdesign.com.devintensive.ui.activities;

import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import devintensive.softdesign.com.devintensive.R;
import devintensive.softdesign.com.devintensive.data.managers.DataManager;
import devintensive.softdesign.com.devintensive.utils.ConstantManager;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG= ConstantManager.TAG_PREFIX + "Main Activity";

    private boolean mCurrentEditMode = false;
    private DataManager mDataManager;

    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mNavigationDrawer;
    private FloatingActionButton mFab;
    private EditText mUserPhone, nUserMail, mUserVK, mUserGit, mUserAbout;
    private List<EditText> mUserInfoViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataManager = DataManager.gatInstance();

        mCallImg = (ImageView)findViewById(R.id.call_img);
        mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_coordinator_container);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mNavigationDrawer = (DrawerLayout)findViewById(R.id.navigation_drawer);
        mFab = (FloatingActionButton)findViewById(R.id.fab);
        mUserPhone = (EditText)findViewById(R.id.phone_et);
        nUserMail = (EditText)findViewById(R.id.mail_et);
        mUserVK = (EditText)findViewById(R.id.vk_et);
        mUserGit = (EditText)findViewById(R.id.git_et);
        mUserAbout = (EditText)findViewById(R.id.about_et);

        mUserInfoViews = new ArrayList<>();
        mUserInfoViews.add(mUserPhone);
        mUserInfoViews.add(nUserMail);
        mUserInfoViews.add(mUserVK);
        mUserInfoViews.add(mUserGit);
        mUserInfoViews.add(mUserAbout);


        mCallImg.setOnClickListener(this);
        mFab.setOnClickListener(this);
        setupToolbar();
        setupDrawer();
        loadUserInfoValue();

        if(savedInstanceState == null){
            //нет сохранений
        } else {
            mCurrentEditMode = savedInstanceState.getBoolean(ConstantManager.EDIT_MODE_KEY, false);
            changeEditMode(mCurrentEditMode);
        }

        Log.d(TAG, "onCreate");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_img:
                // showProgress();
                break;
            case R.id.fab:
                mCurrentEditMode = !mCurrentEditMode;
                changeEditMode(mCurrentEditMode);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(ConstantManager.EDIT_MODE_KEY, mCurrentEditMode);
    }

    private void showSnackBar(String message){
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawer(){
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mNavigationDrawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    /**
     * вкл/выкл режим редактирования
     * @param mode
     */
    private void changeEditMode(boolean mode){
        for (EditText uValue: mUserInfoViews){
            uValue.setEnabled(mode);
            uValue.setFocusable(mode);
            uValue.setFocusableInTouchMode(mode);
        }

        if(mCurrentEditMode){
            mFab.setImageResource(R.drawable.ic_done_black_24dp);
        } else {
            mFab.setImageResource(R.drawable.ic_create_black_24dp);
            saveUserInfoValue();
        }
    }

    private void loadUserInfoValue(){
        List<String> userData = mDataManager.getPreferencesManager().loadUserProfileData();
        for (int i = 0; i < userData.size(); i++){
            mUserInfoViews.get(i).setText(userData.get(i));
        }
    }

    private void saveUserInfoValue(){
        List<String> userData = new ArrayList<>();
        for (EditText userField : mUserInfoViews) {
            userData.add(userField.getText().toString());
        }

        mDataManager.getPreferencesManager().saveUserProfileData(userData);
    }
}
