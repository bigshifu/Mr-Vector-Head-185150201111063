package com.example.challengepapb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgHead, imgHair, imgEyebrow, imgEyes, imgBeard, imgMoustache;
    private SwitchCompat swHead, swHair, swEyebrow, swEyes, swBeard, swMoustache;
    private Button btnHead, btnHair, btnEyebrow, btnEyes, btnBeard, btnMoustache;

    private ImageView[] imageViews;
    private SwitchCompat[] switchs;
    private Uri[] uris = new Uri[6];

    private final int PICK_IMAGE = 0;
    private int numSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setState(savedInstanceState);
    }

    private void setViews() {
        imgHead = findViewById(R.id.imgHead);
        imgHair = findViewById(R.id.imgHair);
        imgEyebrow = findViewById(R.id.imgEyebrow);
        imgEyes = findViewById(R.id.imgEyes);
        imgBeard = findViewById(R.id.imgBeard);
        imgMoustache = findViewById(R.id.imgMoustache);
        swHead = findViewById(R.id.swHead);
        swHair = findViewById(R.id.swHair);
        swEyebrow = findViewById(R.id.swEyebrow);
        swEyes = findViewById(R.id.swEyes);
        swBeard = findViewById(R.id.swBeard);
        swMoustache = findViewById(R.id.swMoustache);
        btnHead = findViewById(R.id.btnHead);
        btnHair = findViewById(R.id.btnHair);
        btnEyebrow = findViewById(R.id.btnEyebrow);
        btnEyes = findViewById(R.id.btnEyes);
        btnBeard = findViewById(R.id.btnBeard);
        btnMoustache = findViewById(R.id.btnMoustache);

        swHead.setOnClickListener(this);
        swHair.setOnClickListener(this);
        swEyebrow.setOnClickListener(this);
        swEyes.setOnClickListener(this);
        swBeard.setOnClickListener(this);
        swMoustache.setOnClickListener(this);
        btnHead.setOnClickListener(this);
        btnHair.setOnClickListener(this);
        btnEyebrow.setOnClickListener(this);
        btnEyes.setOnClickListener(this);
        btnBeard.setOnClickListener(this);
        btnMoustache.setOnClickListener(this);

        imageViews = new ImageView[]{imgHead, imgHair, imgEyebrow, imgEyes, imgBeard, imgMoustache};
        switchs = new SwitchCompat[]{swHead, swHair, swEyebrow, swEyes, swBeard, swMoustache};
    }

    private void setState(Bundle bundle) {
        if (bundle != null) {
            for (int i = 0; i < switchs.length; i++) {
                switchs[i].setChecked(bundle.getBoolean("switch-" + i, true));
                imageViews[i].setVisibility(switchs[i].isChecked() ? View.VISIBLE : View.GONE);
            }

            uris = (Uri[]) bundle.getParcelableArray("uris");
            for (int i = 0; i < uris.length; i++) {
                imageViews[i].setImageURI(uris[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < switchs.length; i++) {
            outState.putBoolean("switch-" + i, switchs[i].isChecked());
        }

        outState.putParcelableArray("uris", uris);
    }

    private void selectImage(int i) {
        numSelected = i;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageViews[numSelected].setImageURI(data.getData());
            imageViews[numSelected].setVisibility(View.VISIBLE);
            switchs[numSelected].setChecked(true);
            uris[numSelected] = data.getData();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == swHead) {
            imgHead.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == swHair) {
            imgHair.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == swEyebrow) {
            imgEyebrow.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == swEyes) {
            imgEyes.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == swBeard) {
            imgBeard.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == swMoustache) {
            imgMoustache.setVisibility(((SwitchCompat) view).isChecked() ? View.VISIBLE : View.GONE);
        } else if (view == btnHead) {
            selectImage(0);
        } else if (view == btnHair) {
            selectImage(1);
        } else if (view == btnEyebrow) {
            selectImage(2);
        } else if (view == btnEyes) {
            selectImage(3);
        } else if (view == btnBeard) {
            selectImage(4);
        } else if (view == btnMoustache) {
            selectImage(5);
        }
    }
}