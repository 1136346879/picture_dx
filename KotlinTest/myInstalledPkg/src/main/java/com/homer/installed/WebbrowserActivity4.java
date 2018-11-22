package com.homer.installed;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


@SuppressLint("NewApi")
public class WebbrowserActivity4 extends Activity {

    private static final int FILE_SELECT_CODE = 0;

    private WebView webView;
    /**
     * 不同方式的请求码
     */
    public static final int REQUEST_SELECT_FILE = 100;  
    public final static int FILECHOOSER_RESULTCODE = 1;  
    /**
     * 接收安卓5.0以上的
     */
    public ValueCallback<Uri[]> uploadMessage;
    /**
     * 接收5.0以下的
     */
    private ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_webbrowser);
        initWebView();

    }

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    private void initWebView() {
    	 webView=(WebView) findViewById(R.id.webView1);  
		   webView.getSettings().setJavaScriptEnabled(true);  
//		   webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");  
		   webView.getSettings().setSupportZoom(true);  
		   webView.getSettings().setDomStorageEnabled(true);  
//		   webView.getSettings().setPluginsEnabled(true);  
		   webView.requestFocus();  
		   webView.getSettings().setUseWideViewPort(true);  
		   webView.getSettings().setLoadWithOverviewMode(true);  
		   webView.getSettings().setSupportZoom(true);  
		   webView.getSettings().setBuiltInZoomControls(true);  

		   webView.setWebViewClient(new WebViewClient(){
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                 view.loadUrl(url);
	                 return true;
	             }
	        });
	        //设置支持调用相册
		   webView.setWebChromeClient(new WebChromeClient() {
	            // Android 3.0+
	            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
	                mUploadMessage = uploadMsg;
	                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	                i.addCategory(Intent.CATEGORY_OPENABLE);
	                i.setType("image/*");
	                startActivityForResult(Intent.createChooser(i, "File Chooser"),FILECHOOSER_RESULTCODE);
	            }

	            // Android 3.0+
	            public void openFileChooser(ValueCallback uploadMsg,String acceptType) {
	                mUploadMessage = uploadMsg;
	                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	                i.addCategory(Intent.CATEGORY_OPENABLE);
	                i.setType("*/*");
	                startActivityForResult(Intent.createChooser(i, "File Browser"),WebbrowserActivity4.FILECHOOSER_RESULTCODE);
	            }

	            // Android 4.1+ 会调用这个
	            public void openFileChooser(ValueCallback<Uri> uploadMsg,String acceptType, String capture) {
	                mUploadMessage = uploadMsg;
	                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	                i.addCategory(Intent.CATEGORY_OPENABLE);
	                i.setType("image/*");
	                startActivityForResult(
	                Intent.createChooser(i, "File Chooser"),WebbrowserActivity4.FILECHOOSER_RESULTCODE);
	            }

	            //Android 5.0会调用这个
	            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
	                //确保没有现有的消息
	                if (uploadMessage != null) {
	                    uploadMessage.onReceiveValue(null);
	                    uploadMessage = null;
	                }
	                uploadMessage = filePathCallback;
	                Intent intent = fileChooserParams.createIntent();
	                startActivityForResult(intent, REQUEST_SELECT_FILE);
	                return true;
	            }
	        });
	        
       
//        webView.loadUrl("http://116.62.145.128/H5/index.html#/chat?skillGroupId=11&enterpriseId=1");
        webView.loadUrl("https://kefu.huayunworld.com/H5/index.html#/chat?skillGroupId=43&enterpriseId=9");

    }
    final class InJavaScriptLocalObj {  
        public void showSource(String html) {  
            System.out.println("====>html="+html);  
        }  
    } 
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE && resultCode == RESULT_OK) {
            Uri uri = intent.getData();
//            Log.i("tag", "图片数据:"+uri.toString());
            //将图片显示到webView
            mUploadMessage.onReceiveValue(intent.getData());
            mUploadMessage = null;
        }else if (requestCode == REQUEST_SELECT_FILE) {  
//            if (uploadMessage == null) 
//                return;  
//            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));  
//            uploadMessage = null;  
        }  
    }

 
}

