package com.example.kimja.a11th_homework_multithread;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    EditText text2;
    TextView text1;
    int imageno = 0;
    mytasks task1;
    int second = 0;
    boolean click = false;
    final int[] imagelist = {R.drawable.hamburger,R.drawable.noodle
            ,R.drawable.pizzaslice,R.drawable.rice};
    String[] imagename = {"햄버거", "국수", "피자", "밥"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton)findViewById(R.id.start);
        text2 = (EditText)findViewById(R.id.editText);
        text1 = (TextView)findViewById(R.id.text1);
        imageButton.setTag(R.drawable.playcircle);
    }

    public void onClick(View v){
        if(v.getId() == R.id.start){
            if(R.drawable.playcircle==(Integer)imageButton.getTag()){
                task1 = new mytasks();
                task1.execute(0);
            }
            else {
                click = true;
            }
        }
        else if(v.getId() == R.id.btnreset){
            if(task1 != null ){
                task1.cancel(true);
                task1 = null;
                imageno=0;
                imageButton.setImageResource(R.drawable.playcircle);
                imageButton.setTag(R.drawable.playcircle);
                text1.setText("");
                text2.setText("");
                click = false;
                second = 0;
            } else {
                imageno=0;
                imageButton.setImageResource(R.drawable.playcircle);
                imageButton.setTag(R.drawable.playcircle);
                text1.setText("");
                text2.setText("");
                click = false;
                second = 0;
            }
        }
    }

    class mytasks extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {  //ui직접건드리는영역
            super.onPreExecute();
            imageButton.setImageResource(imagelist[0]);
            imageButton.setTag(imagelist[0]);
            text1.setTextColor(Color.RED);
            text1.setText("시작부터 0 초");
       }



        @Override
        protected Void doInBackground(Integer... params) {  //진행중
            for(int i = params[0] ; true ; i++){
                if(isCancelled() ==true){ //사용자가 캔슬버튼  누르면
                    return null;
                }
                if(click) break;
                try {
                    second = i;
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values[0] >= Integer.parseInt(text2.getText().toString()) &&values[0]%Integer.parseInt(text2.getText().toString())== 0) {
                imageno++;
                if(imageno > imagelist.length-1) imageno =0;
                imageButton.setImageResource(imagelist[imageno]);
                imageButton.setTag(imagelist[imageno]);//이미지 바꿈
            }
            text1.setText("시작부터 "+values[0]+" 초");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            text1.setText(imagename[imageno]+" 선택 ("+second+")초");
            text1.setTextColor(Color.BLUE);
        }

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }
    }
}
