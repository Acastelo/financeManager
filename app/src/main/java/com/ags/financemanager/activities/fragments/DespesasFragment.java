package com.ags.financemanager.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import com.ags.financemanager.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DespesasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DespesasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DespesasFragment extends Fragment {

    private EditText etDescricao;
    private EditText etValor;
    private EditText etData;
    private MaterialBetterSpinner spTipoDespesa;

    private OnFragmentInteractionListener mListener;

    public DespesasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DespesasFragment newInstance(String param1, String param2) {
        DespesasFragment fragment = new DespesasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_despesas, container, false);
        etDescricao = (EditText) root.findViewById(R.id.add_desc_despesas);
        etValor = (EditText) root.findViewById(R.id.add_valor);
        etData = (EditText) root.findViewById(R.id.add_data);
        spTipoDespesa = (MaterialBetterSpinner) root.findViewById(R.id.spinner_tipo);
        return root;
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
}
