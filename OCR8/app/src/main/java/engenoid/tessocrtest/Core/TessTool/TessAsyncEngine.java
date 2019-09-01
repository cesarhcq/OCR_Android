package engenoid.tessocrtest.Core.TessTool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import engenoid.tessocrtest.Core.Dialogs.ImageDialog;
import engenoid.tessocrtest.Core.ImageProcessing;
import engenoid.tessocrtest.Core.Imaging.Tools;
import engenoid.tessocrtest.FotoFrameWork;

/**
 * Created by Fadi on 6/11/2014.
 */
public class TessAsyncEngine extends AsyncTask<Object, Void, String> {

    static final String TAG = "DBG_" + TessAsyncEngine.class.getName();

    private Bitmap bmp;
    private Bitmap bmp2;

    private Activity context;
    public static String ocrresult="0";
    public static String ocrresult2="0";
    public int i=0;

    @Override
    protected String doInBackground(Object... params) {

        try {

            if(params.length < 2) {
                Log.e(TAG, "Error passing parameter to execute - missing params");
                return null;
            }

            if(!(params[0] instanceof Activity) || !(params[1] instanceof Bitmap)) {
                Log.e(TAG, "Error passing parameter to execute(context, bitmap)");
                return null;
            }

            context = (Activity)params[0];

            bmp = (Bitmap)params[1];
            bmp2 = FotoFrameWork.bmp2;


            //ImageProcessing.imageprocessing_box1(bmp);
            ImageProcessing.imageprocessing_test(bmp);
            ImageProcessing.imageprocessing_box2(bmp2);

            /*
            //processamento de imagem box 1
            try{
                //System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
                //Mat source = new Mat(bmp2.getHeight(),bmp2.getWidth(), CvType.CV_8UC1);
                Mat source = new Mat();
                Utils.bitmapToMat(bmp,source);



                // now convert to gray
                //Mat imageMat = new Mat (bmp2.getHeight(), bmp2.getWidth(), CvType.CV_8U, new Scalar(4));
                Mat grayMat = new Mat ( bmp.getHeight(), bmp.getWidth(), CvType.CV_8U, new Scalar(1));
                Imgproc.cvtColor(source, grayMat, Imgproc.COLOR_RGB2GRAY, 1);



                //equalize image
                Mat equamat = new Mat ( bmp.getHeight(), bmp.getWidth(), CvType.CV_8U, new Scalar(1));
                Imgproc.equalizeHist(grayMat,equamat);



                // get the thresholded image
                Mat thresholdMat = new Mat ( bmp.getHeight(), bmp.getWidth(),CvType.CV_8U, new Scalar(1));

                //Imgproc.threshold(equamat, thresholdMat , 32, 255, Imgproc.THRESH_BINARY);
                Imgproc.adaptiveThreshold(equamat, thresholdMat , 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 71, 40);
                //51,80


                //erosion
                int dilation_size = 2;

                Mat dilatation = new Mat(source.rows(),source.cols(),source.type());
                Mat element2 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(0.1*dilation_size+1, 0.1*dilation_size+1));
                Imgproc.dilate(thresholdMat, dilatation, element2);


                //erosion
                int erosion_size = 2;

                Mat erosion = new Mat(source.rows(),source.cols(),source.type());
                Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(0.5*erosion_size+1, 2*erosion_size+1));
                Imgproc.erode(dilatation, erosion, element1);



                Utils.matToBitmap(thresholdMat,bmp);
                Log.d(TAG, "OpenCV_Image1");

            } catch (Exception e){
                System.out.println("error: " + e.getMessage());
                Log.d(TAG, "Erro_OpenCV_Image1");
            }
            */

            /*
            //processamento de imagem box 2
            try{
                //System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
                //Mat source = new Mat(bmp2.getHeight(),bmp2.getWidth(), CvType.CV_8UC1);
                Mat source = new Mat();
                Utils.bitmapToMat(bmp2,source);



                // now convert to gray
                //Mat imageMat = new Mat (bmp2.getHeight(), bmp2.getWidth(), CvType.CV_8U, new Scalar(4));
                Mat grayMat = new Mat ( bmp2.getHeight(), bmp2.getWidth(), CvType.CV_8U, new Scalar(1));
                Imgproc.cvtColor(source, grayMat, Imgproc.COLOR_RGB2GRAY, 1);



                //equalize image
                Mat equamat = new Mat ( bmp2.getHeight(), bmp2.getWidth(), CvType.CV_8U, new Scalar(1));
                Imgproc.equalizeHist(grayMat,equamat);



                // get the thresholded image
                Mat thresholdMat = new Mat ( bmp2.getHeight(), bmp2.getWidth(),CvType.CV_8U, new Scalar(1));

                //Imgproc.threshold(equamat, thresholdMat , 32, 255, Imgproc.THRESH_BINARY);
                Imgproc.adaptiveThreshold(equamat, thresholdMat , 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 9, 20);
                //51,80

                /*
                //erosion
                int dilation_size = 2;

                Mat dilatation = new Mat(source.rows(),source.cols(),source.type());
                Mat element2 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(0.5*dilation_size+1, 0.1*dilation_size+1));
                Imgproc.dilate(thresholdMat, dilatation, element2);


                //erosion
                int erosion_size = 2;

                Mat erosion = new Mat(source.rows(),source.cols(),source.type());
                Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(0.5*erosion_size+1, 2*erosion_size+1));
                Imgproc.erode(dilatation, erosion, element1);



                Utils.matToBitmap(thresholdMat,bmp2);
                Log.d(TAG, "OpenCV_Image2");

            } catch (Exception e){
                System.out.println("error: " + e.getMessage());
                Log.d(TAG, "Erro_OpenCV_Image2");
            }
            */





            if(context == null || bmp == null) {
                Log.e(TAG, "Error passed null parameter to execute(context, bitmap)");
                return null;
            }

            int rotate = 0;

            if(params.length == 3 && params[2]!= null && params[2] instanceof Integer){
                rotate = (Integer) params[2];
            }

            if(rotate >= -180 && rotate <= 180 && rotate != 0)
            {
                bmp = Tools.preRotateBitmap(bmp, rotate);
                Log.d(TAG, "Rotated OCR bitmap " + rotate + " degrees");
            }


            TessEngine tessEngine =  TessEngine.Generate(context);

            bmp = bmp.copy(Bitmap.Config.ARGB_8888, true);

            String result = tessEngine.detectText(bmp);



            if(params.length == 3 && params[2]!= null && params[2] instanceof Integer){
                rotate = (Integer) params[2];
            }

            if(rotate >= -180 && rotate <= 180 && rotate != 0)
            {
                bmp2 = Tools.preRotateBitmap(bmp2, rotate);
                Log.d(TAG, "Rotated OCR bitmap 2" + rotate + " degrees 2");
            }



            bmp2 = bmp2.copy(Bitmap.Config.ARGB_8888, true);

            String result2 = tessEngine.detectText(bmp2);

                ocrresult = result;
                ocrresult2 = result2;
                Log.d(TAG,"OCR result1 "+ocrresult);
                Log.d(TAG,"OCR result2 "+ocrresult2);

            return result;

        } catch (Exception ex) {
            Log.d(TAG, "Error: " + ex + "\n" + ex.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        if(s == null || bmp == null || bmp2 == null ||context == null)
            return;

        ImageDialog.New()
                .addBitmap(bmp)
                .addBitmap2(bmp2)
                .addTitle(s)
                .addTitle2(ocrresult2)
                .show(context.getFragmentManager(), TAG);

        super.onPostExecute(s);
    }
}
