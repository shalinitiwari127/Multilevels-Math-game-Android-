package com.example.vision.myapplication;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Main2Activity extends AppCompatActivity {    int butcount = 1;
    int a1, b;
    int sum = 0;
    String s;
    private timer time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int count = 50;
        final TextView t3;
        final TextView t1;
        final EditText edt1;
        t3 = findViewById(R.id.t3);
        t1 = findViewById(R.id.t2);
        edt1 = findViewById(R.id.t1);
        final Button b1 = findViewById(R.id.button);
        time = new timer(t3, this, b1);
        final Button quit= findViewById(R.id.button2);
        time.execute(count);
        Intent i=getIntent();
        final int n=i.getIntExtra("n",0);
        final int arr[];
        arr=new int[n];
        final String arr1[];
        arr1=new String[n-1];
        final ArrayList<String> a = new ArrayList<>();
        a.add("+");
        a.add("/");
        a.add("-");
        a.add("X");
        sum=0;
        butcount=1;
        Collections.shuffle(a);
        Random r1 = new Random();
        for(int j=0;j<n;j++){
            arr[j]=r1.nextInt(9); }
        for(int j=0;j<n-1;j++){
            arr1[j]=a.get(j);	}
        s=makestring(arr,arr1);
        t1.setText(s);
        b1.setOnClickListener(new View.OnClickListener() {		@Override
        public void onClick(View view) {
            Double c = calculate(s);
            Double c1 = Double.parseDouble(edt1.getText().toString());
            butcount++;
            if (c.compareTo(c1) == 0) {
                sum = sum + 1;	}
            if (butcount == 5) {
                b1.setText("Submit");	}
            if (butcount == 6) {
                time.cancel(true);
                Intent i2 = new Intent();
                i2.putExtra("sum",sum);
                i2.putExtra("n",n);
                setResult(RESULT_OK,i2);
                finish();		}
            Collections.shuffle(a);
            Random r1 = new Random();
            for(int j=0;j<n;j++){
                arr[j]=r1.nextInt(9);	}
            for(int j=0;j<n-1;j++){
                arr1[j]=a.get(j);	}
            s=makestring(arr,arr1);
            edt1.setText("");
            t1.setText(s);
        }	  });
        quit.setOnClickListener(new View.OnClickListener() {	@Override
        public void onClick(View view) {		time.cancel(true);
            Intent i2 = new Intent();
            i2.putExtra("n",n);
            i2.putExtra("sum",sum);
            setResult(RESULT_OK,i2);
            finish();		}	});
    }
    static double calculate(String equation) {	double result = 0.0;
        String noMinus = equation.replace("-", "+-");
        String[] byPluses = noMinus.split("\\+");
        for (String multipl : byPluses) {
            String[] byMultipl = multipl.split("X");
            double multiplResult = 1.0;
            for (String operand : byMultipl) {
                if (operand.contains("/")) {
                    String[] division = operand.split("\\/");
                    double divident = Double.parseDouble(division[0]);
                    for (int i = 1; i < division.length; i++) {
                        divident /= Double.parseDouble(division[i]);	}
                    multiplResult *= divident;	}
                else {	multiplResult *= Double.parseDouble(operand);		}
            }		result += multiplResult;	  }
        return result;	  }
    String makestring(int arr[],String arr1[]) {
        String s1="";
        for(int i=0;i<arr.length;i++){
            if(i<arr1.length){
                s1=s1.concat(String.valueOf(arr[i]).concat(arr1[i]));}
            else{                s1=s1.concat(String.valueOf(arr[i]));}
        }  return  s1;
    }	}


