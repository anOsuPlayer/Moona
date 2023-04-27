package moonaframework.hallway.dynamo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.MethodProperty;

public final class Dynamo {

	private static final List<NativeGeneration> generations = new ArrayList<>();
	
	private static final String exportLocation = "lib/dynamo";
	
	private static final List<File> implementations = new ArrayList<>();
	
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
					
					String heading = "extern \"C\" JNIEXPORT " + translateType(mp) + " JNICALL Java_"
							+ generateName(gen) + " (" + getParameters(mp) + ")" + "{";
					
					writer.println(heading);
					
					writer.println(gen.getImplementation());
					
					writer.println("}");
					writer.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static long generateProof(NativeGeneration ng) {
		long proof = 0;
		for (byte b : ng.getImplementation().getBytes()) {
			proof += b;
		}
		return proof;
	}
	
	static String generateName(NativeGeneration ng) {
		return (ng.getSource().getDeclaringClass().getName() + "." + ng.getSource().getName()).replace('.', '_');
	}
	
	private static String generationID(NativeGeneration ng) {
		return generateName(ng) + "_" + generateProof(ng);
	}
	
	private static String translateType(MethodProperty mp) {
		String rettype = "";
		try {
			final Class<?> clazz = mp.getReturnType();
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
				return "jobject";
			}
		}
		catch (UndefinedReflectionException e) {
			e.printStackTrace();
		}
		
		return rettype;
	}
	private static String getParameters(MethodProperty mp) {
		return "JNIEnv* env";
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
