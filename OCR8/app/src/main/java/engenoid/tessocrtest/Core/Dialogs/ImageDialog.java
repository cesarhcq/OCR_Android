package engenoid.tessocrtest.Core.Dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googlecode.leptonica.android.Binarize;
import com.googlecode.leptonica.android.Convert;
import com.googlecode.leptonica.android.Enhance;
import com.googlecode.leptonica.android.WriteFile;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.concurrent.BlockingDeque;

import engenoid.tessocrtest.R;

import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * Created by Fadi on 5/11/2014.
 */
public class ImageDialog extends DialogFragment{

    static final String TAG = "DBG_" + ImageDialog.class.getName();

    private Bitmap bmp;
    private Bitmap bmp2;
    private String title;


    private String title2;

    public ImageDialog(){}

    public static ImageDialog New(){
        return new ImageDialog();
    }

    public ImageDialog addBitmap(Bitmap bmp) {
        if (bmp != null) {
            this.bmp = bmp;
            Log.d(TAG, "ocr_imagem1");
        }
        return this;
    }

    public ImageDialog addBitmap2(Bitmap bmp2) {
        if (bmp2 != null) {
            this.bmp2 = bmp2;
            Log.d(TAG, "ocr_imagem2");
        }
        return this;
    }

    public ImageDialog addTitle(String title) {
        if (title != null) {
            this.title = title;
            Log.d(TAG, "ocr_algarismo1");
        }
        return this;
    }

    public ImageDialog addTitle2(String title2) {
        if (title2 != null) {
            this.title2 = title2;
            Log.d(TAG, "ocr_algarismo2");
        }
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_dialog, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_dialog_imageView);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.image_dialog_imageView2);

        TextView textView = (TextView) view.findViewById(R.id.image_dialog_textView);
        TextView textView2 = (TextView) view.findViewById(R.id.image_dialog_textView2);

        if (bmp != null) {
            imageView.setImageBitmap(bmp);
        }

        if (bmp2 != null) {
            imageView2.setImageBitmap(bmp2);
        }

        if(title!=null) {
            textView.setText(title);
        }

        if(title2!=null) {
            textView2.setText(title2);
        }

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        bmp.recycle();
        bmp = null;
        //bmp2.recycle();
        //bmp2 = null;
        System.gc();
        super.onDismiss(dialog);
    }

}