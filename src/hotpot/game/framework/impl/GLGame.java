package hotpot.game.framework.impl;

import hotpot.game.framework.Audio;
import hotpot.game.framework.FileIO;
import hotpot.game.framework.Game;
import hotpot.game.framework.Input;
import hotpot.game.framework.Screen;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class GLGame extends Activity implements Game, Renderer {
    enum GLGameState {
        Initialized, Running, Paused, Finished, Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    long startTime = System.nanoTime();
    WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // タイトルバーを消して全画面使用する
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);

        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glGraphics.setGL(gl);

        synchronized (stateChanged) {
            if (state == GLGameState.Initialized) {
                screen = getStartScreen();
            }
            state = GLGameState.Running;
            screen.resume();
            startTime = System.nanoTime();
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLGameState state = null;
        synchronized (stateChanged) {
            state = this.state;
        }

        if (state == GLGameState.Running) {
            // TODO ここから　page 276
        }
    }
}
