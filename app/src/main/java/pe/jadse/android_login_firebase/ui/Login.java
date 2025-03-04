package pe.jadse.android_login_firebase.ui;

import android.content.Context;
import android.os.Bundle;
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

import pe.jadse.android_login_firebase.R;
import pe.jadse.android_login_firebase.databinding.FragmentLoginBinding;

public class Login extends Fragment {

    NavController navController;
    FragmentLoginBinding binding;
    Context context;
    View view;

    FirebaseAuth fAuth;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin, buttonRegister;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container,false );
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        fAuth = FirebaseAuth.getInstance();

        editTextEmail = binding.edtEmail;
        editTextPassword = binding.edtPassword;
        buttonLogin = binding.btnLogin;
        buttonRegister = binding.btnRegister;

        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor, ingresa tu email y contraseña.", Toast.LENGTH_SHORT).show();
                return;
            }

            fAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_nav_login_to_nav_home);
                        } else {
                            Toast.makeText(context, "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        buttonRegister.setOnClickListener(v -> {
            navController.navigate(R.id.action_nav_login_to_register);
        });
    }
}
