package io.github.dantetam.nemesisthirdtest;

import android.opengl.Matrix;

/**
 * Created by Dante on 6/12/2016.
 */
public class Camera {

    public float eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ;

    public Camera() {
        upX = 0; upY = 1; upZ = 0;
    }

    public void setViewMatrix(float[] viewMatrix) {
        Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
    }

    public void moveTo(float a, float b, float c) {
        eyeX = a; eyeY = b; eyeZ = c;
    }

    public void point(float a, float b, float c) {
        lookX = a; lookY = b; lookZ = c;
    }

    public void orient(float a, float b, float c) {
        upX = a; upY = b; upZ = c;
    }

}
