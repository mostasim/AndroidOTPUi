package tech.mbsoft.otpnumberfocusui;

import android.content.Context;

import androidx.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    Context appContext;
    @Before
    public void setUp() throws Exception {
        // Context of the app under test.
       appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("tech.mbsoft.otpnumberfocusui", appContext.getPackageName());
    }

    @Test
    public void testOtpBoxes(){

    }
}