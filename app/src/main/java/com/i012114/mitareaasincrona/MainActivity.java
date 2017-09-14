package com.i012114.mitareaasincrona;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mProgressButtom;
    ProgressDialog mProgressDialog;
    TextView nombre;
    TextView apellido;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=(TextView) findViewById(R.id.txt_3);
        apellido=(TextView) findViewById(R.id.txt_4);
        imagen=(ImageView) findViewById(R.id.img1);
        mProgressButtom = (Button) findViewById(R.id.ProgressButtom);
        mProgressButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ProgressDialogAsyncTask().execute();

            }
        });




    }

        public class ProgressDialogAsyncTask extends AsyncTask <String, Integer, Integer> {

            @Override
            protected void onPreExecute() {
                mProgressDialog = new ProgressDialog(MainActivity.this);
                mProgressDialog.setTitle("DESCARGAR");
                mProgressDialog.setMessage("Descarga de Datos");
                mProgressDialog.setProgressStyle(mProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setProgress(0);
                mProgressDialog.setMax(100);
                mProgressDialog.show();

            super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(String... strings) {

                for (int j=1;j<=20;j++) {

                    try {
                        Thread.sleep(1000);
                        mProgressDialog.incrementProgressBy(100/20);
                    }catch (InterruptedException e){

                        e.printStackTrace();
                    }
                }
                publishProgress();
                return 20;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                mProgressDialog.dismiss();
                super.onPostExecute(integer);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                if (mProgressDialog.getProgress()<=mProgressDialog.getMax()){

                    nombre.setText("Alejandra");
                    apellido.setText("Salas");
                    imagen.setImageResource(R.drawable.img1);

                    //mProgressDialog.setProgress(values [0]);


                }
            }
        }

}
