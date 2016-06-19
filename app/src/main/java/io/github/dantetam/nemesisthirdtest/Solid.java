package io.github.dantetam.nemesisthirdtest;

import android.opengl.Matrix;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Dante on 6/12/2016.
 */
public class Solid {

    public static final float[] cubePositionData =
            {
                    // In OpenGL counter-clockwise winding is default. This means that when we look at a triangle,
                    // if the points are counter-clockwise we are looking at the "front". If not we are looking at
                    // the back. OpenGL has an optimization where all back-facing triangles are culled, since they
                    // usually represent the backside of an object and aren't visible anyways.

                    // Front face
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,

                    // Right face
                    1.0f, 1.0f, 1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    1.0f, -1.0f, -1.0f,
                    1.0f, 1.0f, -1.0f,

                    // Back face
                    1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,
                    1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, -1.0f,

                    // Left face
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
                    -1.0f, -1.0f, 1.0f,
                    -1.0f, 1.0f, 1.0f,

                    // Top face
                    -1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,
                    -1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, -1.0f,

                    // Bottom face
                    1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
                    1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, 1.0f,
                    -1.0f, -1.0f, -1.0f,
            };

    // R, G, B, A
    public final float[] cubeColorData =
            {
                    // Front face (red)
                    1.0f, 0.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f,
                    1.0f, 0.0f, 0.0f, 1.0f,

                    // Right face (green)
                    0.0f, 1.0f, 0.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,
                    0.0f, 1.0f, 0.0f, 1.0f,

                    // Back face (blue)
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,
                    0.0f, 0.0f, 1.0f, 1.0f,

                    // Left face (yellow)
                    1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 1.0f, 0.0f, 1.0f,
                    1.0f, 1.0f, 0.0f, 1.0f,

                    // Top face (cyan)
                    0.0f, 1.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 1.0f, 1.0f,
                    0.0f, 1.0f, 1.0f, 1.0f,

                    // Bottom face (magenta)
                    1.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.0f, 1.0f, 1.0f,
                    1.0f, 0.0f, 1.0f, 1.0f
            };

    // X, Y, Z
    // The normal is used in light calculations and is a vector which points
    // orthogonal to the plane of the surface. For a cube model, the normals
    // should be orthogonal to the points of each face.
    public static final float[] cubeNormalData =
            {
                    // Front face
                    0.0f, 0.0f, 1.0f,
                    0.0f, 0.0f, 1.0f,
                    0.0f, 0.0f, 1.0f,
                    0.0f, 0.0f, 1.0f,
                    0.0f, 0.0f, 1.0f,
                    0.0f, 0.0f, 1.0f,

                    // Right face
                    1.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f,
                    1.0f, 0.0f, 0.0f,

                    // Back face
                    0.0f, 0.0f, -1.0f,
                    0.0f, 0.0f, -1.0f,
                    0.0f, 0.0f, -1.0f,
                    0.0f, 0.0f, -1.0f,
                    0.0f, 0.0f, -1.0f,
                    0.0f, 0.0f, -1.0f,

                    // Left face
                    -1.0f, 0.0f, 0.0f,
                    -1.0f, 0.0f, 0.0f,
                    -1.0f, 0.0f, 0.0f,
                    -1.0f, 0.0f, 0.0f,
                    -1.0f, 0.0f, 0.0f,
                    -1.0f, 0.0f, 0.0f,

                    // Top face
                    0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f,

                    // Bottom face
                    0.0f, -1.0f, 0.0f,
                    0.0f, -1.0f, 0.0f,
                    0.0f, -1.0f, 0.0f,
                    0.0f, -1.0f, 0.0f,
                    0.0f, -1.0f, 0.0f,
                    0.0f, -1.0f, 0.0f
            };

    public FloatBuffer mCubePositions, mCubeColors, mCubeNormals;

    /**
     * How many bytes per float.
     */
    private static final int mBytesPerFloat = 4;

    public final float[] position = new float[3];
    public final float[] size = new float[3];
    public final float[] rotation = new float[4];
    public final float[] color = new float[4];

    public final int mCubePositionsBufferIdx;
    public final int mCubeNormalsBufferIdx;
    public final int mCubeColorCoordsBufferIdx;

