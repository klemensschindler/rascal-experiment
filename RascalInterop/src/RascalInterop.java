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

        IRascalSearchPathContributor modulePath = new URIContributor(URIUtil.createFileLocation("C:/Users/schindlerk/workspace/RascalInterop/src"));
        j2r.getEvaluator().addRascalSearchPathContributor(modulePath);
        
        try {
        	System.out.println("Eval: import MyTest;");
            j2r.eval("import MyTest;"); // null pointer exception
            j2r.eval("hello();");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getClass() + " " + e.getMessage());
        	e.printStackTrace();
        }

        j2r.getEvaluator().addRascalSearchPathContributor(StandardLibraryContributor.getInstance());
        try {
            j2r.eval("import IO;"); // null pointer exception
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getClass() + " " + e.getMessage());
        	e.printStackTrace();
        }
                
    	//j2r.eval("writeFile(|cwd:///hello.txt|, \"Helloooo!\");");
    }
}