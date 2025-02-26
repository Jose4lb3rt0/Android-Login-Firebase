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
import pe.jadse.android_login_firebase.databinding.FragmentHomeBinding;
import pe.jadse.android_login_firebase.databinding.FragmentLoginBinding;

public class Home extends Fragment {

    NavController navController;
    FragmentHomeBinding binding;
    Context context;
    View view;

    FirebaseAuth fAuth;
    Button buttonCerrarSesion;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container,false );
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);

        fAuth = FirebaseAuth.getInstance();
        buttonCerrarSesion = binding.btnCerrarSesion;

        buttonCerrarSesion.setOnClickListener(view1 -> {
            fAuth.signOut();;
            navController.navigate(R.id.nav_splash);
        });
    }
}
