package org.izv.ad.acl.aerolinea2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.izv.ad.acl.aerolinea2.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {



    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //controladores de los botones
        binding.btBuyNormal.setOnClickListener((View v) -> {
            procederCompra(view, false);
        });

        binding.btBuyPremium.setOnClickListener((View v) -> {
            procederCompra(view, true);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Valido los campos comprbandolos 1 a 1
    private boolean validarCampos(){
        String errorMsg = "";
        boolean res = true;

        if (binding.inputFecha.getText().toString().isEmpty() ||
            binding.inputName.getText().toString().isEmpty() ||
            binding.inputSurname.getText().toString().isEmpty() ||
            binding.inputAddress.getText().toString().isEmpty() ||
            binding.inputPhone.getText().toString().isEmpty() ||
            binding.inputEmail.getText().toString().isEmpty()) {

            res = false;
            errorMsg = "Te falta algún campo por rellenar";
        }
        if (!binding.swTerms.isChecked()){
            errorMsg = "No has marcado los terminos y condiciones";
            res = false;
        }
        if (!res){
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Error");
            builder.setMessage(errorMsg);
            AlertDialog ad = builder.create();
            ad.show();
        }

        return res;
    }

    //Extraigo todos los valores de los campos, los almaceno en un objeto de tipo
    //vuelo y lo mando en parcelable al siguiente fragmento
    private void procederCompra(View v, boolean premium){
        if (validarCampos()) {
            String nombre = binding.spnPrefix.getSelectedItem().toString() + " " + binding.inputName.getText().toString();
            String apellido = binding.inputSurname.getText().toString();
            String direccion = binding.inputAddress.getText().toString();
            String email = binding.inputEmail.getText().toString();
            String numTelf = binding.inputPhone.getText().toString();
            String fecha = binding.inputFecha.getText().toString();
            String ciudadIda = binding.spinnerOrigen.getSelectedItem().toString();
            String ciudadVuelta = binding.spinnerDestino.getSelectedItem().toString();
            int[] extrasIds = binding.groupExtras.getReferencedIds();
            String[] extras = new String[extrasIds.length];
            for (int i = 0; i < extrasIds.length; i++) {
                CheckBox cb = v.findViewById(extrasIds[i]);
                if (cb.isChecked()) {
                    String str = cb.getText().toString();
                    extras[i] = str;
                }
            }

            boolean diversidadFuncional = binding.swMovRed.isChecked();
            boolean seguro = binding.rbYesEns.isChecked();

            Vuelo vuelo = new Vuelo(nombre, apellido, direccion, email, fecha, numTelf, ciudadIda, ciudadVuelta, extras, diversidadFuncional, seguro, premium);
            Bundle bundle = new Bundle();
            bundle.putParcelable("vuelo", vuelo);
            Toast.makeText(this.getContext(), "Calculando precio de compra", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            //goActividadCompra(vuelo);
        }
    }



}