package org.izv.ad.acl.aerolinea2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.izv.ad.acl.aerolinea2.databinding.FragmentFirstBinding;
import org.izv.ad.acl.aerolinea2.databinding.FragmentSecondBinding;
import org.izv.ad.acl.aerolinea2.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {
    private FragmentThirdBinding binding;
    private Vuelo vuelo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //capturamos el vuelo en el bundle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        vuelo = bundle.getParcelable("vuelo");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvDesp.setText(vuelo.getDespliegue());  //Extraemos la cadena de despliegue y la mostramos en un Edit Text

        binding.btTerminar.setOnClickListener((View v)->{
            NavHostFragment.findNavController(this).navigate(R.id.action_thirdFragment_to_FirstFragment);
        });
    }
}

