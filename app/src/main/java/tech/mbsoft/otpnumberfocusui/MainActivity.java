package tech.mbsoft.otpnumberfocusui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnKeyListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 123;

    EditText et_otp_1, et_otp_2, et_otp_3, et_otp_4;
    Button btnPhoneAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        btnPhoneAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.PhoneBuilder().build()
                                        ))
                                .build(),
                        RC_SIGN_IN);
            }
        });

    }

    private void initUI() {
        et_otp_1 = findViewById(R.id.et_otp_1);
        et_otp_2 = findViewById(R.id.et_otp_2);
        et_otp_3 = findViewById(R.id.et_otp_3);
        et_otp_4 = findViewById(R.id.et_otp_4);

        btnPhoneAuth = findViewById(R.id.btnPhoneAuth);

        et_otp_1.addTextChangedListener(this);
        et_otp_2.addTextChangedListener(this);
        et_otp_3.addTextChangedListener(this);
        et_otp_4.addTextChangedListener(this);

        et_otp_1.setOnKeyListener(this);
        et_otp_2.setOnKeyListener(this);
        et_otp_3.setOnKeyListener(this);
        et_otp_4.setOnKeyListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() == 0)
            return;
        if (editable == et_otp_1.getEditableText() && is4EditTextFull()) {
            et_otp_2.requestFocus();
        } else if (editable == et_otp_2.getEditableText() && is4EditTextFull()) {
            et_otp_3.requestFocus();
        } else if (editable == et_otp_3.getEditableText() && is4EditTextFull()) {
            et_otp_4.requestFocus();
        } else if (editable == et_otp_4.getEditableText() && is4EditTextFull()) {
            et_otp_1.requestFocus();
        }
    }

    private boolean is4EditTextFull() {
        return !(isEditTextContainOtp(et_otp_1) && isEditTextContainOtp(et_otp_2) && isEditTextContainOtp(et_otp_3) && isEditTextContainOtp(et_otp_4));
    }

    private boolean isEditTextContainOtp(EditText editText) {
        return editText.getText().length() == 1;
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        int length = -1;
        if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
            return true;
        switch (view.getId()) {
            case R.id.et_otp_1:
                length = et_otp_1.getText().toString().length();
                if (keyCode == KeyEvent.KEYCODE_DEL && length == 0 && et_otp_4.getText().toString().length() == 1) {
                    et_otp_4.requestFocus();
                }
                break;
            case R.id.et_otp_2:
                length = et_otp_2.getText().toString().length();
                if (keyCode == KeyEvent.KEYCODE_DEL && length == 0) {
                    et_otp_1.requestFocus();
                }
                break;
            case R.id.et_otp_3:
                length = et_otp_3.getText().toString().length();
                if (keyCode == KeyEvent.KEYCODE_DEL && length == 0) {
                    et_otp_2.requestFocus();
                }
                break;
            case R.id.et_otp_4:
                length = et_otp_4.getText().toString().length();
                if (keyCode == KeyEvent.KEYCODE_DEL && length == 0) {
                    et_otp_3.requestFocus();
                }
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"code->"+requestCode);
    }
}
