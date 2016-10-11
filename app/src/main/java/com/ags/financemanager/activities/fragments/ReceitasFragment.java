package com.ags.financemanager.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ags.financemanager.controller.ReceitaController;
import com.ags.financemanager.controller.ReceitaControllerImpl;
import com.ags.financemanager.controller.TipoReceitaController;
import com.ags.financemanager.controller.TipoReceitaControllerImpl;
import com.ags.financemanager.model.bean.Receita;
import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.bean.Usuario;
import com.ags.financemanager.model.dao.ReceitaDAO;
import com.ags.financemanager.model.dao.ReceitaDAOImpl;
import com.ags.financemanager.model.dao.TipoReceitaDAOImpl;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import com.ags.financemanager.R;

import java.util.Date;
import java.util.List;

import static android.R.layout.simple_dropdown_item_1line;


public class ReceitasFragment extends Fragment {
    private EditText etDescricaRec;
    private EditText etValorRec;
    private EditText etDataRec;
    private MaterialBetterSpinner spTipoRec;
    private Button btnSalvarRec;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReceitasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceitasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceitasFragment newInstance(String param1, String param2) {
        ReceitasFragment fragment = new ReceitasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TipoReceitaController rc = new TipoReceitaControllerImpl(new TipoReceitaDAOImpl(getActivity()));

        List<TipoReceita> tipos = rc.getTodos();
        String [] SPINNERLIST = new String[tipos.size()];

        for(int i = 0; i<tipos.size(); i++){
            SPINNERLIST[i] = tipos.get(i).getDescricao();
        }

        View root = inflater.inflate(R.layout.fragment_receitas, container, false);
        etDescricaRec = (EditText) root.findViewById(R.id.add_desc_receita);
        etValorRec = (EditText) root.findViewById(R.id.add_rec_valor);
        etDataRec = (EditText) root.findViewById(R.id.add_rec_data);
        spTipoRec = (MaterialBetterSpinner) root.findViewById(R.id.spinner_tipo_rec);
        btnSalvarRec = (Button) root.findViewById(R.id.btn_salvar_receita);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spTipoRec.setAdapter(arrayAdapter);

        btnSalvarRec.setOnClickListener(new BtSalvarListener());

        return root;
    }

    public void resetarCampos(){
        etDescricaRec.setText("");
        etDataRec.setText("");
        etValorRec.setText("");
        spTipoRec.setText("");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class BtSalvarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String desc = etDescricaRec.getText().toString();
            float valor = Float.parseFloat(etValorRec.getText().toString());
            Date date = new Date(etDataRec.getText().toString());
            long idTipoReceita = 1;

            TipoReceita tipo = new TipoReceita(idTipoReceita, spTipoRec.getText().toString());
            Usuario usuario = new Usuario("maikon", "maikonigor@gmail.com", "123");

            Receita receita = new Receita(desc, (int) date.getTime(),valor, tipo,usuario);

            ReceitaDAO rdao = new ReceitaDAOImpl(getActivity());
            ReceitaController rc = new ReceitaControllerImpl(rdao);
            rc.salvar(receita);
            resetarCampos();
            Toast.makeText(getActivity(), "Receita salva com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }
}
