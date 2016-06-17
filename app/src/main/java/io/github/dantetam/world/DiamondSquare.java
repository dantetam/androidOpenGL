package io.github.dantetam.world;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dante on 6/17/2016.
 */
public class DiamondSquare {

    public Random random;

    public DiamondSquare() {

    }

    public void seed(long s) {
        random = new Random();
        random.setSeed(s);
    }

    public static double[][] makeTable(double topLeft, double topRight, double botLeft, double botRight, int width)
    {
        double[][] temp = new double[width][width];
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < width; c++) {
                temp[r][c] = 0;
            }
        }
        temp[0][0] = topLeft;
        temp[0][width-1] = topRight;
        temp[width-1][0] = botLeft;
        temp[width-1][width-1] = botRight;
        return temp;
    }

    public double[][] dS(double[][] t, int sX, int sY, int width, double startAmp, double ratio)
    {
        while (true)
        {
            for (int r = sX; r <= t.length - 2; r += width)
            for (int c = sY; c <= t[0].length - 2; c += width)
            diamond(t, r, c, width, startAmp);
            if (width > 1)
            {
                width /= 2;
                startAmp *= ratio;
            }
            else
                break;
        }
        return t;
    }

    void diamond(double[][] t, int sX, int sY, int width, double startAmp)
    {
        t[sX + width/2][sY + width/2] = (t[sX][sY] + t[sX+width][sY] + t[sX][sY+width] + t[sX+width][sY+width])/4;
        t[sX + width/2][sY + width/2] += startAmp*(random.nextDouble() - 0.5)*2;

        if (width > 1) {
            square(t, sX + width/2, sY, width, startAmp);
            square(t, sX, sY + width/2, width, startAmp);
            square(t, sX + width, sY + width/2, width, startAmp);
            square(t, sX + width/2, sY + width, width, startAmp);
        }
    }

    void square(double[][] t, int sX, int sY, int width, double startAmp)
    {
        if (sX - width/2 < 0)
            t[sX][sY] = (t[sX][sY - width/2] + t[sX][sY + width/2] + t[sX + width/2][sY])/3;
        else if (sX + width/2 >= t.length)
            t[sX][sY] = (t[sX][sY - width/2] + t[sX][sY + width/2] + t[sX - width/2][sY])/3;
        else if (sY - width/2 < 0)
            t[sX][sY] = (t[sX][sY + width/2] + t[sX + width/2][sY] + t[sX - width/2][sY])/3;
        else if (sY + width/2 >= t.length)
            t[sX][sY] = (t[sX][sY - width/2] + t[sX + width/2][sY] + t[sX - width/2][sY])/3;
        else
            t[sX][sY] = (t[sX][sY + width/2] + t[sX][sY - width/2] + t[sX + width/2][sY] + t[sX - width/2][sY])/4;
        t[sX][sY] += startAmp*(random.nextDouble() - 0.5)*2;
    }

}
