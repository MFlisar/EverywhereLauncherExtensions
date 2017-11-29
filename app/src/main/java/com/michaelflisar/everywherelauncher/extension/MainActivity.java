package com.michaelflisar.everywherelauncher.extension;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.michaelflisar.everywherelauncher.extension.common.CommonExtensionManager;
import com.michaelflisar.everywherelauncher.extension.services.MyAccessibilityService;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvAccessibilityState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAccessibilityState = findViewById(R.id.tvAccessibilityState);
        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        boolean accessibilityServiceRunning = CommonExtensionManager.isAccessibilityEnabled(this);
        tvAccessibilityState.setText(accessibilityServiceRunning ? R.string.yes : R.string.no);
        tvAccessibilityState.setTextColor(accessibilityServiceRunning ? Color.GREEN : Color.RED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btEnableAccessibilityService:
                if (!MyAccessibilityService.isAccessibilityEnabled()) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.already_running, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}