package uk.co.arcorelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.filament.View;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import uk.co.appoly.arcorelocation.LocationMarker;
import uk.co.appoly.arcorelocation.LocationScene;

public class MainActivity extends AppCompatActivity {
    private LocationScene locationScene;
    private LocationMarker locationMarker;
    private ArFragment arSceneView;
    private Renderable andyRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            arSceneView =(ArFragment) getSupportFragmentManager().findFragmentById(R.id.Arfragment);

        CompletableFuture<ModelRenderable> andy =
                ModelRenderable
                        .builder()
                        .setSource(this, R.raw.andy)
                        .build();
        CompletableFuture.allOf(andy)
                .handle(
                        (notUsed, throwable) ->
                        {
                            if (throwable != null) {
                                DemoUtils.displayError(this, "Unable to load renderables", throwable);
                                return null;
                            }

                            try {
                                andyRenderable = andy.get();

                            } catch (InterruptedException | ExecutionException ex) {
                                DemoUtils.displayError(this, "Unable to load renderables", ex);
                            }
                            return null;
                        });



    }
}
