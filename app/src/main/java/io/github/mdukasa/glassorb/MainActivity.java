package io.github.mdukasa.glassorb;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/** Shows the orb as large as the screen allows. Tap it to switch between spinning and dimmed. */
public class MainActivity extends Activity {
    private boolean active = true;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int size = (int) (Math.min(dm.widthPixels, dm.heightPixels) * 0.72f);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xff1d2226, 0xff0b0d0f}));

        final OrbView orb = new OrbView(this);
        root.addView(orb, new LinearLayout.LayoutParams(size, size));

        TextView title = new TextView(this);
        title.setText(R.string.app_name);
        title.setTextSize(28);
        title.setTextColor(0xffe8ecef);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, (int) (24 * dm.density), 0, 0);
        root.addView(title);

        final TextView hint = new TextView(this);
        hint.setText("Tap the orb to dim it");
        hint.setTextSize(14);
        hint.setTextColor(0xff8a949c);
        hint.setGravity(Gravity.CENTER);
        root.addView(hint);

        orb.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                active = !active;
                orb.setActive(active);
                hint.setText(active ? "Tap the orb to dim it" : "Tap the orb to spin it");
            }
        });
        setContentView(root);
    }
}
