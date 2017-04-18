package com.mpos.catalogue.view;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyperbound.network.util.NetworkInterface;
import com.mpos.catalogue.R;
import com.mpos.catalogue.util.CameraUtil;
import com.mpos.catalogue.util.CatalogueInterface;

import java.io.IOException;

import static com.mpos.catalogue.CatalogueApplication.TAG;
import static com.mpos.catalogue.CatalogueApplication.getCatalogueContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment implements View.OnClickListener{

    ImageButton mAddItemImageButton;
    EditText mItemNameEdittext;
    EditText mItemPriceEdittext;
    Spinner mCategorySpinner;
    Spinner mSubcategorySpinner;
    EditText mDescriptionEditText;
    Button mAddNewItemButton;
    Bitmap mItemImage;
    private Uri imageUri = null;



    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_add_item, container, false);
        initView(rootview);
        return rootview;
    }

    private void initView(View view){
        mAddItemImageButton=(ImageButton) view.findViewById(R.id.additemimage);
        mAddItemImageButton.setOnClickListener(this);
        mItemNameEdittext=(EditText)view.findViewById(R.id.itemname);
        mItemPriceEdittext=(EditText)view.findViewById(R.id.itemprice);
        mDescriptionEditText=(EditText)view.findViewById(R.id.itemdescription);
        mCategorySpinner=(Spinner)view.findViewById(R.id.category_spinner);
        mSubcategorySpinner=(Spinner)view.findViewById(R.id.subcategory_spinner);
        mAddNewItemButton=(Button)view.findViewById(R.id.additembutton);
        mAddNewItemButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.additembutton){

        }else if(view.getId()==R.id.additemimage){
            checkForPermission();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CatalogueInterface.SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == CatalogueInterface.REQUEST_CAMERA) {
                String lImageId = convertImageUriToFile(imageUri, getActivity());
                if (lImageId != null) {
                    new LoadImagesFromSDCard().execute("" + lImageId);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.device_not_supported_text), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void checkForPermission() {
        boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, CatalogueInterface.REQUEST_PERMISSION);
        } else {
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CatalogueInterface.REQUEST_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_LONG).show();
                    selectImage();
                } else {
                    Toast.makeText(getActivity(), "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.take_photo_text), getString(R.string.select_from_gallery_text)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.select_photo_dialog__title_text));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.take_photo_text))) {
                    String fileName = String.valueOf(System.currentTimeMillis());
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                    imageUri = getActivity().getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(intent, CatalogueInterface.REQUEST_CAMERA);
                } else if (items[item].equals(getString(R.string.select_from_gallery_text))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            CatalogueInterface.SELECT_FILE);
                }
            }
        });
        builder.show();
    }
    //TODO:needs to be more generic code for xiomi
    private void onSelectFromGalleryResult(Intent data) {
        if (null != data) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    if (null != bitmap) {
                        //Giving device width to the square image
                        mItemImage= CameraUtil.changeOrientationIfRequired(selectedImageUri,bitmap,getActivity());
                        //bitmap = Bitmap.createScaledBitmap(bitmap, getDeviceWidth(), getDeviceWidth(), true);

                        //mBundle.putParcelable("Photo", bitmap);
                        //MovemberWeaponApp.setThumbnail(bitmap);
                        //goToNextScreen();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception :: ", e);
                }
            }
        }
    }

    public static String convertImageUriToFile(Uri imageUri, Activity activity) {

        Cursor cursor = null;
        int imageID = 0;

        try {

            /*********** Which columns values want to get *******/
            String[] proj = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };

            cursor = activity.getContentResolver().query(

                    imageUri,         //  Get data for specific image URI
                    proj,             //  Which columns to return
                    null,             //  WHERE clause; which rows to return (all rows)
                    null,             //  WHERE clause selection arguments (none)
                    null              //  Order-by clause (ascending by name)

            );

            //  Get Query Data
            if (null != cursor) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                int size = cursor.getCount();

                /*******  If size is 0, there are no images on the SD Card. *****/

                if (size == 0) {


                    Log.d(TAG, "size is 0");
                } else {

                    int thumbID = 0;
                    if (cursor.moveToFirst()) {
                        imageID = cursor.getInt(columnIndex);
                    }
                }
            } else {
                return null;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return "" + imageID;
    }

    public class LoadImagesFromSDCard extends AsyncTask<String, Void, Void> {

        private Bitmap mBitmap;
        private ProgressDialog Dialog = new ProgressDialog(getActivity());

        protected void onPreExecute() {
            Dialog.setMessage(" Loading image from Sdcard..");
            Dialog.show();
        }


        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {

            Bitmap bitmap = null;
            Bitmap newBitmap = null;
            Uri uri = null;


            try {

                uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + urls[0]);
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));

                if (bitmap != null) {
                    //taking device width for both height and width for the square image
                    bitmap=CameraUtil.changeOrientationIfRequired(uri,bitmap,getActivity());
                    //newBitmap = Bitmap.createScaledBitmap(bitmap, getDeviceWidth(), getDeviceWidth(), true);

                    if (bitmap != null) {
                        mItemImage = bitmap;
                    }
                    bitmap.recycle();
                }
            } catch (IOException e) {
                cancel(true);
            }

            return null;
        }


        protected void onPostExecute(Void unused) {
            if (Dialog != null) {
                Dialog.dismiss();
            }
            if (mBitmap != null) {
                // Set Image to ImageView
                //mBundle.putParcelable("Photo", mBitmap);
                //MovemberWeaponApp.setThumbnail(mBitmap);
                //goToNextScreen();
            }

        }

    }
}
