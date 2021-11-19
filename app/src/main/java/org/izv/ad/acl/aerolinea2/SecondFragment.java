package org.izv.ad.acl.aerolinea2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.izv.ad.acl.aerolinea2.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Vuelo vuelo;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //extraemos el objeto vuelo del bundle
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        vuelo = bundle.getParcelable("vuelo");
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //asignamos la funcionalidad de los botones
        binding.precioTotalInput.setText(vuelo.getTotal() + "€");
        binding.btBuyAccept.setOnClickListener((View v) -> {
            goActividadDespliegue();
        });
        binding.btPayCancel.setOnClickListener((View v)->{
            cancelBuy();
        });

    }

    //Confirmamos la compra a través de un Dialog
    private void goActividadDespliegue(){

        AlertDialog.Builder ad = new AlertDialog.Builder(this.getContext());
        ad.setTitle("Confirmar compra");
        ad.setMessage("¿Desea confirmar la compra de su billete?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmBuy();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelBuy();
            }
        })
                .setCancelable(false)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Manda el Bundle al siguiente fragmento con el vuelo
    private void confirmBuy(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("vuelo", vuelo);
        NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_thirdFragment, bundle);
    }

    //cancela el vuelo llevandonos al primer fragmento
    private void cancelBuy(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("vuelo", vuelo);
        NavHostFragment.findNavController(this).navigate(R.id.action_SecondFragment_to_FirstFragment, bundle);
    }


}