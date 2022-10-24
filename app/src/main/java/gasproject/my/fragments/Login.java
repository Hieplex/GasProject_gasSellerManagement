package gasproject.my.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gasproject.my.MainActivity;
import gasproject.my.R;

public class Login extends AppCompatActivity {
    private static Login instance;
    private EditText account ,password;
    private Button login,button;
    private FirebaseAuth authen;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
            account = findViewById(R.id.edtTextAccount);
            password = findViewById(R.id.edtTextPassword);
            login = findViewById(R.id.loginbtn);


      authen = FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                        String acccountmail = account.getText().toString().trim();
                        String passwordmail = password.getText().toString().trim();
//                        progressbar.setVisibility(getVisibility());
                    if(acccountmail.isEmpty() && passwordmail.isEmpty())
                    {
                        Toast.makeText(Login.this,"Vui lòng nhập tài khoản và mật khẩu",Toast.LENGTH_LONG).show();
                        return ;
                    }if(acccountmail.isEmpty())
                    {
                        Toast.makeText(Login.this,"Tài khoản trống , vui lòng nhập tài khoản",Toast.LENGTH_LONG).show();
                        return ;

                    }if(passwordmail.isEmpty())
                    {
                        Toast.makeText(Login.this,"Mật khẩu trống , vui lòng nhập mật khẩu",Toast.LENGTH_LONG).show();
                        return ;
                    }if(passwordmail.length()< 6)
                    {
                        Toast.makeText(Login.this,"Mật khẩu phải từ 6 chữ số trở lên",Toast.LENGTH_LONG).show();
                        return ;
                    }

                        authen.signInWithEmailAndPassword(acccountmail,passwordmail).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Login.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                }
                                else{
                                    Toast.makeText(Login.this,"Tài khoản hoặc mật khẩu sai vui lòng nhập lại",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            });


    }

}