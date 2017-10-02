package com.example.brilho.maisibta;

import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brilho.maisibta.dao.NoticesService;
import com.example.brilho.maisibta.modelo.Notices;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class NoticiasActivity extends Fragment {

    ListView listaNoticias;
    ArrayList<Notices> noticias = new ArrayList<Notices>();
    ArrayList<String> value_array_title = new ArrayList<String>();
    ArrayList<String> value_array_post = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);
        listaNoticias = (ListView) rootView.findViewById(R.id.noticias_lista);

        OkHttpClient okhttp = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://appibta.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).client(okhttp).build();

        NoticesService service = retrofit.create(NoticesService.class);
        final Call<ResponseBody> requestCatalog = service.listaNoticias();

        requestCatalog.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "Entrou no response");

                try{
                    ResponseBody responseBody = response.body();
                    String jsonString = responseBody.string();
                    Log.d(TAG, "Json String -----> " + jsonString);
                    JSONArray jsonArray = new JSONArray(jsonString);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject json_data = null;
                        try{
                            json_data = jsonArray.getJSONObject(i);

                            //value_array_post.add(json_data.getString("posted_at"));
                            //value_array_title.add(json_data.getString("title"));
                            Notices notice = new Notices();
                            notice.setTitle(json_data.getString("title"));
                            notice.setPosted_at(json_data.getString("posted_at"));
                            notice.setDescription(json_data.getString("description"));
                            notice.setImage(json_data.getString("image"));
                            notice.setId(json_data.getInt("id"));


                            noticias.add(notice);


                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }


                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onResume();
            }
            },2000);


        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        ListaNoticesadapter adapter = new ListaNoticesadapter(getActivity(), noticias, getContext());
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, value_array_title);
        listaNoticias.setAdapter(adapter);
    }



    public static class ListaNoticesadapter extends BaseAdapter {

        private static ArrayList<Notices> Listanoticias;
        private  LayoutInflater mInflate;
        private Context context;


        public ListaNoticesadapter(Context noticiasActivity, ArrayList<Notices> noticias, Context context){
            Listanoticias = noticias;
            this.mInflate = LayoutInflater.from(noticiasActivity);
            this.context = context;
        }

        static class ViewHolder {

            TextView txtTitulo, txtDescription;
            ImageView img;
        }

        @Override
        public int getCount() {
            return Listanoticias.size();
        }

        @Override
        public Object getItem(int position) {
            return Listanoticias.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            final Notices notices = Listanoticias.get(position);
            if(convertView == null){
                convertView = mInflate.inflate(R.layout.customlayout, parent, false);
                holder = new ViewHolder();
                holder.txtTitulo = (TextView)convertView.findViewById(R.id.txtTitle);
                holder.txtDescription = (TextView)convertView.findViewById(R.id.txtDesc);
                holder.img = (ImageView)convertView.findViewById(R.id.imgloader);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtTitulo.setText(notices.getTitle());
            holder.txtDescription.setText(notices.getDescription());
            Picasso.with(context).load(notices.getImage()).into(holder.img);

            return convertView;
        }
    }

}
