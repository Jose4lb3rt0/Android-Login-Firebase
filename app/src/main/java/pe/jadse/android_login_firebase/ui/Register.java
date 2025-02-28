package pe.jadse.android_login_firebase.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.jadse.android_login_firebase.R;
import pe.jadse.android_login_firebase.databinding.FragmentRegisterBinding;

public class Register extends Fragment {
    FragmentRegisterBinding binding;
    Context context;
    View view;
    NavController navController;

    FirebaseAuth fAuth;
    Button buttonRegister, buttonLogin;
    EditText editTextEmail, editTextPassword;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        fAuth = FirebaseAuth.getInstance();

        buttonLogin = binding.btnLogin;
        buttonRegister = binding.btnRegister;

        buttonLogin.setPaintFlags(buttonLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        editTextEmail = binding.edtEmail;
        editTextPassword = binding.edtPassword;

        buttonLogin.setOnClickListener(v -> {
            navController.navigate(R.id.nav_login);
        });

        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor, ingresa tus credenciales",Toast.LENGTH_SHORT).show();
                return;
            }

            fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Usuario registrado exitosamente.", Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.nav_login);
                        } else {
                            Toast.makeText(context, "Usuario registrado exitosamente.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}

