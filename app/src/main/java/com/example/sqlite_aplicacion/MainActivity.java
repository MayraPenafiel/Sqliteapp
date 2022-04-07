package com.example.sqlite_aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText name, contact, lastname, correo;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.txtnombre);
        lastname=findViewById(R.id.txtapellido);
        contact = findViewById(R.id.txttelefono);
        correo =findViewById(R.id.txtcorreo);
        insert = findViewById(R.id.btnGuardar);
        update = findViewById(R.id.btnEditar);
        delete = findViewById(R.id.btnEliminar);
        view = findViewById(R.id.btnListar);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String contactTXT = contact.getText().toString();
                String cor= correo.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(nameTXT,lastnameTXT, contactTXT,cor);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Nuevos Datos Ingresados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no Ingresados", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String contactTXT = contact.getText().toString();
                String cor= correo.getText().toString();
                Boolean checkupdatedata = DB.updateuserdata(nameTXT,lastnameTXT, contactTXT,cor);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no Actualizados", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Datos Eliminados", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Datos no eliminados", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No existen datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nombre :"+res.getString(0)+"\n");
                    buffer.append("Apellido :"+res.getString(1)+"\n");
                    buffer.append("Contacto :"+res.getString(2)+"\n");
                    buffer.append("correo: "+res.getString(3)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Informacion De Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}