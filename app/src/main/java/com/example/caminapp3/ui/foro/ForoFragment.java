package com.example.caminapp3.ui.foro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.caminapp3.MainActivity;
import com.example.caminapp3.R;
import com.example.caminapp3.databinding.FragmentDesccomentBinding;
import com.example.caminapp3.databinding.FragmentForoBinding;
import com.example.caminapp3.ui.BD.FuncionesDB;
import com.example.caminapp3.ui.descComent.DesccomentFragment;
import com.example.caminapp3.ui.registroUsuario.RegistroUsuario;

import java.util.ArrayList;
import java.util.List;

public class ForoFragment extends Fragment {

    private ForoViewModel foroViewModel;
    private FragmentForoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        foroViewModel =
                new ViewModelProvider(this).get(ForoViewModel.class);
        binding = FragmentForoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        leerComentarios();
    }

    public void leerComentarios(){
        ListView li = (ListView) getView().findViewById(R.id.listaComentarios);
        ArrayList<String> hola = FuncionesDB.leer(getContext(), li);
        li.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,hola));
        li.setClickable(true);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent nuevaActivity = new Intent(getContext(),RegistroUsuario.class);
                startActivity(nuevaActivity);
                Log.d("Pruebas","Click de la posición " + position);

                //((MainActivity) getActivity()).getBinding().setContentView(R.layout.fragment_foro);

                DesccomentFragment fragmento = new DesccomentFragment();
                Fragment fprueba4 = getChildFragmentManager().findFragmentById(R.id.cl_1);
                FragmentManager fm3 = getActivity().getSupportFragmentManager();
                Fragment fprueba3 = fm3.findFragmentById(R.id.cl_1);
                FragmentManager fm2 = getChildFragmentManager();
                Fragment fprueba2 = fm2.findFragmentById(R.id.cl_1);
                FragmentManager fm = getParentFragmentManager();
                Fragment fprueba = fm.findFragmentById(R.id.cl_1);
                FragmentTransaction ft = fm.beginTransaction();

                ft.replace(R.id.cl_1,fragmento);
                ft.commit();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}