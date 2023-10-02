package com.m21290940.myfirstapplication.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.m21290940.myfirstapplication.R;
import com.m21290940.myfirstapplication.usuario.model.Usuario;
import com.m21290940.myfirstapplication.usuario.repository.UsuarioRepository;
import android.content.DialogInterface;


public class DashboardUsuario extends AppCompatActivity {
    private UsuarioRepository ur;
    private Usuario userInfo;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);
        //Aquí creamos la instancia del Usuario repository
        ur = UsuarioRepository.getInstance();
        //Esta es la manera en que recibimos información de otro activity
        String usuario = getIntent().getStringExtra("usuario");
        String pass = getIntent().getStringExtra("pass");
        //Obtenemos información del usuario logueado
        userInfo = ur.getRegisteredUsers().get(usuario).get(pass);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ShapeableImageView ivUserImage = findViewById(R.id.ivUserImage);
        if ( userInfo.getGenero() == 'h' )
            ivUserImage.setImageResource(R.drawable.hombre);
        else
            ivUserImage.setImageResource(R.drawable.mujer);


        TextView tvUserUsuario = findViewById(R.id.tvUserUsuario);
        TextView tvUserNombre = findViewById(R.id.tvUserNombre);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        TextView tvUserEdad = findViewById(R.id.tvUserEdad);

        tvUserUsuario.setText( userInfo.getUsuario() );
        tvUserNombre .setText( userInfo.getNombre() );
        tvUserEmail  .setText( userInfo.getEmail() );
        tvUserEdad   .setText( userInfo.getEdad() + " ".concat( getString(R.string.tvUserEdadComplement) ) );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tollbar_menu, menu);

        return true;
    }
    private AlertDialog createAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message);
        return builder.create();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.miBorrar ){
            //Toast.makeText(this, "Borrar", Toast.LENGTH_LONG).show();
            createConfirmationDialog("Borrar", "Desea borrar? ").show();

        } else if ( item.getItemId() == R.id.miInfo ) {
            //Toast.makeText(this, "Info", Toast.LENGTH_LONG).show();
            this.createAlertDialog("Informacion", "Kevin Martin Zamudio Reyes   20291031").show();
        } else if ( item.getItemId() == R.id.miSetting ) {
            //Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
            this.createAlertDialog("Settings", "Accediendo a configuracion").show();

        }


        return super.onOptionsItemSelected(item);
    }
    // Método para crear un diálogo de confirmación
    private AlertDialog createConfirmationDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardUsuario.this, "Selecciono SI", Toast.LENGTH_LONG).show();
                        //showToast("Respuesta", "Seleccionó Sí");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DashboardUsuario.this, "Selecciono NO", Toast.LENGTH_LONG).show();
                        //showToast("Respuesta", "Seleccionó No");
                    }
                });
        return builder.create();
    }
}