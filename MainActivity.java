package com.example.vision.myapplication;

import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int req=1;
    Button b6,b2,b3,b4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b6= findViewById(R.id.bt1);
        b2=findViewById(R.id.bt2);
        b3= findViewById(R.id.bt3);
        b4=findViewById(R.id.bt4);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("n",2);
                startActivityForResult(i,req);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("n",3);
                startActivityForResult(i,req);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("n",4);
                startActivityForResult(i,req);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("n",5);
                startActivityForResult(i,req);
            }
        });
    }
    public void onActivityResult(int req,int res,Intent data )

    {int sum;

        if(req==this.req)
        {
            if(res==RESULT_OK)
            {int n=  data.getIntExtra("n",0);
                sum=data.getIntExtra("sum",0);
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("YOUR TOTAL SCORCE IS "+ sum);
                builder.setTitle("RESULT");
                AlertDialog ab=builder.create();
                ab.show();

                if(sum>=2)
                {Toast.makeText(getApplicationContext(),"your are eligible for next round",Toast.LENGTH_LONG).show();
                    if(n==2) {
                        b6.setEnabled(false);
                        b2.setEnabled(true);
                    }
                    if(n==3) {
                        b6.setEnabled(false);
                        b2.setEnabled(false);
                        b3.setEnabled(true);
                    }
                    if(n==4){
                        b6.setEnabled(false);
                        b2.setEnabled(false);
                        b3.setEnabled(false);
                        b4.setEnabled(true);
                    }
                }
                else{Toast.makeText(getApplicationContext(),"you lose the game",Toast.LENGTH_LONG).show();}


            }
            else
            {
                Toast.makeText(getApplicationContext(),"SomeThing went wrong Please  Reorder",Toast.LENGTH_LONG).show();
            }
        }


    }

}
class timer extends AsyncTask<Integer,Integer,Integer>
{ TextView t1;
    Context con;
    Button b1;

    timer(TextView t1, Context con,Button b1)
    {this.t1=t1;
        this.con=con;
        this.b1=b1;
    }
    @Override
    protected  Integer doInBackground(Integer...arq)
    {int count=arq[0];
        while(count>0)
        {try {
            Thread.sleep(1000);
            count--;

            publishProgress(count);
            if(isCancelled())
                break;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
        return count;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        t1.setText(values[0].toString());
        if(values[0]==0)
            b1.setEnabled(false);
    }
    @Override
    protected void onPostExecute(Integer s) {
        super.onPostExecute(s);
        Toast.makeText(con ,"Sorry,Your Time has Expired you have Quit",Toast.LENGTH_LONG).show();


    }
}


