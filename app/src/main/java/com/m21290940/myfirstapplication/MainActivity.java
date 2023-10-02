package com.m21290940.myfirstapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.m21290940.myfirstapplication.usuario.DashboardUsuario;
import com.m21290940.myfirstapplication.usuario.repository.UsuarioRepository;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private UsuarioRepository ur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ur = UsuarioRepository.getInstance();

    }

    private AlertDialog createAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message);
        return builder.create();
    }
    public void onBtnIngresarTap(View view) {
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //AlertDialog dialog = builder.create();

        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPass    = findViewById(R.id.etPass);

        String user, pass;
        user = etUsuario.getText().toString();
        pass = etPass.getText().toString();

        if ( !ur.getRegisteredUsers().containsKey(user) ) {
            //dialog.setTitle("Atencíon");
            //dialog.setMessage("Usuario no registrado");
            //dialog.show();
            this.createAlertDialog("Atención", "Usuario no registrado").show();
            return;
        }

        if ( !ur.getRegisteredUsers().get(user).containsKey(pass) ){
            //dialog.setTitle("Atención");
            //dialog.setMessage("La constraseña no coincide");
            //dialog.show();
            this.createAlertDialog("Atención", "La contraseña no coincide").show();
            return;
        }
        //user = user.toUpperCase();
        //dialog.setTitle("Felicidades has iniciado sesión");
        //dialog.setMessage(String.format("Datos ingresado\nUsuario: %s\nPassword: %s", user, pass));
        //dialog.show();
        String message = "Felicidades ha iniciado sesión con éxito";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DashboardUsuario.class);
        i.putExtra("usuario", user);
        i.putExtra("pass", pass);
        startActivity(i);
    }
}