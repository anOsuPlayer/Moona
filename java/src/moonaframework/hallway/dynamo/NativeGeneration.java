package moonaframework.hallway.dynamo;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.hallway.HallwayAccessException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Method;

public final class NativeGeneration {

	private final Method ref;
	
	public Method getSource() {
		return this.ref;
	}
	
	private List<String> imports;
	
	public List<String> getRequiredImports() {
		return this.imports;
	}
	
	private void addRequiredImport(String imp) throws NullArgumentException, IllegalArgumentException {
		if (imp == null) {
			throw new NullArgumentException("Unable to add a null String as an import.");
		}
		if (imports.contains(imp)) {
			throw new IllegalArgumentException("This import is already present.");
		}
		this.imports.add(imp);
	}
	public void addRequiredImports(String...imps) throws NullArgumentException, IllegalArgumentException {
		for (String imp : imps) {
			addRequiredImport(imp);
		}
	}
	
	public void setRequiredImports(List<String> imports) throws NullArgumentException {
		if (imports == null) {
			throw new NullArgumentException("Unable to use a null List<String> as source of imports.");
		}
		this.imports = imports;
	}
	
	private List<String> files;
	
	public List<String> getRequiredFiles() {
		return this.files;
	}
	
	private void addRequiredFile(String file) throws NullArgumentException, IllegalArgumentException {
		if (file == null) {
			throw new NullArgumentException("Unable to add a null String as a required file.");
		}
		if (files.contains(file)) {
			throw new IllegalArgumentException("This file is already present.");
		}
		this.files.add(file);
	}
	public void addRequiredFiles(String...files) throws NullArgumentException, IllegalArgumentException {
		for (String file : files) {
			addRequiredFile(file);
		}
	}
	
	public void setRequiredFiles(List<String> files) throws NullArgumentException {
		if (files == null) {
			throw new NullArgumentException("Unable to use a null List<String> as source of files.");
		}
		this.files = files;
	}
	
	private String implementation;
	
	public String getImplementation() {
		return this.implementation;
	}
	public void setImplementation(String code) throws NullArgumentException {
		if (code == null) {
			throw new NullArgumentException("Unable to build a NativeGeneration from a null code snippet.");
		}
		this.implementation = code;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof NativeGeneration ng) ? this.isSame(ng)
				&& this.imports.equals(ng.imports) && this.files.equals(ng.files)
				&& (this.implementation != null && ng.implementation != null) ? this.implementation.equals(ng.implementation) : true : false;
		
	}
	public boolean isSame(NativeGeneration ng) {
		return this.ref.equals(ng.ref);
	}
	
	public NativeGeneration(Method ref) throws NullArgumentException, IllegalArgumentException, HallwayAccessException {
		if (!Moona.enableHallwayAccess.evaluate()) {
			throw new HallwayAccessException();
		}
		if (ref == null) {
			throw new NullArgumentException("A link to a null Method Reference cannot be done.");
		}
		try {
			if (!ref.derive().getModifiers().isNative()) {
				throw new IllegalArgumentException("The given method must be native.");
			}
		}
		catch (UndefinedReflectionException ure) {
			ure.printStackTrace();
		}
		this.imports = new ArrayList<>(); this.imports.add("jni.h");
		this.files = new ArrayList<>();
		
		this.ref = ref;
		
		if (Moona.implicitDynamoInclusion.evaluate()) {
			Dynamo.add(this);
		}
	}
}
