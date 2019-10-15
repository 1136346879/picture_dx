package com.example.administrator.kotlintest.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.kotlintest.R;
import com.mukesh.MarkdownView;

public class MarkDownViewActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_markdownview);
    MarkdownView markdownView = (MarkdownView) findViewById(R.id.markdown_view);
    markdownView.setMarkDownText("# Hello World\nThis is a simple markdown\n"
        + "https://github.com/mukeshsolanki/MarkdownView-Android/");
    //markdownView.loadMarkdownFromAssets("README.md");
    markdownView.setOpenUrlInBrowser(true); // default false

  }
}
