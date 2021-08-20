package com.siamsoft.add_customer;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

class Add_Customer extends AppCompatActivity {

    //private String insertDataURL = "http://192.168.56.1/android/crudmysql/volleycrud/addProduct.php";


    private String insertDataURL = "http://10.0.2.2/Android/LMEM_01/addCustomer.php";
    //  private String insertDataURL = "http://stamasoft.com/android/stu_master_details/Registration.php";



    private static String TAG = Add_Customer.class.getSimpleName();
    private Button insertData;

    private EditText editIname, editMobile;

    // Progress dialogs
    private ProgressDialog pDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_customer);
        insertData = (Button) findViewById(R.id.btn);



        editIname = (EditText) findViewById(R.id.name);
        editMobile = (EditText) findViewById(R.id.mob);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);




        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // insertDataOnline();

                //  insertdatanewway2();
                confirmadddata();
            }
        });


    }//slb


    private void confirmadddata(){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilde  = new androidx.appcompat.app.AlertDialog.Builder (this);
        alertDialogBuilde.setMessage("Are you sure you want to Add Data?");

        alertDialogBuilde.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        insertdatanewway2();
                        //  startActivity(new Intent(Insert_new.this,ViewproductList_2.class));
                    }
                });

        alertDialogBuilde.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilde.create();
        alertDialog.show();
    }



//--------------------------

    private void showpDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }


    //------------------
    //---------insert rnd----
    public void insertdatanewway2() {

        showpDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressBar.setVisibility(View.GONE);
                editIname.setText("");
                editMobile.setText("");

                Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();

                parameters.put("c_fullname", editIname.getText().toString());
                parameters.put("c_mobile", editMobile.getText().toString());

                hidepDialog();

                return parameters;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(stringRequest,TAG);

    }





    //---------------------






}//mlb