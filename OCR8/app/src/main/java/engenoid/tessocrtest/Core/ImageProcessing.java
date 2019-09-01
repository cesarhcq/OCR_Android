package engenoid.tessocrtest.Core;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.renderscript.Double2;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.Vector;

/**
 * Created by alantavares on 10/06/16.
 */
public class ImageProcessing{

    static final String TAG = "DBG_ " + ImageProcessing.class.getName();

    public static Bitmap imageprocessing_box1(Bitmap bmp) {


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
        Mat element2 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(0.3*dilation_size+1, 0.1*dilation_size+1));
        Imgproc.dilate(thresholdMat, dilatation, element2);


         //erosion
         int erosion_size = 2;

         Mat erosion = new Mat(source.rows(),source.cols(),source.type());
         Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(0.5*erosion_size+1, 2*erosion_size+1));
         Imgproc.erode(dilatation, erosion, element1);

        Mat Edgemat = new Mat(source.rows(),source.cols(),source.type());

        Imgproc.Canny(grayMat,Edgemat,10,250);



         Utils.matToBitmap(Edgemat,bmp);
         Log.d(TAG, "OpenCV_Image1");

        return bmp;
    }

    public static Bitmap imageprocessing_box2(Bitmap bmp2) {

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
        Imgproc.adaptiveThreshold(equamat, thresholdMat , 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 71, 40);
        //51,80


        /*
        //erosion
        int dilation_size = 2;

        Mat dilatation = new Mat(source.rows(),source.cols(),source.type());
        Mat element2 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(0.3*dilation_size+1, 0.1*dilation_size+1));
        Imgproc.dilate(thresholdMat, dilatation, element2);


        //erosion
        int erosion_size = 2;

        Mat erosion = new Mat(source.rows(),source.cols(),source.type());
        Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(0.5*erosion_size+1, 2*erosion_size+1));
        Imgproc.erode(dilatation, erosion, element1);

        Mat Edgemat = new Mat(source.rows(),source.cols(),source.type());



        Imgproc.Canny(grayMat,thresholdMat,10,250);
        */

        Utils.matToBitmap(thresholdMat,bmp2);
        Log.d(TAG, "OpenCV_Image2");

        return bmp2;
    }

    public static Bitmap imageprocessing_test(Bitmap bmp) {

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

        Utils.matToBitmap(thresholdMat,bmp);

        int i,j;
        int k=0, pixcont=0;

        int pixd;
        int pixm;
        int pixe;
        int medpix;

        int[] ind = new int[100];

        float[] lamb = new float[bmp.getWidth()];
        float[] lamb2 = new float[bmp.getHeight()];
        float[] lamb3 = new float[bmp.getWidth()];
        float[] lamb4 = new float[bmp.getHeight()];


        //linhas vertival direita
        for(j= bmp.getWidth()-2 ;j > 1 ; j--) {
            for (i = 0 ; i < bmp.getHeight() ; i++) {
                pixd = Color.red(bmp.getPixel(j + 1, i));
                pixm = Color.red(bmp.getPixel(j, i));
                pixe = Color.red(bmp.getPixel(j - 1, i));
                medpix = ((pixd + pixm + pixe) / 3);

                //densidade de pretos (pelos menos 2 pretos de 3 considera preto)
                if (medpix <= 85) {
                    pixcont++;
                }
            }
            //densidade pixels por coluna
            lamb[j] = (pixcont / (float) bmp.getHeight() * 100);

            if (lamb[j+1] > 40 && (lamb[j+1]-lamb[j]) >= 0 ) {

                ind[k] = j+1;
                int aux = ind[k];
                Log.d(TAG, "ind["+k+"]: "+aux+" Lambida_atual["+j+"]: " + Float.toString(lamb[j])+"%"+" Lambida_anterior["+(j+1)+"]: " + Float.toString(lamb[j+1])+"%");
                k++;
                pixcont=0;
                break;

            } else{

                Log.d(TAG, "Lambida(coluna): " + Float.toString(lamb[j]) + " %" + "Posição[J]: "+j);
                pixcont = 0;
            }

        }

        Log.d(TAG, "Fim");
        Log.d(TAG, "Inicio");



        //linhas horizontais superior
        for(i=1 ; i<bmp.getHeight()-2 ; i++) {
            for (j=0; j<bmp.getWidth(); j++) {
                pixd = Color.red(bmp.getPixel(j,i-1));
                pixm = Color.red(bmp.getPixel(j,i));
                pixe = Color.red(bmp.getPixel(j,i+1));
                medpix = ((pixd + pixm + pixe) / 3);

                //densidade de pretos (pelos menos 2 pretos de 3 considera preto)
                if (medpix <= 85) {
                    pixcont++;
                }
            }

            //densidade pixels por linha
            lamb2[i] = (pixcont / (float) bmp.getWidth() * 100);

            if ( lamb2[i-1] > 20 && (lamb2[i-1]-lamb2[i]) >= 0 ) {
                ind[k] = i-1;
                int aux = ind[k];
                Log.d(TAG, "Valor do ind["+k+"]: "+aux+" Salvo - Lambida_atual["+i+"]: " + Float.toString(lamb2[i])+"%"+" Lambida_anterior["+(i-1)+"]: " + Float.toString(lamb2[i-1])+"%");
                k++;
                pixcont=0;
                break;
            } else{

                Log.d(TAG, "Lambida(linha): " + Float.toString(lamb2[i]) + " %" + "Posição[i]: "+i);
                pixcont = 0;
            }

        }

        Log.d(TAG, "Fim");
        Log.d(TAG, "Inicio");



        //linhas vertival esquerda
        for(j=1 ; j<bmp.getWidth()-2 ; j++) {
            for (i=0 ; i <bmp.getHeight() ; i++) {
                pixd = Color.red(bmp.getPixel(j + 1, i));
                pixm = Color.red(bmp.getPixel(j, i));
                pixe = Color.red(bmp.getPixel(j - 1, i));
                medpix = ((pixd + pixm + pixe) / 3);

                //densidade de pretos (pelos menos 2 pretos de 3 considera preto)
                if (medpix <= 85) {
                    pixcont++;
                }
            }
            //densidade pixels por coluna
            lamb3[j] = (pixcont / (float) bmp.getHeight() * 100);

            if (lamb3[j-1] > 20 && (lamb3[j-1]-lamb3[j]) >= 0 ) {

                ind[k] = j-1;
                int aux = ind[k];
                Log.d(TAG, "ind["+k+"]: "+aux+" Lambida_atual["+j+"]: " + Float.toString(lamb3[j])+"%"+" Lambida_anterior["+(j-1)+"]: " + Float.toString(lamb3[j-1])+"%");
                k++;
                pixcont=0;
                break;

            } else{

                Log.d(TAG, "Lambida(coluna): " + Float.toString(lamb3[j]) + " %" + "Posição[J]: "+j);
                pixcont = 0;
            }

        }

        Log.d(TAG, "Fim");



        //linhas horizontais inferior
        for (i=bmp.getHeight()-2 ; i > 1 ; i--) {
            for (j=0; j<bmp.getWidth(); j++) {
                pixd = Color.red(bmp.getPixel(j,i-1));
                pixm = Color.red(bmp.getPixel(j,i));
                pixe = Color.red(bmp.getPixel(j,i+1));
                medpix = ((pixd + pixm + pixe) / 3);

                //densidade de pretos (pelos menos 2 pretos de 3 considera preto)
                if (medpix <= 85) {
                    pixcont++;
                }
            }

            //densidade pixels por linha
            lamb4[i] = (pixcont / (float) bmp.getWidth() * 100);

            if ( lamb4[i+1] > 20 && (lamb4[i+1]-lamb4[i]) >= 0 ) {
                ind[k] = i+1;
                int aux = ind[k];
                Log.d(TAG, "Valor do ind["+k+"]: "+aux+" Salvo - Lambida_atual["+i+"]: " + Float.toString(lamb4[i])+"%"+" Lambida_anterior["+(i+1)+"]: " + Float.toString(lamb4[i+1])+"%");
                k++;
                pixcont=0;
                break;
            } else{

                Log.d(TAG, "Lambida(linha): " + Float.toString(lamb4[i]) + " %" + "Posição[i]: "+i);
                pixcont = 0;
            }

        }

        Log.d(TAG, "Altura: " + bmp.getHeight() + " Largura: " + bmp.getWidth());

        int aux = ind[0];
        int aux1 = ind[1];
        int aux2 = ind[2];
        int aux3 = ind[3];

        Log.d(TAG, "Indice[esquerdo]: j-"+aux2);
        Log.d(TAG, "Indice[direito]: j-"+aux);


        Log.d(TAG, "Indice[superior]: i-"+aux1);
        Log.d(TAG, "Indice[inferior]: i-"+aux3);

        float ind_altura = aux3-aux1;
        float ind_lagura = aux-aux2;

        Log.d(TAG, "Indice[Altura]: i-"+ind_altura);
        Log.d(TAG, "Indice[Largura]: j-"+ind_lagura);

        float coeficiente = (float)(ind_lagura/(float)ind_altura);

        Log.d(TAG, "Coeficiente(L/H)= "+Float.toString(coeficiente));

        /*
        for(i=0 ; i<bmp.getHeight() ; i++) {
            for (j = 0; j < bmp.getWidth(); j++) {
                if (j == aux2 || j == aux) {
                    bmp.setPixel(j,i,0);
                }
                if (i == aux1 || i == aux3) {
                    bmp.setPixel(j,i,0);
                }

            }
        }
        */

        return bmp;
    }

}
