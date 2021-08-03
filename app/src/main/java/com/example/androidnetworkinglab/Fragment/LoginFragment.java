//package com.example.androidnetworkinglab.Fragment;
//
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatButton;
//
//import com.example.androidnetworkinglab.Datalab4.Constants;
//import com.example.androidnetworkinglab.Datalab4.RequestInterface;
//import com.example.androidnetworkinglab.Datalab4.ServerRequest;
//import com.example.androidnetworkinglab.Datalab4.ServerResponse;
//import com.example.androidnetworkinglab.Datalab4.User;
//import com.example.androidnetworkinglab.R;
//import com.google.android.material.snackbar.Snackbar;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class LoginFragment extends Fragment implements View.OnClickListener
//{
//    private AppCompatButton btn_login;
//    private EditText edt_email, edt_password;
//    private TextView tv_register,tv_notforgot;
//    private ProgressBar progressBar;
//    private SharedPreferences pref;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_login, container,
//                false);
//        initViews(view);
//        return view;
//    }
//    private void initViews(View view) {
//        pref = getActivity().getPreferences(0);
//        btn_login = (AppCompatButton) view.findViewById(R.id.btn_login);
//        edt_email = (EditText) view.findViewById(R.id.et_email);
//        edt_password = (EditText) view.findViewById(R.id.et_password);
//        tv_register = (TextView) view.findViewById(R.id.tv_register);
//        tv_notforgot = (TextView) view.findViewById(R.id.tv_notforgot);
//        progressBar = (ProgressBar) view.findViewById(R.id.progress);
//        tv_register.setOnClickListener(this);
//        tv_notforgot.setOnClickListener(this);
//        btn_login.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_login:
//                String email = edt_email.getText().toString();
//                String password = edt_password.getText().toString();
//                if(!email.isEmpty() && !password.isEmpty()){
//                    progressBar.setVisibility(View.VISIBLE);
//                    loginProcess(email,password);
//                }else {
//                    Snackbar.make(getView(),"Fields are empty !",Snackbar.LENGTH_LONG).show();
//                }
//                break;
//            case R.id.tv_register:
//                Snackbar.make(getView(),"Hello",Snackbar.LENGTH_LONG).show();
//                goToRegister();
//                break;
//            case R.id.tv_notforgot:
//                goToResetPassword();
//                break;
//        }
//    }
//    private void loginProcess(String email, String password){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RequestInterface requestInterface =
//                retrofit.create(RequestInterface.class);
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        ServerRequest request = new ServerRequest();
//        request.setOperation(Constants.LOGIN_OPERATION);
//        request.setUser(user);
//        Call<ServerResponse> response = requestInterface.operation(request);
//        response.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call,
//                                   Response<ServerResponse> response) {
//                ServerResponse resp = response.body();
//
//                Snackbar.make(getView(),resp.getMessage(),Snackbar.LENGTH_LONG).show();
//                if(resp.getResult().equals(Constants.SUCCESS)){
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
//
//                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
//                    editor.putString(Constants.NAME,
//                            resp.getUser().getName());
//
//                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
//                    editor.apply();
//                    goToProfile();
//                }
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                progressBar.setVisibility(View.INVISIBLE);
//                Log.d(Constants.TAG,"failed");
//
//                Snackbar.make(getView(),t.getMessage(),Snackbar.LENGTH_LONG).show();
//            }
//        });
//    }
//    private void goToRegister() {
//        Fragment register = new RegisterFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, register);
//        ft.commit();
//    }
//    private void goToProfile() {
//        Fragment profile = new ProfileFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, profile);
//        ft.commit();
//    }
//    private void goToResetPassword(){
//        Fragment reset = new ResetPasswordFragment();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame,reset);
//        ft.commit();
//    }
//}
