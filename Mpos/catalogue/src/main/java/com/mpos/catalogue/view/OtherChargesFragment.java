package com.mpos.catalogue.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mpos.catalogue.adapters.ChargeRecyclerViewAdapter;
import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Charges;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherChargesFragment extends Fragment implements View.OnClickListener {

    EditText chargeNameEditText;
    Button addChargeButton;
    Button cancelChargeButton;


    public OtherChargesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_other_charges, container, false);
        chargeNameEditText = (EditText) rootView.findViewById(R.id.chargeaddedittext);
        addChargeButton = (Button) rootView.findViewById(R.id.chargeaddbutton);
        cancelChargeButton = (Button) rootView.findViewById(R.id.chargecancelbutton);
        addChargeButton.setOnClickListener(this);
        cancelChargeButton.setOnClickListener(this);
        List<Charges> rowListItem = getAllChargesList();// TODO : replace with MposDatabaseUtil.getAllCategories();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);

        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.charge_recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        ChargeRecyclerViewAdapter rcAdapter = new ChargeRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    private List<Charges> getAllChargesList() {
        List<Charges> chargesList = new ArrayList<Charges>();
        chargesList.add(new Charges(001, "Shipping Charges", "150"));
        chargesList.add(new Charges(002, "Packing Charges", "50"));
        chargesList.add(new Charges(003, "Gift Pack", "120"));

        return chargesList;
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.chargeaddbutton) {

        }else if(view.getId()==R.id.chargecancelbutton){

        }
    }
}
