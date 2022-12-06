package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.service.MusicService;

public class ForegroundServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_song; // 声明一个编辑框对象
    private Button btn_send_service; // 声明一个按钮对象
    private boolean isPlaying = true; // 是否正在播放

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foregrand_service);
        et_song = findViewById(R.id.et_song);
        btn_send_service = findViewById(R.id.btn_send_service);
        btn_send_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_send_service) {
            if (TextUtils.isEmpty(et_song.getText())) {
                Toast.makeText(this, "请填写歌曲名称", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, MusicService.class);
            intent.putExtra("is_play", isPlaying);
            intent.putExtra("song", et_song.getText().toString());
            startService(intent);
            isPlaying = !isPlaying;
            btn_send_service.setText(isPlaying ? "暂停音乐" : "播放音乐");
            Log.d("lily", "onClick: ");
        }
    }
}