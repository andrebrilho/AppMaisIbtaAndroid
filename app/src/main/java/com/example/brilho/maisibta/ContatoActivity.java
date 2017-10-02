package com.example.brilho.maisibta;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brilho.maisibta.dao.NoticesService;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContatoActivity extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contato, container, false);

        final TextView txtEmail = (TextView)rootView.findViewById(R.id.contato_email);
        final TextView txtMensagem = (TextView)rootView.findViewById(R.id.contato_nome);
        final TextView txtPhone = (TextView)rootView.findViewById(R.id.contato_telefone);
        final TextView txtAssunto = (TextView)rootView.findViewById(R.id.contato_assunto);

        Button btnEnviar = (Button)rootView.findViewById(R.id.contato_formulario_btnSalvar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                OkHttpClient okhttp = new OkHttpClient.Builder().build();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://appibta.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).client(okhttp).build();
                NoticesService service = retrofit.create(NoticesService.class);

                final Call<ResponseBody> requestCtalog = service.enviarForm(txtEmail.getText().toString(),txtMensagem.getText().toString(),txtAssunto.getText().toString(),txtPhone.getText().toString());

                requestCtalog.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()){
                            Toast.makeText(getActivity(), "Enviado com sucesso em breve entraremos em contato", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            Toast.makeText(getActivity(), "Enviado com sucesso em breve entraremos em contato", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "Enviado com sucesso em breve entraremos em contato", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });



        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
    }
}
