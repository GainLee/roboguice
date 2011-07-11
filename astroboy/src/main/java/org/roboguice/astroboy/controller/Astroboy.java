package org.roboguice.astroboy.controller;

import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.util.Random;

// There's only one Astroboy, so make it a @Singleton.
// This means that there will be only one instance of Astroboy in the entire app.
// Any class that requires an instance of Astroboy will get the same instance.
// This also means this class needs to be thread safe, of course
@Singleton
public class Astroboy {

    // Because Astroboy is a Singleton, we can't directly inject the current Context
    // since the current context may change depending on what activity is using Astroboy
    // at the time.  Instead, inject a PROVIDER of the current context, then we can
    // ask the provider for the context when we need it.
    // Vibrator is bound to context.getSystemService(VIBRATOR_SERVICE) in RoboModule.
    // Random has no special bindings, so Guice will create a new instance for us.
    @Inject Provider<Context> contextProvider;
    @Inject Vibrator vibrator;
    @Inject Random random;

    public void say(String something) {
        // Make a Toast, using the current context as returned by the Context Provider
        Toast.makeText(contextProvider.get(), "Astroboy says, \"" + something + "\"", Toast.LENGTH_LONG).show();
    }

    public void brushTeeth() {
        vibrator.vibrate(new long[]{0, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50, 200, 50,  }, -1);
    }

    public String punch() {
        final String expletives[] = new String[]{"POW!", "BANG!", "KERPOW!", "OOF!"};
        return expletives[random.nextInt(expletives.length)];
    }
}
