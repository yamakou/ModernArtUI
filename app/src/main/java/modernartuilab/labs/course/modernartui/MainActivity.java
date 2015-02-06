package modernartuilab.labs.course.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "ModernArtUI";
    private static final String URL = "http://www.moma.org";
    private TextView rect_black;
    private TextView rect_green;
    private TextView rect_red;
    private TextView rect_blue;
    private TextView rect_grey;
    private DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Make Action bar blue

        // TODO get reference to the rects.
        rect_black = (TextView)findViewById(R.id.rect1);
        rect_green = (TextView)findViewById(R.id.rect2);
        rect_red = (TextView)findViewById(R.id.rect3);
        rect_blue = (TextView)findViewById(R.id.rect4);
        rect_grey = (TextView)findViewById(R.id.rect5);

        final SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        Log.i(TAG, "onProgressChanged");
                        updateRectangleColor(progress);

                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Log.i(TAG, "onStartTrackingTouch");
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Log.i(TAG, "onStopTrackingTouch");
                    }
                }
        );
    }

    /**
     *
      * @param color current color
     * @param base_color base color
     * @param pos amount of color change
     * @return changed color
     */
    private int changeColor(int color ,int base_color, int pos){

        float[] hsv = new float[3];
        float[] hsv_base = new float[3];
        Color.colorToHSV(color, hsv);
        Color.colorToHSV(base_color, hsv_base);

        hsv[0] = hsv_base[0] + pos;
        hsv[1] = hsv_base[1] + pos/100;
        if (hsv[0] >= 360) {
            hsv[0] = hsv[0]% 360;
        }

        return Color.HSVToColor(hsv);
    }

    /**
     * update rectangle"s color
      * @param pos amount of color change
     */
    private void updateRectangleColor(int pos){
        Log.i(TAG, "updateRectangleColor");
        // TODO change color based on the current slider state.


        ColorDrawable drawable2 = (ColorDrawable) rect_green.getBackground();
        int color2 = drawable2.getColor();
        color2 = changeColor(color2, Color.GREEN, pos);
        rect_green.setBackgroundColor(color2);

        ColorDrawable drawable3 = (ColorDrawable) rect_red.getBackground();
        int color3 = drawable3.getColor();
        color3 = changeColor(color3, Color.RED, pos);
        rect_red.setBackgroundColor(color3);

        ColorDrawable drawable4 = (ColorDrawable) rect_blue.getBackground();
        int color4 = drawable4.getColor();
        color4 = changeColor(color4, Color.BLUE, pos);
        rect_blue.setBackgroundColor(color4);

    }

    /**
     * Jump to MOMA web site
      */
    private void gotoMOMAPage(){
        Log.i(TAG, "gotoMOMAPage");
        Intent intent = null;
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.more_information)
        {
            // Create a new AlertDialogFragment
            mDialog = AlertDialogFragment.newInstance();

            // Show AlertDialogFragment
            mDialog.show(getFragmentManager(), "Alert");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Class that creates the AlertDialog
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(getString(R.string.moma_dialog_message))

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Log.i(TAG, "Vitit MOMA");
                                    ((MainActivity)getActivity()).gotoMOMAPage();
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    Log.i(TAG, "Not Now");

                                }
                            }).create();
        }
    }

}
