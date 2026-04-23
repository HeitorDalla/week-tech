package com.heitor.week_tech;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

/**
 * Activity responsável pela tela de Login Administrativo (RF07).
 * Segue as diretrizes de UI/UX intuitiva (RNF03).
 */
public class LoginActivity extends AppCompatActivity {

    // Declaração dos componentes da interface
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML que será exibido (activity_login.xml)
        setContentView(R.layout.activity_login);

        // Inicialização dos componentes buscando-os pelo ID definido no XML
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Configuração do evento de clique no botão de login
        btnLogin.setOnClickListener(v -> {
            // Captura os textos digitados pelo usuário
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            // Validação simples de campos obrigatórios
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Implementar a lógica de autenticação segura e armazenamento (RNF07)
                // Exibe uma mensagem de sucesso temporária
                Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}