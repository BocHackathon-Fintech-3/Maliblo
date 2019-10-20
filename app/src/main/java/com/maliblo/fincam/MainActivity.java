package com.maliblo.fincam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.maliblo.fincam.Db.DataBaseAPP;
import com.maliblo.fincam.Db.tables.Bills;
import com.maliblo.fincam.Db.tables.ExtractedData;
import com.maliblo.fincam.Responses.RespFullLabel;
import com.maliblo.fincam.Responses.RespLabelPrediction;
import com.maliblo.fincam.Responses.RespLabelResult;
import com.maliblo.fincam.ViewModelsss.BillDetails;
import com.maliblo.fincam.ViewModelsss.ExtractDataView;
import com.maliblo.fincam.ViewModelsss.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    private ArrayList<ExtractDataView> model;
    private ProgressDialog progressDialog;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.extractedData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final DataAdapter adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getExtractData().observe(this, new Observer<List<ExtractedData>>() {
            @Override
            public void onChanged(List<ExtractedData> extractedData) {
                model = new ArrayList<>();
                for (int i = 0; extractedData.size() > i; i++) {
                    int bill_id = extractedData.get(i).getBill_id();
                    String invoiceNo = extractedData.get(i).getInvoiceNo();
                    String compName = extractedData.get(i).getCompany();
                    String total = extractedData.get(i).getTotal();
                    String invChecNo = extractedData.get(i).getInvoiceNo();
                    String barcode = extractedData.get(i).getBarcode();
                    CompanyLogos.setLogo(extractedData.get(i).getCompany());


                    model.add(new ExtractDataView(CompanyLogos.setLogo(extractedData.get(i).getCompany()), compName, total, extractedData));
                }
                adapter.setExtractData(model);

            }
        });

        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                String company = model.get(position).getExtractedData().get(position).getCompany();
                String invoiceNo = model.get(position).getExtractedData().get(position).getInvoiceNo();
                String invoiceCheckDigits = model.get(position).getExtractedData().get(position).getInvoiceCheckNo();
                String barcode = model.get(position).getExtractedData().get(position).getBarcode();
                int billId = model.get(position).getExtractedData().get(position).getBill_id();
                int id = model.get(position).getExtractedData().get(position).getId();
                String total = model.get(position).getExtractedData().get(position).getTotal();

                showDetails(id,company,barcode,invoiceNo,invoiceCheckDigits,total,billId,"");
            }
        });

//        DataBaseAPP.insertExtractedData(new ExtractedData(2, Constants.EPIC, "191001156591234", "€28,30", "7", "275123123812741241872132"), getApplicationContext());
//        DataBaseAPP.insertExtractedData(new ExtractedData(2, Constants.EAC, "045 729 0827 3", "€275,23", "312", "045 729 0827 3 312 275,23"), getApplicationContext());
//        DataBaseAPP.insertExtractedData(new ExtractedData(2, Constants.CYTA, "0231 1241 2", "€43,21", "23", "123686125324378"), getApplicationContext());
//        DataBaseAPP.insertExtractedData(new ExtractedData(2, Constants.EAC, "075 719 0026 2", "€80,41", "321", "075 719 0026 2321 80,41"), getApplicationContext());
//        DataBaseAPP.extractedData(new ExtractedData(2, Constants.EAC, "InvoiceNom", "20$", "DAFDSF", "DFADF"), getApplicationContext());
//        DataBaseAPP.insertBills(new Bills("uri", "timestamp"), getApplicationContext());
//        DataBaseAPP.insertBillTags(new BillTags(1, 2), getApplicationContext());
//        DataBaseAPP.insertPayment(new PaymentsDB(1, "timestamp", "status"), getApplicationContext());
//        DataBaseAPP.insertTags(new Tags("tags"), getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });
    }

    private void showDetails(int id,String company, String barcode,String invoiceNo,String invoiceCheckDigits,String total,int bill_id,String imagePath){
        Intent intent = new Intent(getApplicationContext(), BillDetails.class);
        intent.putExtra("COMPANY", company);
        intent.putExtra("BARCODE", barcode);
        intent.putExtra("INVOICE_NO",invoiceNo);
        intent.putExtra("INVOICE_CHECK_DIGITS",invoiceCheckDigits);
        intent.putExtra("TOTAL", total);
       intent.putExtra("BILL", String.valueOf(bill_id));
        intent.putExtra("ID", String.valueOf(id));
         intent.putExtra("IMAGEPATH", imagePath);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );

        imagePath = image.getAbsolutePath();
        return image;
    }

    private void captureImage(){
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d(Constants.LOG_TAG,"ERROR: " + ex.getMessage());
            }
            if (photoFile != null) {

                Uri uri = FileProvider.getUriForFile(this,
                        "com.maliblo.fincam.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                pictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                startActivityForResult(pictureIntent,
                        Constants.CAMERA_RESULT);
            }
        }

    }
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constants.PICK_PHOTO_RESULT:
//data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Log.d(Constants.LOG_TAG, "Uri: " + filePathColumn.toString());
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
//                    imageBitmap = BitmapFactory.decodeFile(imgDecodableString);
//                    imageView.setImageBitmap(imageBitmap);
//                    uploadToServer(getRealPathFromURI(selectedImage));
                    uploadToServer(imagePath);
                    break;
                case Constants.CAMERA_RESULT:
