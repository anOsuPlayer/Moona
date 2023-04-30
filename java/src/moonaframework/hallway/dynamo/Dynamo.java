package moonaframework.hallway.dynamo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.flare.MethodProperty;

public final class Dynamo {

	private static final List<NativeGeneration> generations = new ArrayList<>();
	
	private static final String exportLocation = "lib/dynamo";
	
	private static final List<File> implementations = new ArrayList<>();
	
	private static final int DYNAMO_OK = 0;
	
	public static void loadGenerations() throws MoonaHandlingException {
		if (Moona.isOn()) {
			throw new MoonaHandlingException("Dynamo Generations are disabled after Moona.isOn() invocation.");
		}
		
		for (NativeGeneration gen : generations) {
			final String genID = generationID(gen);
			
			File generation = new File(exportLocation + "/" + genID + ".dll");
			if (!generation.exists()) {
				File sourceCode = new File(exportLocation + "/" + genID + ".cpp");
				try {
					sourceCode.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				PrintStream writer;
				try {
					writer = new PrintStream(sourceCode);
					
					for (String incl : gen.getRequiredImports()) {
						writer.println("#include <" + incl + ">");
					}
					for (String file : gen.getRequiredFiles()) {
						writer.println("#include \"" + file + "\"");
					}
					
					MethodProperty mp = gen.getSource().derive();
					
					String heading = "extern \"C\" JNIEXPORT " + translateType(mp.getReturnType()) + " JNICALL Java_"
							+ generateName(gen) + " (" + getParameters(mp) + ")" + "{";
					
					writer.println(heading);
					writer.println(gen.getImplementation());
					writer.println("}");
					
					compile(genID);
					
					writer.close();
				}
				catch (IOException | UndefinedReflectionException | InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			System.loadLibrary("dynamo/" + genID);
		}
		
		Runtime.getRuntime().gc();
	}
	
	private static void compile(String genID) throws CompilationError, InterruptedException, IOException {
		String[] shell = new String[2];
		if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
			shell[0] = "cmd.exe"; shell[1] = "/c";
		}
		else {
			shell[0] = "/bin/sh"; shell[1] = "-c";
		}
		
		ProcessBuilder firstComp = new ProcessBuilder(shell[0], shell[1], "g++", "-c", "-std=c++2a", "-I\"%JAVA_HOME%\\include\"", "-I\"%JAVA_HOME%\\include\\win32\"",
				new File(exportLocation + "/" + "*.cpp").getAbsolutePath());
		Process p1 = firstComp.start();
		
		if (p1.waitFor() != DYNAMO_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
			String log = "", line;
			while ((line = br.readLine()) != null) {
				log += line + "\n";
			}
			br.close();
			System.err.println(log);
			
			throw new CompilationError("First Phase failed: defective code.");
		}
		
		ProcessBuilder buildLib = new ProcessBuilder(shell[0], shell[1], "g++", "-shared", "-o", exportLocation + "/" + genID + ".dll", "*.o");
		Process p2 = buildLib.start();
		
		if (p2.waitFor() != DYNAMO_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
			String log = "", line;
			while ((line = br.readLine()) != null) {
				log += line + "\n";
			}
			System.err.println(log);
			br.close();
			
			throw new CompilationError("Second Phase failed: unable to compile .dll file.");
		}
		
		File source = new File(exportLocation + "/" + genID + ".cpp");
		File comp = new File(genID + ".o");
		
		source.deleteOnExit();
		comp.delete();
	}
	
	private static long generateProof(NativeGeneration ng) {
		long proof = ng.getImplementation().hashCode();
		for (String incl : ng.getRequiredImports()) {
			proof += incl.hashCode();
		}
		for (String file : ng.getRequiredFiles()) {
			proof += file.hashCode();
		}
		return (proof < 0) ? ((long)(Integer.MAX_VALUE)) * 2 + proof + 1 : proof;
	}
	
	static String generateName(NativeGeneration ng) {
		return (ng.getSource().getDeclaringClass().getName() + "." + ng.getSource().getName()).replace('.', '_');
	}
	
	private static String generationID(NativeGeneration ng) {
		return generateName(ng) + "_" + generateProof(ng);
	}
	
	private static String translateType(Class<?> clazz) {
		String rettype = "";
		if (clazz.isPrimitive()) {
			rettype = clazz.getSimpleName();
			if (!rettype.equals("void")) {
				rettype = "j" + rettype;
			}
			
			if (clazz.isArray()) {
				rettype += "Array";
			}
		}
		else if (clazz.isArray()) {
			if (clazz.componentType().isPrimitive()) {
				return "j" + clazz.componentType().getSimpleName() + "Array";
			}
			return "jobjectArray"; 
		}
		else {
			return (clazz.getSimpleName().equals("Class")) ? "jclass"
					: (clazz.getSimpleName().equals("String")) ? "jstring" : "jobject";
		}
		
		return rettype;
	}
	private static String getParameters(MethodProperty mp) {
		String params = "JNIEnv* env";
		
		try {
			params += (mp.getModifiers().isStatic()) ? ", jclass thisClass" : ", jobject thisObj";
			final List<Parameter> ps = mp.getParameters();
			
			for (int i = 0; i < ps.size(); i++) {
				params += (", " + translateType(ps.get(i).getType()) + " arg" + i);
			}
			
			return params;
		}
		catch (UndefinedReflectionException ure) {
			ure.printStackTrace();
		}
		return null;
	}
	
	public static void add(NativeGeneration ng) throws NullArgumentException, DynamoAccessException, DuplicateGenerationException {
		if (!Moona.enableDynamo.evaluate()) {
			throw new DynamoAccessException();
		}
 		if (ng == null) {
 			throw new NullArgumentException("Unable to add a null NativeGeneration to the Dynamo.");
 		}
		if (has(ng)) {
			throw new DuplicateGenerationException(ng);
		}
 		
 		generations.add(ng);
	}
	public static void remove(NativeGeneration ng) throws NullArgumentException, DynamoAccessException {
		if (!Moona.enableDynamo.evaluate()) {
			throw new DynamoAccessException();
		}
 		if (ng == null) {
 			throw new NullArgumentException("Unable to remove a null NativeGeneration from the Dynamo.");
 		}
 		if (!generations.contains(ng)) {
 			throw new IllegalArgumentException("This NativeGeneration is not present in the Dynamo.");
 		}
 		generations.remove(ng);
	}
	
	public static void clear() {
		if (!Moona.enableDynamo.evaluate()) {
			throw new DynamoAccessException();
		}
		generations.clear();
	}
	
	public static boolean has(NativeGeneration ng) {
		for (NativeGeneration gen : generations) {
 			if (gen.isSame(ng)) {
 				return true;
 			}
 		}
		return false;
	}
	
	static {
		implementations.addAll(Arrays.asList(new File(exportLocation).listFiles()));
	}
	
	private Dynamo() {
		
	}
}
