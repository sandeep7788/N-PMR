package com.codunite.myrecharge.Custom_;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codunite.myrecharge.Helper.Constances;
import com.codunite.myrecharge.Helper.Local_data;
import com.codunite.myrecharge.Adapter.AllBankAdapter;
import com.codunite.myrecharge.Adapter.GetreferaldetailsAdapter;
import com.codunite.myrecharge.Helper.Constances;
import com.codunite.myrecharge.Helper.Local_data;
import com.codunite.myrecharge.Models.BankListModel;
import com.codunite.myrecharge.Models.GetMemberBankAccountModel;
import com.codunite.myrecharge.Models.GetreferaldetailsModel;
import com.codunite.myrecharge.R;

import java.util.ArrayList;

public class CustomDialogGetreferaldetails extends Dialog implements AdapterView.OnItemClickListener {

    public Activity c;
    public ImageView dialog_cancel_btn;
    public ArrayList<GetreferaldetailsModel> TitleName = new ArrayList<>();
    int textlength=0;
    public Context context;
    TextView textView;
    ListView alertdialog_Listview;
    Boolean click_status=false;
    OnMyDialogResult mDialogResult;
    String BankID=" ";

    public CustomDialogGetreferaldetails(Context context, final ArrayList<GetreferaldetailsModel> TitleName){
        super(context,R.style.FullWidth_Dialog);
        this.context=context;
        this.TitleName=TitleName;
    }

    public CustomDialogGetreferaldetails(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(WindowManager.LayoutParams.FILL_PARENT);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_layout1);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setAttributes(layoutParams);

        dialog_cancel_btn = findViewById(R.id.back);
        alertdialog_Listview = findViewById(R.id.alertdialog_Listview);
        dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setview();
    }

    public void setview() {
        GetreferaldetailsAdapter arrayAdapter=new GetreferaldetailsAdapter((Activity) context, TitleName);
        alertdialog_Listview.setAdapter(arrayAdapter);
        alertdialog_Listview.setOnItemClickListener(this);

    }
    private class OKListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if( mDialogResult != null ){
                mDialogResult.finish(String.valueOf(BankID));
            }
            dismiss();
        }
    }

    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(click_status) {
            Local_data pref = new Local_data(context);
            pref.writeStringPreference(Constances.PREF_operator_code,TitleName.get(i).getMemberName().toString());
            textView.setText(" "+TitleName.get(i).getMemberName().toString());
            textView.setTextColor(Color.BLACK);
            BankID= String.valueOf(TitleName.get(i).getMemberID());
            mDialogResult.finish(String.valueOf(BankID));
        } else {
            Local_data pref = new Local_data(context);
            pref.writeStringPreference(Constances.PREF_operator_code,TitleName.get(i).getMemberName().toString());
            textView.setText(" "+TitleName.get(i).getMemberName().toString());
            textView.setTextColor(Color.BLACK);
            Log.e(">>itemclick"," "+TitleName.get(i).getMemberName().toString());
            BankID= String.valueOf(TitleName.get(i).getMemberID());
            mDialogResult.finish(String.valueOf(BankID));
        }
        dismiss();
    }
}