//                    Glide.with(this).load(imagePath).into(imageView);
                    uploadToServer(imagePath);
                    break;
            }
        }else{
            Log.d(Constants.LOG_TAG,"Failed to get image");
        }
    }

    private void uploadToServer(final String filePath) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("FinCam");
        progressDialog.setMessage("The Magic is happening now!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        UploadAPIs uploadAPIs = retrofit.create(UploadAPIs.class);

        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Call<RespFullLabel> call = uploadAPIs.uploadImage(body,Constants.NANONETS_MODEL_ID);
        call.enqueue(new Callback<RespFullLabel>() {
            @Override
            public void onResponse(Call<RespFullLabel> call, Response<RespFullLabel> response) {
                progressDialog.hide();
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(Constants.LOG_TAG,response.body().getMessage());

 
                    RespLabelResult labelResult = response.body().getResult().get(0);
                    List<RespLabelPrediction> labelPredictions = labelResult.getPrediction();
                    String currentValue;
                    String barcode="";
                    String company="";
                    String check_digits="";
                    String invoice_no="";
                    String total="";

                    for(int i=0;i<labelPredictions.size();i++) {
                        if(labelPredictions.get(i).getScore()>0.5) {
//                            Log.d(Constants.LOG_TAG, labelPredictions.get(i).getLabel());
//                            Log.d(Constants.LOG_TAG, labelPredictions.get(i).getOcr_text());
                            currentValue = labelPredictions.get(i).getOcr_text();
                            //skip empty values
                            if(currentValue==null || currentValue.equals(" ") || currentValue.length()==0){
                                continue;
                            }
                            switch (labelPredictions.get(i).getLabel()) {
                                case Constants.LBL_BARCODE:
                                    Log.d(Constants.LOG_TAG,"Barcode:" + currentValue);
                                    Log.d(Constants.LOG_TAG,currentValue.replaceAll("[^0-9]", ""));
                                    currentValue = currentValue.replaceAll("[^0-9]", "");
                                    if(currentValue.length()>=Constants.EAC_BARCODE_MIN_LENGTH) {
                                        barcode = currentValue;
                                    }
                                    break;
                                case Constants.LBL_COMPANY_NAME:
                                    company = currentValue;
                                    break;
                                case Constants.LBL_INVOICE_CHECK_DIGITS:
                                    //keep the highest prediction
                                    Log.d(Constants.LOG_TAG,"Invoice Check Digits:" + currentValue);
                                    currentValue = currentValue.replaceAll("[^0-9]", "");
                                    if(currentValue.length()==Constants.EAC_INVOICE_CHECK_DIGITS_LENGTH ) {
                                        check_digits = currentValue;
                                    }
                                    break;
                                case Constants.LBL_INVOICE_NO:
                                    Log.d(Constants.LOG_TAG,"Invoice No:" + currentValue);
                                    currentValue = currentValue.replaceAll("[^0-9]", "");
                                    if(currentValue.length()>invoice_no.length()) {
                                        invoice_no = currentValue;
                                    }
                                    break;
                                case Constants.LBL_TOTAL:
                                    Log.d(Constants.LOG_TAG,"Total:" + currentValue);
                                    currentValue = currentValue.replaceAll("[^0-9]", "");
                                    if(currentValue.length()>total.length()) {
                                        total = currentValue;
                                    }
                                    break;
                            }
                        }
                    }
                    if(InvoiceValidator.validateEAC(company,barcode,invoice_no,check_digits,total)) {
                        DataBaseAPP.insertExtractedData(new ExtractedData(2, InvoiceValidator.getCompany(), InvoiceValidator.getInvoice_no(), InvoiceValidator.getTotal(), InvoiceValidator.getInvoice_check_digits(), InvoiceValidator.getBarcode()), getApplicationContext());
                        DataBaseAPP.insertBills(new Bills(imagePath, HelperClass.getTimeStamp()), getApplicationContext());
                        showDetails(0,InvoiceValidator.getCompany(), InvoiceValidator.getBarcode(), InvoiceValidator.getInvoice_no(),InvoiceValidator.getInvoice_check_digits(), InvoiceValidator.getTotal(), 0,imagePath);
                    }else{
                        Log.d(Constants.LOG_TAG,InvoiceValidator.getErrorMsg());
                        showErrorDialog(InvoiceValidator.getErrorMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<RespFullLabel> call, Throwable t) {
                Log.d(Constants.LOG_TAG,"Failed to resolve internet data");
            }
        });
    }

    public void showErrorDialog(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Magic Smoke covers everything{ " + message +  " }. Do you want to upload this invoice to help us improve our services?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Log.d(Constants.LOG_TAG,"Please upload");
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(Constants.LOG_TAG,"User doesn't want to upload the invoice");
//                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
