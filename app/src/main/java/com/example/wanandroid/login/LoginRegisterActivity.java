package com.example.wanandroid.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.login.LoginFragment;



/*
 * created by taofu on 2019-06-11
 **/
public class LoginRegisterActivity extends BaseActivity {


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_login_register);


    addFragment(getSupportFragmentManager(), LoginFragment.class, R.id.login_fragment_container, null);
  }
}
