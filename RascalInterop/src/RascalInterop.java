import java.io.PrintWriter;
import java.net.URISyntaxException;

import org.rascalmpl.interpreter.JavaToRascal;
import org.rascalmpl.interpreter.load.IRascalSearchPathContributor;
import org.rascalmpl.interpreter.load.StandardLibraryContributor;
import org.rascalmpl.interpreter.load.URIContributor;
import org.rascalmpl.uri.URIUtil;

public class RascalInterop {
	public static void main(String[] args) throws URISyntaxException {
		JavaToRascal j2r = new JavaToRascal(new PrintWriter(System.out), new PrintWriter(System.err));

		IRascalSearchPathContributor modulePath = new URIContributor(
				URIUtil.correctLocation("file", "", "/repo/github/rascal-experiment/RascalInterop/src"));
		j2r.getEvaluator().addRascalSearchPathContributor(modulePath);
		j2r.getEvaluator().addRascalSearchPathContributor(StandardLibraryContributor.getInstance());

		try {
			System.out.println("Eval: import MyTest;");
			j2r.voidValue("import MyTest;", "stdin:///"); // Exception
			j2r.voidValue("hello();", "stdin:///");
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e.getClass() + " " + e.getMessage());
			e.printStackTrace();
		}

		try {
			j2r.voidValue("import IO;", "stdin:///"); // null pointer exception
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e.getClass() + " " + e.getMessage());
			e.printStackTrace();
		}

		// j2r.eval("writeFile(|cwd:///hello.txt|, \"Helloooo!\");");
	}
}