package com.cste.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends ArrayAdapter<Medicine>{

	Activity con;
	ArrayList<Medicine> medicinelist;
	public AdapterClass(Context context,
						ArrayList<Medicine> objects) {
		super(context,R.layout.list_search, objects);
		// TODO Auto-generated constructor stub
		this.con=(Activity)context;
		this.medicinelist=objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=null;
		if(convertView==null)
		{
			LayoutInflater inflator=con.getLayoutInflater();
			v=inflator.inflate(R.layout.list_search, null);
			
			TextView tvName=(TextView) v.findViewById(R.id.tvGeneric);
			TextView tvInd=(TextView) v.findViewById(R.id.tvIndication);
			TextView tvDos=(TextView) v.findViewById(R.id.tvDosage);
			TextView tvAct=(TextView) v.findViewById(R.id.tvAction);
			TextView tvCon=(TextView) v.findViewById(R.id.tvContraindication);
			TextView tvSid=(TextView) v.findViewById(R.id.tvSideEff);
			TextView tvCom= (TextView) v.findViewById(R.id.tvCompany);
			TextView tvPrice=(TextView) v.findViewById(R.id.tvSizePrice);
			
			Medicine medicine= medicinelist.get(position);
			tvName.setText(medicine.getGenName());
			tvInd.setText(medicine.getInd());
			tvDos.setText(medicine.getDos());
			tvAct.setText(medicine.getAction());
			tvCon.setText(medicine.getContra());
			tvSid.setText(medicine.getSide());
			tvCom.setText(medicine.getCompany());
			tvPrice.setText(medicine.getPrice());
		}
		else
			v=convertView;
		return v;
	}

	
}
