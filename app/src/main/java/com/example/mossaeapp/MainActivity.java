package com.example.mossaeapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mossaeapp.databinding.ActivityMainBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.Manifest;
import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityMainBinding binding;
    MediaPlayer mPlayer;
    GoogleMap gMap;
    CameraManager cameraManager;
    String mCameraId =null;
    private GpsInfo gps;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    Geocoder geocoder;//= new Geocoder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFrag;
        mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map); //as SupportMapFragment(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        assert mapFrag != null;
        mapFrag.getMapAsync(this);//getMapAsync(this);
        geocoder = new Geocoder(this);

        binding.message.setOnClickListener(arg0 -> {
            if (!isPermission) {
                callPermission();
                return;
            }

            gps = new GpsInfo(MainActivity.this);
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String[] str3;
            if (gps.isGetLocation()) {
                List<Address> list = null;
                try {
                    list = geocoder.getFromLocation(
                            latitude, // 위도
                            longitude, // 경도
                            10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }
                if (list != null) {
                    if (list.size() == 0) {
                        Toast.makeText(
                                getApplicationContext(),
                                "해당되는 주소 정보는 없습니다",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String str = list.get(0).toString();
                        String str2 = str.substring(str.indexOf("대한민국"));
                        str3 = str2.split("\"");

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.putExtra("sms_body", "http://maps.google.com/?q=" + latitude + "," + longitude + "\n\n" + str3[0]);

                        intent.setData(Uri.parse("smsto:" + Uri.encode("010-0000-0000")));
                        startActivity(intent);
                    }
                }
            } else {
                gps.showSettingsAlert();
            }
        });

        callPermission();

        binding.help.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_in_right, R.anim.hold);
        });

        binding.fakecall.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FakecallActivity.class);
            startActivity(intent);
        });

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        if (mCameraId == null) {
            try {
                for (String id : cameraManager.getCameraIdList()) {
                    CameraCharacteristics c = cameraManager.getCameraCharacteristics(id);
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        mCameraId = id;
                        break;
                    }
                }
            } catch (CameraAccessException e) {
                mCameraId = null;
                e.printStackTrace();
                return;
            }
        }

        binding.flash.setOnClickListener(v -> {
            if (binding.flash.isChecked()) {
                binding.flash.setBackgroundResource(R.drawable.flashon);
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(mCameraId, true);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else {
                binding.flash.setBackgroundResource(R.drawable.flash);
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(mCameraId, false);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });


        binding.police.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PoliceActivity.class);
            startActivity(intent);
        });

        binding.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Information.class);
                startActivity(intent);
            }
        });

        final ToggleButton siren = (ToggleButton) findViewById((R.id.siren));
        mPlayer = MediaPlayer.create(this, R.raw.sirensound);
        mPlayer.setLooping(true);
        siren.setOnClickListener(v -> {
            if (siren.isChecked()) {
                siren.setBackgroundResource(R.drawable.sirenon);
                mPlayer.start();
            } else {
                siren.setBackgroundResource(R.drawable.siren);
                mPlayer.pause();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessFineLocation = true;
        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessCoarseLocation = true;
        }
        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }


    private void callPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }


    public boolean onKeyLongPress(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_VOLUME_UP
                || keycode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(this, "긴급통화가 연결됩니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:01089378971")));
            return true;
        }
        return super.onKeyLongPress(keycode, event);
    }


    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_VOLUME_UP ||
                keycode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keycode, event);
    }


    public boolean onKeyUp(int keyCode, KeyEvent event) {

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if ((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng sungkyul = new LatLng(37.379865, 126.928731);
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        gMap.setMyLocationEnabled(true);
    }

    class GpsInfo extends Service implements LocationListener {

        private final Context mContext;


        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;
        boolean isGetLocation = false;

        Location location;
        double lat; // 위도
        double lon; // 경도

        private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
        private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

        protected LocationManager locationManager;

        public GpsInfo(Context context) {
            this.mContext = context;
            getLocation();
        }

        @TargetApi(23)
        public void getLocation() {
            if (Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission(
                            mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                            mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            try {
                locationManager = (LocationManager) mContext
                        .getSystemService(LOCATION_SERVICE);
                isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                } else {
                    this.isGetLocation = true;
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }

                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    lat = location.getLatitude();
                                    lon = location.getLongitude();
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void stopUsingGPS() {
            if (locationManager != null) {
                locationManager.removeUpdates(GpsInfo.this);
            }
        }

        public double getLatitude() {
            if (location != null) {
                lat = location.getLatitude();
            }
            return lat;
        }


        public double getLongitude() {
            if (location != null) {
                lon = location.getLongitude();
            }
            return lon;
        }

        public boolean isGetLocation() {
            return this.isGetLocation;
        }


        public void showSettingsAlert() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

            alertDialog.setTitle("GPS 사용유무셋팅");
            alertDialog.setMessage("GPS 셋팅이 되지 않았을수도 있습니다. \n 설정창으로 가시겠습니까?");

            alertDialog.setPositiveButton("Settings",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mContext.startActivity(intent);
                        }
                    });
            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();
        }

        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }

        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }
    }

    protected void onDestroy() {
        super.onDestroy();
        mPlayer.pause();

    }
}