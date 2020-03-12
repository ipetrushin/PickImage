package com.example.test12321;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final int PICK_CONTACT_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v)
    {
        Log.d("mytag","TAG:"+v.getId());
        //Intent i = new Intent(this, MainActivity.class)
        Intent i = new Intent(Intent.ACTION_PICK);

        //i.setDataAndType(Uri.parse("content://contacts"), ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        i.setType("image/*");
        startActivityForResult(i, PICK_CONTACT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // проверяем, что код вызова соответствует тому, что мы отправляли в методе onClick()
        if (requestCode == PICK_CONTACT_CODE) {

            // получаем Uri контакта
            Uri uri = data.getData();
            Log.d("mytag","request: "+uri);

            // запрашиваем данные контакта по его Uri
            final String[] columns = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.WIDTH,
                    MediaStore.Images.Media.HEIGHT
            };
            Cursor cursor = getContentResolver()
                    .query(uri, columns, null, null, null);

            Log.d("mytag","cursor size: "+cursor.getCount());
            cursor.moveToFirst();
            int width = cursor.getInt(1); // поле - ширина
            int height = cursor.getInt(2); // поле - ширина

            Log.d("mytag","image w: "+width + " image h: " +height);

        }
    }
}
