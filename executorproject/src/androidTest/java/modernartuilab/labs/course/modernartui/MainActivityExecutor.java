package modernartuilab.labs.course.modernartui;

import android.app.Activity;
import com.robotium.recorder.executor.Executor;

@SuppressWarnings("rawtypes")
public class MainActivityExecutor extends Executor {

	@SuppressWarnings("unchecked")
	public MainActivityExecutor() throws Exception {
		super((Class<? extends Activity>) Class.forName("modernartuilab.labs.course.modernartui.MainActivity"),  "modernartuilab.labs.course.modernartui.R.id.", new android.R.id(), false, false, "1423188647970");
	}

	public void setUp() throws Exception { 
		super.setUp();
	}
}
