package com.example.beroepsproduct4;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EvenementAanmaken extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference, databaseEvenementen;
    private EditText editTextEvenementnaam, editTextLocatie, editTextBeschrijving, editTextDatum;
    private Button btnOpslaan;
    private FirebaseAuth firebaseAuth;
    private ImageView ibEvenementFoto;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private Button mButtonUpload;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenementaanmaken);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextEvenementnaam = (EditText) findViewById(R.id.editTextEvenementnaam);
        editTextLocatie = (EditText) findViewById(R.id.editTextLocatie);
        editTextBeschrijving = (EditText) findViewById(R.id.editTextBeschrijving);
        editTextDatum = (EditText) findViewById(R.id.editTextDatum);
        ibEvenementFoto = (ImageView) findViewById(R.id.ibAfbeeldingEvenement);
        btnOpslaan = (Button) findViewById(R.id.button4);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseEvenementen = FirebaseDatabase.getInstance().getReference("Evenementen");
        btnOpslaan.setOnClickListener(this);

        mStorageRef = FirebaseStorage.getInstance().getReference().child("EvenementFoto/");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Evenementen");
        mButtonUpload = findViewById(R.id.button4);
        mProgressBar = findViewById(R.id.mProgressBar);
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(EvenementAanmaken.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onClick(View view) {

    }

    public void btnEvenementFoto(View view) {
        openFileChooser();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(ibEvenementFoto);
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() { mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(EvenementAanmaken.this, "Upload successful", Toast.LENGTH_LONG).show();


                            mStorageRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    return mStorageRef.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        String id = mDatabaseRef.push().getKey();
                                        String naam = editTextEvenementnaam.getText().toString().trim();
                                        String datum = editTextDatum.getText().toString().trim();
                                        String locatie = editTextLocatie.getText().toString().trim();
                                        String beschrijving = editTextBeschrijving.getText().toString().trim();
                                        String image = downloadUri.toString();
                                        Evenement evenement = new Evenement(id, naam, locatie, beschrijving, datum, image);
                                        mDatabaseRef.child(naam).setValue(evenement);
                                    } else {
                                        Toast.makeText(EvenementAanmaken.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EvenementAanmaken.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}