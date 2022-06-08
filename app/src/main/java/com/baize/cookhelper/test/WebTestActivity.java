package com.baize.cookhelper.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baize.cookhelper.R;

import java.io.File;
import java.io.IOException;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class WebTestActivity extends AppCompatActivity {

  private WebView webview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_test);
    webview = findViewById(R.id.webview);
    initWebView();
    checkIsPostEditor();
    //    webview.loadUrl("https:www.baidu.com");
//    webview.loadUrl("file:///android_asset/editor/editor.html");
//   /data/data/com.baize.cookhelper/files/editor/editor.html
    webview.loadUrl("file:///data/data/com.baize.cookhelper/files/editor/editor.html");
    Log.i("baize_", "path:" + new File(getFilesDir() + "/editor"));
  }

  private void checkIsPostEditor() {
    //1.检查缓存中帖子编辑器是否存在
    File editorFile = new File(getFilesDir() +"/editor");
    if (!editorFile.exists()) {
      Log.i("baize_", "帖子编辑器不存在...");
      //不能直接加载assets目录下文件，需要复制到缓存
//      File apk = new File(getCacheDir() + "/plugin.apk");
      try (Source source = Okio.source(getAssets().open("editor")); //输入
           Sink sink = Okio.sink(editorFile);//输出
           BufferedSink bufferSink = Okio.buffer(sink)) {
        bufferSink.writeAll(source);
        Log.i("baize_", "复制完成：" + editorFile.getPath());
      } catch (IOException e) {
        Log.i("baize_", "复制失败：" + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  private void initWebView() {
    WebSettings settings = webview.getSettings();
    // 设置允许访问文件数据
    settings.setAllowFileAccess(true);
    settings.setAllowContentAccess(true);
    webview.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
      }
    });
  }
}