    public Solid() {
        // Initialize the buffers.
        FloatBuffer[] floatBuffers = getBuffers(cubePositionData, cubeNormalData, cubeColorData);

        FloatBuffer cubePositionsBuffer = floatBuffers[0];
        FloatBuffer cubeNormalsBuffer = floatBuffers[1];
        FloatBuffer cubeColorCoordinatesBuffer = floatBuffers[2];

        // Second, copy these buffers into OpenGL's memory. After, we don't need to keep the client-side buffers around.
        final int buffers[] = new int[3];
        GLES20.glGenBuffers(3, buffers, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cubePositionsBuffer.capacity() * mBytesPerFloat, cubePositionsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cubeNormalsBuffer.capacity() * mBytesPerFloat, cubeNormalsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cubeColorCoordinatesBuffer.capacity() * mBytesPerFloat, cubeColorCoordinatesBuffer,
                GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        mCubePositionsBufferIdx = buffers[0];
        mCubeNormalsBufferIdx = buffers[1];
        mCubeColorCoordsBufferIdx = buffers[2];

        cubePositionsBuffer.limit(0);
        cubePositionsBuffer = null;
        cubeNormalsBuffer.limit(0);
        cubeNormalsBuffer = null;
        cubeTextureCoordinatesBuffer.limit(0);
        cubeTextureCoordinatesBuffer = null;

        /*mCubePositions = ByteBuffer.allocateDirect(cubePositionData.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubePositions.put(cubePositionData).position(0);

        mCubeColors = ByteBuffer.allocateDirect(cubeColorData.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeColors.put(cubeColorData).position(0);

        mCubeNormals = ByteBuffer.allocateDirect(cubeNormalData.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeNormals.put(cubeNormalData).position(0);*/
    }

    private FloatBuffer[] getBuffers(float[] cubePositions, float[] cubeNormals, float[] cubeColor) {
        // First, copy cube information into client-side floating point buffers.
        final FloatBuffer cubePositionsBuffer;
        final FloatBuffer cubeNormalsBuffer;
        final FloatBuffer cubeColorCoordinatesBuffer;

        cubePositionsBuffer = ByteBuffer.allocateDirect(cubePositions.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        cubePositionsBuffer.put(cubePositions).position(0);

        cubeNormalsBuffer = ByteBuffer.allocateDirect(cubeNormals.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        cubeNormalsBuffer.put(cubeNormals).position(0);

        cubeColorCoordinatesBuffer = ByteBuffer.allocateDirect(cubeColor.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        cubeColorCoordinatesBuffer.put(cubeColor).position(0);

        return new FloatBuffer[] {cubePositionsBuffer, cubeNormalsBuffer, cubeColorCoordinatesBuffer};
    }

    private final float[] modelMatrix = new float[16]; //Store in memory
    public float[] getModelMatrix() {
        if (modelMatrix == null) {
            prepareMatrices();
        }
        return modelMatrix;
    }

    //When any data of this solid is changed, this method should be called.
    public void prepareMatrices() {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, position[0], position[1], position[2]);
        Matrix.scaleM(modelMatrix, 0, size[0], size[1], size[2]);
        Matrix.rotateM(modelMatrix, 0, rotation[0], rotation[1], rotation[2], rotation[3]);
    }

    public void move(float a, float b, float c) {
        position[0] = a; position[1] = b; position[2] = c;
        prepareMatrices();
    }

    public void scale(float a, float b, float c) {
        size[0] = a; size[1] = b; size[2] = c;
        prepareMatrices();
    }

    public void rotate(float angle, float a, float b, float c) {
        rotation[0] = angle; rotation[1] = a; rotation[2] = b; rotation[3] = c;
        prepareMatrices();
    }

    public void color(float[] t) {
        if (t.length == 3)
            color(t[0], t[1], t[2], 1.0f);
        else if (t.length == 4)
            color(t[0], t[1], t[2], t[3]);
        else
            throw new IllegalArgumentException("Color argument is not of correct length");
    }
    public void color(float a, float b, float c, float d) {
        color[0] = a; color[1] = b; color[2] = c; color[3] = d;

        //cubeColorData = new float[36*4];
        for (int i = 0; i < 36; i++) {
            cubeColorData[i*4] = a;
            cubeColorData[i*4 + 1] = b;
            cubeColorData[i*4 + 2] = c;
            cubeColorData[i*4 + 3] = d;
        }
        mCubeColors.clear();
        mCubeColors = ByteBuffer.allocateDirect(cubeColorData.length * mBytesPerFloat)
            .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubeColors.put(cubeColorData).position(0);

        prepareMatrices();
    }

    public float angle() {
        return rotation[0];
    }
    public void rotateAngle(float f) {
        rotation[0] = f;
        prepareMatrices();
    }

}
