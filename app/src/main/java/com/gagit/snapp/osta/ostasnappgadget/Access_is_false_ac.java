package com.gagit.snapp.osta.ostasnappgadget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Access_is_false_ac extends AppCompatActivity
{



    //Vars Start
    String Phone_number,SHA;
    WebView webView;
    //Vars Start





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_access_is_false_ac);
            refresh();
            get_number_and_sha_code();
            timer_refresher();
        }
        catch (Exception Err)
        {
            Log.i("Err",Err.getMessage());
        }
    }








    //Timer Check Start
    public void timer_refresher() throws Exception
    {

        CountDownTimer timer=new CountDownTimer(6000000,1000)
        {
            @Override
            public void onTick(long l)
            {


                new database_class(Phone_number, SHA);


                CountDownTimer timer1=new CountDownTimer(9800,100)
                {
                    @Override
                    public void onTick(long l)
                    {

                    }

                    @Override
                    public void onFinish()
                    {

                        if(user_class.profile_status.trim().equals("true"))
                        {
                            startActivity(new Intent(getApplicationContext(), logo_ac.class));
                        }

                    }

                }.start();

            }

            @Override
            public void onFinish()
            {

            }
        }.start();

    }
    //Timer Check End










    //Get SHA And Phone User Number Start
    public void get_number_and_sha_code() throws Exception
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Snapp_gagit_Services_profile", MODE_PRIVATE);
        Phone_number = sharedPreferences.getString("phone", "");
        SHA = sharedPreferences.getString("SHA", "");

    }
    //Get SHA And Phone User Number End







    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }








    //Get All Components Start
    public void refresh() throws Exception
    {
        webView=(WebView)findViewById(R.id.web_view_Access_is_false_ac);


        webView.setWebViewClient(new WebViewClient()
                                 {
                                     public void onPageFinished(WebView view, String url) {


                                     }


                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                         if (url.startsWith("tel:")) {
                                             Intent intent = new Intent(Intent.ACTION_DIAL,
                                                     Uri.parse(url));
                                             startActivity(intent);
                                         } else if (url.startsWith("http:") || url.startsWith("https:")) {
                                             view.loadUrl(url);
                                         }
                                         return true;

                                     }


                                     @Override
                                     public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                                         Intent Err = new Intent(Access_is_false_ac.this, no_internet_ac.class);

                                         startActivity(Err);

                                     }


                                 }
        );

        webView.setWebChromeClient(new WebChromeClient() {










            public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> filePath, WebChromeClient.FileChooserParams fileChooserParams) {
                // Double check that we don't have any existing callbacks
                if (mFilePathCallback != null) {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = filePath;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        Log.e("", "Unable to create Image File", ex);
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("image/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }
                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
                return true;
            }








            private File createImageFile() throws IOException {
                // Create an image file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File imageFile = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".jpg",         /* suffix */
                        storageDir      /* directory */
                );
                return imageFile;
            }














        });


        webView.getSettings().setJavaScriptEnabled(true);

        security security=new security();


        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.getSettings().setAllowFileAccess(true);



        byte []post=EncodingUtils.getBytes("security="+security.code()+"&uid="+user_class.uid+"&type=1","UTF-8");
        webView.postUrl("http://snapp-gadget.ir/api/user_s/to_server/profile.php",post);

    }
    //Get All Components End








    //Chosee FIle Web View
    private static final int INPUT_FILE_REQUEST_CODE = 1;
    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private ValueCallback<Uri[]> mFilePathCallback;
    private Uri mCapturedImageURI = null;
    private String mCameraPhotoPath;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri[] results = null;
            // Check that the response is a good one
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == this.mUploadMessage) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCode != RESULT_OK) {
                        result = null;
                    } else {
                        // retrieve from the private variable if the intent is null
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "activity :" + e,
                            Toast.LENGTH_LONG).show();
                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
        return;
    }
    //Chosee FIle Web View





}
