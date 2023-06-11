# CHANGELOG

## early_dev-0.0.6 - The Hallway Update - Part I!

* **Added Hallway Interface:** It's a set of features that allows Moona to interact with other languages. In this update, compatibility between Java and C++ was made possible; in the future, when Moona expands to new languages, new interoperability features will come.
* **Added Dynamo System:** This system allows a Java user to link _native methods_ without the need for creating .dll libraries manually. It utilizes _NativeGenerations_ to link to a Method Reference (which points to a native Java method) and makes automatic code generation possible. One needs to specify an _implementation_, which is the C++ raw code, as well as the _required #include clauses_: the Dynamo Handler will automatically generate the .dll library and link it to Java.
* **Added moonaframework.hallway package:** It stores all the newly added Hallway-related features.
* **Added Dynamo Class:** The Dynamo Class is the Object Handler that manages NativeGenerations and creates the native .dll libraries. Other than taking care of generating the native code, it will also deal with _deleting unnecessary files_. In case a file was the result of an aborted compilation or in case it was no longer needed, the Dynamo will take care of it.
* **Added NativeGeneration Class:** A NativeGeneration is a particular Object that allows a native Java method to receive a _full implementation_ without dealing with separate C++ files. It will need a String, containing _C++ code_ as _implementation_, as well as _a list of all the required #include clauses_. NativeGenerations are automatically handled by the Dynamo (if the _Moona.implicitDynamoInclusion_ setting is disabled) and they each have _one and only one corresponding .dll library_. Said library will be deleted in case the NativeGeneration's implementation in the code went missing: this means that the Dynamo will maintain a NativeGeneration's .dll if and only if _the NativeGeneration keeps being a part of the code_. In case a NativeGeneration ended up being _defective_, the deletion of aborted compilation product will be deleted automatically.
* **Added Generated Annotation:** This optional Annotation has to be used to mark _methods generated with the Dynamo_.
* **Added Moona.enableDynamo Setting:** This setting establishes whether or not the Dynamo is active. This is needed since the Dynamo will perform a routine task every time the Moona.init() method is called, for this reason, this might end up resulting in performance issues if not needed.
* **Added Moona.enableHallwayAccess Setting:** For the same reasons listed above, this setting states if Moona is allowed to interact with the Hallway and, consequently, with its other languages counterparts.
> NOTE: This Setting has been added to both Java and C++.
* **Added Moona.implicitDynamoInclusion Setting:** States whether or not NativeGenerations should be automatically added to the Dynamo or not. This is especially useful when _dealing with multiple NativeGenerations pointing to the same native method_: In case an implementation was needed on top of another for whatever reason, this will be a good way of deciding without the Dynamo interfering.
* **Added DynamoAccessException Class:** Gets thrown in case some Dynamo-related feature was called when the _Moona.enableDynamo Setting_ is disabled.
* **Added CompilationError Class:** Gets thrown in case the Dynamo tried to generate a .dll library from a NativeGeneration _containing defective C++ code_. With it, a detailed _g++ error log_ will follow to enlighten you about what was wrong with said code.
* **Added DuplicateGenerationException Class:** This Exception will be thrown in case multiple NativeGenerations pointing to the same native method were to be added to the Dynamo. This does not apply in case the _Moona.implicitDynamoInclusion_ is disabled
* **Added Moona.nativeInit() Method:** It initializes the C++ part of Moona. This only gets called if the _Moona.enableHallwayException Setting_ is enabled; it's called alongside the _Moona.init() method_.
* **Added Moona.interrupt() Method:** It's a method that is automatically called as soon as _the program ends_. It's a set of cleanup features that are required to not make Moona a giant living memory leak. If invoked when the program hasn't ended its lifecycle yet, it will no longer be possible to call it twice (and it will not be called once the program ends).
* **Added Moona.nativeInterrupt() Method:** This method does the same thing as the _Moona.interrupt() method_ but on the native side of Moona. This only gets called if the _Moona.enableHallwayException Setting_ is enabled.
* **Added Endexpr Class:** Endexpressions are snippets of code that get executed when the _Moona.interrupt() method_ is called.
* **Added MoonaSetting Class:** MoonaSettings are a special kind of settings that _can be modified only before the Moona.init() method's invocation_.
> NOTE: MoonaSettings have been added to both Java and C++.
* **Added Cpp Class:** Inside the .hallway package, it is required to handle any _C++ related interactions_. This does not mean it's an equivalent of the _Java's Unsafe Class_, but rather, it focuses on minor things, such as _telling you what C++ version you're using_ or _stating where the JNI libraries reside_ (which sounds, and actually is, more useful than the latter).
* **Added Address Class:** It contains a long that points to a _memory address_. As of now, it's not used.
* **Added Moona.moonastack:** It is a _java.lang.foreign.MemorySession_ instance that is entirely handled by Moona. It stays alive until the framework does, and it will be automatically opened and closed.
* **Added .asOptional Method in SuperObject Class:** Casts a SuperObject\<T\> into an Optional\<T\>.
* **Added C++ exception Assets:** A brand new set of Exceptions were added to the C++ side of Moona.
> NOTE: All the newly added Exceptions will be implemented only in _Moona-related situations_. Do not expect regular C++ to throw these bad boys on its own (would be way too good).
* **Added Exception C++ Class:** Acts as a base class for all the other Moona Exceptions.
* **Added BadCastException C++ Exception:** Gets thrown when a bad cast occurs.
* **Added IllegalArgumentException C++ Exception:** In a very Java-fashion, gets thrown when an _unworty argument_ is fed into an unsuspecting method.
* **Added IndexOutOfBoundsException C++ Exception:** Gets thrown when, guess what, a given index goes out of bounds for a certain context.
* **Added NullPointerException C++ Exception:** Gets thrown when a _nullptr_ goes where it shouldn't.
* **Added UnsupportedOperationException C++ Exception:** In case a certain operation was restricted, this bad boy gets thrown.
* **Added C++ hallway Assets:** All the Hallway related assets will be stored in the newly added _cpp/hallway_ folder.
* **Added JVM C++ Class:** Represents a Java Virtual Machine in C++. Can either come from a _Java initialization_ or a _C++ initialization_ of Moona. It contains a JNIEnv pointer, which is the root of all the JNI-related actions that take place during runtime.
* **Added JVMException C++ Exception:** Gets thrown when a Java-related exception occurs. Specifically, this exception will be thrown both when _a JVM creation fails_ or when _Java Method Calls rise Java Exceptions_. Together with the JVMException, in case the JNI detected Java Exceptions being thrown, a thorough log of the latter will be displayed.
> NOTE: Resides in the _cpp/hallway_ folder.
* **Added cppimpl C++ Header and Implementation:** Serves both as a _test file_ and as a place where to put _native methods implementations_ for the Java Cpp class.
* **Added javanotation.hpp Header:** Contains a series of _#define constants_ which are useful when writing native implementations for native Java Methods.
* **Added JavaPackage C++ Wrapper Class:** Serves as a Wrapper Class for a Java Package. Can be used to _get classes_ which are (supposedly) inside of it.
* **Added JavaClass C++ Wrapper Class:** Mimics a Java Class. It contains a _jclass JNI pointer_ stored as _WeakGlobalReference_. It's the base source to derive _static and non-static methods and fields_. Can also be used to directly call _JavaStaticMethod(s)_ and to edit/view _JavaStaticField(s)_.
* **Added NoSuchClassException C++ Exception:** Gets thrown when trying to get a Java Class which doesn't exist.
* **Added JavaObject C++ Wrapper Class:** Resembles a Java Object. Can be constructed from both a _JavaConstructor_ or _an already existing jobject JNI pointer_. It's the base to _call regular methods_ and access or edit _non-static fields_.
* **Added JavaObjectArray C++ Wrapper Class:** Resembles a Java Object[] and can be edited with the "[]" operator.
* **Added JavaObjectArrayElement C++ Class:** It's the Object returned from the "[]" operator of the JavaObjectArray Wrapper Class. Serves as a vessel to edit an Object inside of a JavaObjectArray.
* **Added JavaArray Wrapper Class:** By itself, the JavaArray Class is a _template_ which acts as an _abstract super-class_ for all the other _primitive Array Wrapper Classes_, which wrap a _boolean, short, char, int, long, float and double_ primitive Java Array.
> NOTE: A JavaObjectArray is NOT a JavaArray!
* **Added JavaMethod and JavaStaticMethod Wrapper Classes:** They resemble, respectively, a _Java method and static method_. They store a _jmethodID_ and can be called on _JavaObjects_ and _JavaClasses_, respectively.
* **Added NoSuchMethodException C++ Exception:** Gets thrown when trying to get a Java Method which doesn't belong to a certain Class.
* **Added JavaField and JavaStaticField Wrapper Classes:** Much like their Method counterparts, this Wrappers represent _Java fields and static fields_. They store a _jfieldID_ and can be accessed on _JavaObjects_ and _JavaClasses_, respectively.
* **Added NoSuchFieldException C++ Exception:** Gets thrown when trying to get a Java Field which doesn't belong to a certain Class.
* **Added JValue Wrapper Class:** Wraps a _jvalue JNI pointer_ making it _less painful_ to utilize.
* **Added JavaString Wrapper Class:** Holds a _const char*_ and exports it as a _jstring JNI pointer_ when needed. This design choice was taken because editing already existing jstrings is quite slow compared to editing a _const char*_.
* **Added javasignature C++ Header and Implementation:** These files store a series of Objects which can be used to _generate Java Methods Signatures_ without having to remember how to write them by heart. There is the: _PureSignature Class_, acting as a solid base for all the other Signatures (Except _Field_, _Method_ and _ConstructorSignatures_); _Signature Class_, representing the basic _primitive types signatures_; _ObjectSignature Class_, representing the signature of any _Java Object_; _ArraySignature Class_, which can generate an array of any other Signature or ObjectSignature; _ComposedSignature Class_, which represents an _integral signature_ for _method and constructor parameters_; _MethodSignature, ConstructorSignature and FieldSignature Classes_, which represent... well read their names and you'll find out.
* **Added cpp/util Folder:** Stores all the util features of Moona's C++ side.
* **Added LICENSE.md File:** Finally Moona has a license too.
* **Added base/notation.hpp C++ Header:** The base/notation.hpp header will provide the standard notation for all the files concerning the C++ side of Moona.
* **Improved .evaluate() Method in Setting Class:** Better handles autoboxing of booleans, avoiding the use of wrapper classes in the process.
* **Removed cpp/contitions Assets:** To be re-implemented.
* **Removed Unused Imports:** Across the framework... somewhere.
* **Removed Useless Casts:** in Benchmark Java Class.

## early_dev-0.0.5.1 - The SuperObjects Update!

* **C++ Features:** About the _actual_ C++ features I've added, I have a disclaimer to do.. _THEY ARE VERY UNSTABLE_. I am not yet 100% confident on how to make good code in C++ (at least not as much as I am in Java) and this makes things... a bit radioactive, to say the least.
* **Added C++ Base Framework Structure:** The first thing I did was creating a port for _the Moona class_ and _the Benchmark class_. Their Java counterparts make up for a sturdy base for Moona, so I thought that replicating them in C++ would have been ideal.
> NOTE: Keep in mind that all the C++ "Java-like" features I'll mention throughout this changelog are just _mere conceptual replicas_. Do not expect to find them _exactly_ how you would in Java.
* **Added C++ Object's System:** Another thing I was really trying hard to replicate were *Java's Objects*. Since this framework is all about OOP, having a good Object System is crucial, to my advise. I've created such a system heavily relying on _C++ templates_.
* **Added C++ Base and Entity Classes:** Entities are the base for C++ Objects, and every Entity extends a Base. Make any user-defined class extend the Entity class in order for it to become one and, as type arguments, _always_ put the class itself as _the first one_ and all the other Entities you wish it to extend _as second, third and so on_. Entities are non-virtual base-classes which allow for some basic action like _type derivation_ from objects themselves and an _instanceof-like operation_.
* **Added C++ Objects:** They differ from Entities because of the fact that they're _virtual classes_, hence their weight rises up by 8 bytes in memory, in order to be able to store their virtual table. For implementing a class in Moona, you can either make it extend Entity or Object (the way you do that mimics the method described in the previous point). Objects are close replicas of the ones in Java, they have all they need, except for the waiting and notifying operations.
* **Added C++ Type Class:** This class' purpose is nothing but to emulate what Java's Class\<?\>... _class_... does: Type-related operations are similar to those operations you'd perform by using them. They're also used for type comparisons between Objects.
* **Added C++ Moona's Concepts:** I surely didn't hold myself back when it came to use all the wonderful features C++ has to offer in order to make the most out of Moona. Concepts were, without a doubt, one of those features: I used them to make some basic concepts to recognize Objects and Entities.
* **Added C++ Assertions and Conditionals:** This was kinda the point where I stopped making weird experiments with C++ and where I went back to Java. They're basically an attempt to mimic Moona's Conditionals in Java, as simple as it sounds.
* **Added MirrorFilters:** About Moona's Java side, big news for Reflections once again. Since the Mirror is the place where _all the Reflections_ are stored, I thought that I had to add some kind of way to extract them from the there. Here's where MirrorFilters come in place, there are dedicate methods in the Mirror which return one of them containing all the Reflections of a certain type (for example, if you wanted to pull _all the Method References_, there's the method to do that now!). There is also the possibility to _furtherly filtrate_ Reflections on a Java Stream-like fashion, searching by name, arguments etc...
* **Added Reflections Filters:** Despite the intricate name, they're nothing but _super-interfaces_ of certain Reflections which hold a certain method (for example, the _Nominal Filter_ implements the _.getName() method_). They're needed by MirrorFilters to furtherly filtrate a stream of Reflections if needed.
* **Added Unsafe Reflection Loading:** Moona now has a new Setting, the _unsafeReflectionLoading_. Enabling this setting will make so that the mirror will _ignore Undefined Reflections_. This means that no exceptions will be thrown during the loading phase, however _the Reflection will remain there_ and _it will remain Undefined_.
* **Added .safeReflect() and .safeEval() methods for Reflections:** They're the safe version of _.reflect()_ and _.evaluate()_ methods, and they _will not throw any exception_ in case the reflection process failed. They're used by the Mirror when it comes to Unsafe Refleciton Loading.
* **Added .isDeclared() Method to all the Class Member References:** Constructor, Method and Field References now feature this new method in order to understand whether or not the referenced class member is _explicitly declared_ or not (e.g. defaulted interfaces methods do not count as explicitly declared methods). It is also possible, via constructor, to make the Reference search for _an explicitly declared member_ or not (by default, it will search non-declared members, too).
* **Added .hasAnnotation() Method to Annotated Flare:** It returns _true_ in case the given Annotation is present inside the Annotated Flare calling this method.
* **Added CRITICAL MoonaHandlingException:** As the name suggests, this particular exception gets thrown when _NO EXCEPTIONS SHOULD BE THROWN._
* **Added SuperObjects:** A SuperObject is nothing but _a buffed-up Java's Optional_. Despite the resemblance, though, it's not quite the same thing. Not all the features an Optional has have been ported to SuperObjects, but they still feature a great number of interactions. Many instructions related to a certain Object can be concatenated using the numerous methods this feature offers.
* **Added SuperObject Interface:** SuperObjects are not created using classes, in fact they consist in _pure abstraction_. Don't get me wrong: they _have an actual class implementing the related interfaces_, but it's not accessible. However, the SuperObject interface can let you create a new SuperObject via the _.of() method_.
* **SuperObjects States:** A SuperObject enters a certain State when _certain methods are called_. For example, when creating a SuperObject, it is in its _Default State_ but, as soon as some conditional method is called (e.g. _.ifPresent()_ or _.ifTrue()_), it moves into it _Conditional State_, where you can either concatenate new statements to it or end the conditional chain. The States are many and are each represented by a certain interface, which, thanks to the immense power of _information hiding_ lets you access the right methods of the base classes at the right time. This fine system was created in order to _not cause non-sensed instruction chains_ (for example something like _yourSO.and().or().ifPresent().orElse().and()_... _non-sensed_).
* **Improved Agent.flush() Method:** Now checks whether or not the toAdd and toRemove Lists actually have elements inside of them before entering .forEach() loops.
* **Fixed Processor's .initializer() and .ender() Methods:** One of the main reasons behind the whole Reflection Rework was making this system work properly. This process detects _whether or not the Timeless Annotation is present on a Process' method_... so yes, the Timeless Annotation now works!
* **Fixed Mirror.totalReflections() Method:** Now returns the number of _all_ the Reflections contained inside of it, Flares included.
* **Fixed Exception Handling for Reflections:** One of the key changes in this Update is that _Reflections now have a proper exception handling system_. Previously, all the potential UndefinedReflectionExceptions were _reduced to RuntimeExceptions_, therefore excluding the strict necessity of try-catch blocks to handle potential flaws. But since I finally learned that try-catch blocks are actually pretty and useful I decided that _it was about time to stop adopting that ratherly dangerous method_ and to _explicitly declare "throws UndefinedReflectionException"_ on every method that used that stupid trick before... _SAFETY!_
* **Fixed .getTarget() Method for some References:** Constructor, Method and Field References now return their declaring class instead of their reflected value.
* **Removed Initializer Block from Constexpr Class:** It was borderline confusing, so it got _obliterated_ and all of its content was moved to the constructor.
* **Removed Presence Checks from the Mirror:** Since each Reflection is unique, there is no need to throw exceptions when including them in the Mirror if they're already there. The same thing cannot be done for all the other Object Handlers since the Mirror is the only one which, sometimes, _automatically manages Reflections which enter and exit it_; this ambiguity is unique to it and, therefore, the risk of useless exception-throws is higher compared to the rest.
* **Removed Mirror.dequeue() Method:** It was... useless.
* **Removed Serials and Naturals:** With a bit of pain in my heart, I had to remove those OG features due to their... _uselessness_. They never had an actual use inside the framework and their presence was starting to bother, since they were just _additional weight for Moona's Objects_ and since I had to plan every new feature accordingly, since _all the Moona Objects_ had to be their sub-types. 

## early_dev-0.0.5 - The Reflection Rework Update!

* **Improved Reflection Class:** Generally speaking, Reflections have been overhauled. They changed a lot and their new functionalities will be explained further in the changelog.
* **Declaration and Reflection Processes:** Declaring a Reflection (with the "new" operator) is the regular procedure you'll follow in order to instantiate a regular object. However, Reflections _are not regular objects_: a Reflection could be declared _under any circumstance_, this meaning that, until the Reflection Process doesn't happen, any Reflection (even if non-sensed) could exist. _Reflecting_ a _Reflection_, however, means _evaluating its value based on its target_, meaning that, if the attributes of a Reflection don't make sense, the Reflection Process will inevitably fail. 
* **Undefined Reflections:** Since _declaration_ and _reflections_ are two different and non-related processes, it would be good to point out what would happen _if either should fail_. The outcomes of a failed declarations would be immediately evident, as constructors themselves would notify the presence of wrong arguments. On the other hand, the reflection process is different. This failure isn't notified straight away: you'll have to _call the .reflect() method_ in order to find out. Failed Reflections are called _Undefined Reflections_.
* **Added UndefinedReflectionException:** Notifies a failed reflection attempt. Reflections fail to generate if _the given arguments don't make sense_: to put it simply, trying to search for _a record component_ in an _interface_ would end up badly... or, more simply, giving _a wrong method's name_ or _giving it wrong arguments_ and so on, you got the picture.
* **Reflections and Exceptions:** The only _direct Exception_ (which needs _try-catch_) is the UndefinedReflectionException, which is only thrown by the _.reflect()_ and _.evaluate()_ methods. As for all those otehr methods which _need to reflect Reflections in their process_, UndefinedReflectionExceptions are turned into MoonaHandlingExceptions, not forcing the user to handle them (since.. there _should not_ be the necessity).
* **Added References:** A Reference is a Reflection which targets an element of the java.lang.reflect package. It's a sealed class which only allows a few subtypes. It allows a type argument which must extend java.lang.reflect.AnnotatedElement; this is because that class acts as a super-interface of most of the classes in java.lang.reflect, allowing for an almost optimal inheritance-based structure for all the possile References that could be needed (there are a few exceptions, though).
* **Added Flares:** A Flare is nothing but a Reflection _containing other Reflections_. It allows a type argument such that a Flare\<T\> acts as a container for *any Reflection of type T* (with T having Reflection\<?\> as an upper bound). Flares are mostly used with the derivation process, which will be explained later. To put it briefly, a derived Flare is nothing but _a Flare containing Reflections related to a certain Reference_.
* **Improved the Mirror Class:** This class went through a very long process of rework, which ended up making it 100 times _less boilerplate_ than it was before. Instead of featuring an endless series of methods to get the Reflections you want from its IshMap, it became more of a "passive Object Handler". You'll rarely need to interact with the Mirror from now on, since it will automatically perform all the operations needed for Reflections to work properly completely automatically.
* **Introduced Value Extraction:** This concept is a way of automatically managing duplicated Reflections. If a Reflection is generated but one exactly equal to it already resides in the Mirror, their values get immediately matched. Keep in mind that _creating multiple equal Reflections_ will not stack them up in the Mirror: since Reflections are pure informations, there is no need to have a double entry inside of the Mirror.
* **Added Setting Class:** the Setting Class is a class located inside the base package which extends Status\<Boolean\>. Settings can be either _enabled or disabled_ with the related methods, or they can be _locked_ by Moona when it decides not to make their value change anymore. Settings are usually a pretty workraound to avoid useless incapsulation, since using them as public static final fields will be the same thing, _and_ it will also be prettier. Settings are the base of many of the new concepts introduced ahead.
* **Introduced Derivation:** Derivation is a process exclusive to References and it consists of _creating a Flare containing Reflections related to them_. An example of related Reflections could be, for example, deriving a Method Reference's _Modifier Reflection and Parameter References_, which will be contained in a specialized Flare which will be described later.
* **Annotation Derivation:** The process of Derivation doesn't stop to just creating a Flare related to a certain Derivable, but it also allows for _an Annotated Flare_ (which you'll meet later) to be generated. This extra step isn't distinguished from regular derivation and it's considered part of the process. The method which allows the Annotated Flare to be generated is the _.getAnnotated() method_ (described later).
> NOTE: Further references to "the derivation process" will refer to _both_ the derivation and the annotation derivation process, since, as mentioned, they're considered part of the same procedure.
* **Introduced Automatic Reflections:** Auto-Reflections, for short, are a clever mechanism which allows Reflections _to be automatically initialized_ at the same time the framework itself is started. This process is triggered if and only if the _Moona.autoReflections setting is enabled_, otherwise the privilege of initializing/adding to the Mirror Reflections will remain to the user. They're enabled by default and they behave differently if they're triggered _before or after calling the Moona.init() method_.
* **Introduced Strict Context:** The Strict Context is a protected static Setting inside the Reflection class which _tells whether or not it's the case to bypass Auto Reflections_. If Auto Reflections are not activated and a Reference of some sort gets derived, the derivation process might lead to _the creation of more Reflections_, which would then need to be evaluated as soon as the user needs them, causing performance issues (this doesn't apply to _every derived Reflection_, but some of them are really heavy to evaluate). Usually, the Strict Context gets triggered as soon as a Flare is being reflected: if Auto Reflections are enbled, there will be no need to worry, otherwise, the Strict Context will provide a way for those newly generated Reflections to be automatically reflected, to maximize efficiency.
* **Added Derivable Interface:** By definition, a Derivable declares the _.derive() method_ and the _.getAnnotated() method_ either overridden or deadlined. Derivables are mostly related to References, as they're the only Reflections which are required to be derived. This interface exists to allow _other non-Reference types_ to have access to the derivation process (since inheritance forced things to be different for certain References, I had to fix that issue with this interface).
* **Reflections Before Moona.init() Call:** If a Reflection is instantiated before the call to the Moona.init() method it will be _automatically processed_ if the Moona.autoReflections setting is _enabled_ and also _automatically derived_ if the Moona.autoDeriveReferences setting is _enabled_.
> NOTE: All the Moona Settings are bypassed in case the Strict Context is active: when generating Flares as a consequence of derivation, all the newly generated Reflections will automatically be processed _and_ derived.
* **Reflections After Moona.init() Call:** Declaring Reflections after the Moona.init() method is called makes things follow a different path: they will be _automatically processed_ if the Moona.autoReflections setting is _enabled_, but they'll only be derived if the Moona.deriveWhenInitialized setting is _enabled_ (that setting also requires the _Moona.autoDeriveReferences_ to be _enabled_). This slight difference makes so that Reflections generated in the Post-Init Phase are _more manageable_ than those generated before: allowing the user to manage loading times and potentially performance-heavy tasks as they please is a crucial aspect of this phase.
> NOTE: Not taking advantage of the Pre-Init Phase isn't a good idea.
* **Added Type References:** They target a java class and can be derived, generating a _TypeContent Flare_.
* **Added TypeContent Flares:** As mentioned, they contain all the informations related to a java class, them being _mehods, constructors, fields_ and (if present) _type arguments_.
* **Added Method References:** Refers to a class' method and can be derived, generating a _MethodProperty Flare_.
* **Added MethodProperty Flares:** Contains all the informations related to a class' method, which are _modifiers, parameters, return type_ and _type arguments_ (if present).
* **Added Constrictor References:** Refers to a class' constrictor and can be derived, generating a _ConstructorProperty Flare_.
* **Added ConstructorProperty Flares:** Contains all the informations related to a class' constructor, which are _modifiers, parameters_ and _type arguments_ (if present).
* **Added Field References:** Refers to a class' field and can be derived, generating a _FieldProperty Flare_.
* **Added FieldProperty Flares:** Contains all the informations related to a class' Field, which are _modifiers_ and _actual type arguments_ (if present).
* **Added Existing References:** They're nothing but _references which can be built on top of their already reflected value_. For example, an _ExistingParameter_ will be built on top of an already obtained _java.lang.reflect.Parameter_ object. They extend four other References, being _Generic, Modifier, Parameter_ and _RecordComponent_. They exist for safety purposes and to speed up other operations.
* **Added Parameter References:** They refer to a constructor/method's parameter and cannot be derived.
* **Added Modifier References:** Modifier References extrapolate modifiers from any Reference that can have them (such as _methods, constructor_ etc...). They can't be derived.
* **Added Annotation References:** They're the one of the few References which accept _type arguments_. They're not meant for users to work with them, I'd recommend just going with _Type References_ to interact with java annotations. They're much like Type References and can be derived, obtaining a _TypeContent Flare_. They're the main tool used by _Annotated Flares_ to reflect java annotations.
* **Added Generic References:** Generic References target _any type argument_. They can be derived, generating a _GenericBounds Flare_.
* **Added GenericBounds Flares:** Those very particular Flares allow to obtain a _complete list of the upper bounds of a certain type argument_.
* **Added RawType References:** RawTypes are... _particular_. They're rather useless on their own and they'd rarely be used for active Reflection purposes, but their strength resides in their _behind the scenes_ uses. They're very useful when it comes to _high end reflection tasks_, such as _generic bounds derivation_ or _actual type argument deduction_ in Field References. They're similar to Type References, but can't be derived.
* **Added EnumConstant References:** They target _an enum constant_. They're generic References just like _Annotation(s)_, they can be derived and they generate a _TypeContent_.
* **Added RecordComponent References:** RecordComponents refer to a _java record's component_. They're pretty much built as _Parameter References_ and, just like them, they can't be derived.
* **Added Annotated Flares:** They're returned by the _.getAnnotated() method_ of _derivable References_, and they contain an Annotation Reference _for each annotation_ present on a certain element.
* **Added EnumContent Flares:** When reflected, EnumContents will provide the user with _a full list of EnumConstant References_, all belonging to a certain _java enum_.
* **Added RecordContent Flares:** When reflected, RecordContents will provide the user with _a full list of RecordComponent References_, all belonging to a certain _java record_.
* **Added ExceptionProfile Flares:** ExceptionProfiles are Flares which contain TypeReferences to _all the exceptions_ of a method or a constructor.
* **Added SealedProfie Flares:** SealedProfiles target _java sealed classes_ and they provide _a list of all the classes_ which are _permitted_ by said sealed class.
* **Added Hierarchy Flares:** Hierarchies allow for a _complete derivation_ of a type's upper bounds, them being _its superclass_ (_Object_ by default) and _all of its superinterfaces_.


## early_dev-0.0.4.3 - The Event Fixing Patch

* **Meet the Constexpressions**: For this feature I took inspiration from C++'s compile time operations. I thought that having a similar thing in Moona would have been very cool... *so I added them*. They're just code Snippets which *Moona runs as soon as the Moona.init() method gets called*.
* **Added Constexpr Class**: In order to manage the aforementioned constexpressions, you need to interact with the *Constexpr Class*. This special Class is very similar to an Object Handler: it uses *a static method* that accepts *a Snippet* (usually passed as a lambda expression) and then *builds the Constexpr Object* which, being a Serial, can be handler by Moona itself. Once the Moona.init() method is called, the first operation performed will be *cycling through Moona's IshMap* in order to *find and execute* all the Constexpressions.
* **Fixed IshMap's .toArray() Method**: It now declares the returned array with *length 2* as first dimension and *the size of the IshMap* as second dimension
* **Fixed IshMap's .toString() Method**: It now prints square brackets properly when the IshMap is empty.
* **Improved Snippets**: In order to improve Snippet's efficiency, they're no longer supposed to be translated into Runnables, but they *directly extend them*.
* **Modified ProcessClock Class**: The visibility of its constructor was *lowered to package*. This is due to the fact that the only case in which ProcessClocks are useful is *when an AbstractProcess gets instantiated*. Furthermore, it helps avoiding *useless null checks*
* **Fixed Agent Class' Collapsing**: Both the collapsing and fading procedures were revisited for the Agent Class. Now the .collapse() method directly terminates the handler Task inside the Agent Class, meanwhile the .fade() method doesn't allow any new Events to be added to the Agent until all the others cease to execute.
* **Improved Handler Task in Agent Class**: The handler Task was provided with an *.end() method* which will *set back to false* both the collapser and the fader booleans.
* **Fixed Agent Class' Counters**: Added a *new counter* to keep track of ModalEvents and added the .eventCount() method, which returns the quantity of regular Events.
* **Improved Agent Class' Methods**: .include(Event e) and .exclude(Event e) methods will now check for *the fader boolean to be set to false* before moving on and performing all the checks required.
* **Added FailSafe to Moona.init() Method**: Because of Constexpressions, calling the Moona.init() method more than once would lead to them  being invoked multiple times.. which is wrong. This is because Constexpressions are designed to be called *just once* when running a program with the framework and since the same applies for *all the instructions inside the Moona.init() method* I decided to make this change.
* **Fixed Moona IDs' assignment**: The Moona.generateID() method used to *glitch out* when multiple static-declared Serials (such as Constexpressions) were trying to acquire their ID at the same time. It was fixed by replacing a *postfix increment* with a *prefix increment*, which, *for sure*, changes the idCounter before returning it to assign the new requested ID, avoiding a lot of bad issues.
* **Removed C++ Headers**: *They'll come back, I promise*.
* **Removed .test Package from Jar File**: The .test Package is a small side-package which contains the *Test Class*, which I use to test the framework. I removed it because.. *you'd make absolutely no use of such a useless Class*.

## eraly_dev-0.0.4.2 - The Phases-Improving Patch (Part 2)

* **Fixed Phase's .interrupt(Process p) and .terminate(Process p)**: These two methods used to firstly discard the given Process from the processor and, only then, they'd kick it out of the Phase itself. When passing Processes instead of IDs references, though, the Processor gets involved to return the Process associated with that ID... so removing the Process from the Processor *before* removing it from the Phase.. wasn't a great idea
* **Removed NotNull and Nullable Annotations**: I am not fully sure on how to integrate them in the framework, so, for now, they'll not be part of it. They might get back once I'll find a decent use for them.

## early_dev-0.0.4.1 - The Phases-Improving Patch

* **Corrected Providing Methods**: Methods such as *.start(Process p)* and *.provide(Process p)* were improved. They now check beforehand if the ID of the provided Process is already in their IDs list. This was made because of the fact that involving the Processor, which would perform *tons of extra useless checks* other than this made no sense.
* **Fixed .unlock(Process p) method**: Because of my *overly notable lack of attention*, I forgot to *make my copy-paste seamless* by forgetting to edit a "small detail" in the .unlock(Process p) methods of the Phase. Basically, *it did the same things as the .await(Process p) method*... yeah...
* **Fixed .spark(Process p) and .flick(Process p) methods**: These ones *actually did their job*... slight issue: *they increased the number of Processes* inside the Phase *every time they were called*... mind blowing.
* **Fixed .interrupt(Process p) and .terminate(Process p) methods**: Flying over the fact that *they increased Process counters instead of decreasing them*, which would be funny enough, these ones where the most hilarious to fix. Apart from that... *small detail*, they received a much more significant improvement: they now proceed by *firstly erasing the Process from the Phase* and only then *they call the Processor* to remove it from the main IshMap.
* **Fixed Processor's IDs references**: Added *return* to prevent fall-through when reverse-searching the wanted Process, which would have triggered a MoonaHandlingException otherwise... I haven't got *the slightest clue* on how on earth I couldn't spot this issue before...

## early_dev-0.0.4 - The Functional Update

* **Making functions... MORE FUNCTIONAL!**: I tried my best to integrate my Functional Interfaces with the ones already implemented in Java. To do this, I used the Satellite\<T\> Interface, native to Moona, which purpose is to *translate certain framework's elements* into a Java counterpart. This is the case of Moona's Functional Interfaces: they now implement Satellite\<T\>, where T corresponds to an analogue Functional in java.lang.function. 
* **Added Property\<T\> Interface**: A Property<T> uses the *.evaluate() method* to return a certain value (the value of the Property). It corresponds to *a Java Supplier\<E\>*, where *E is the same as T*.
* **Widely Spreading Functionality**: As mentioned earlier, many elements of the framework now rely on Functional Interface to work. The Property, being the simplest of them all, makes up for a pretty solid base *elements such as Reflections\<T\>*, which can now be translated into Suppliers by implementing the Property<T> Interface.
* **Added Functional Annotation and Made Functional Interfaces Explicit**: Each Functional Interface is declared with Java's *FunctionalInterface Annotation*... it's not needed, but it's better to have it explicit rather than not having it. Besides, elements which _declare Functional Interfaces as SuperInterfaces_ are now marked with the *Functional Annotation*, a new entry in this release.
* **Added Packable\<T\> Functional Interface**: This Functional Interface *replaces the Synced Interface*. This means that it now makes use of *all the new features of Functionals*. Regardless of that, its base function remains the same: it returns *an array of T* which represents a certain Object's properties *wrapped up into an array*. Translates into a *Supplier\<T[]\>*.
* **Re-added Phases**: Phases were re-introduced in this release, at the last bit. They keep their function as *containers*, but now they are only *restricted to containing Processes*. Though, they don't quite... *contain* Processes, since they only *store their ID inside of a List*. Actual Processes, alongside their ID, are contained _inside the Processor_, so that they can be both accessible in a static way via the Processor and manageable via Phases (which work like "folders").
* **Changed "CompositeProcess"es in "Synthetized"s**: There's not much to explain... *it just sounds better... that's all*.
* **Greatly Improved the Handler Class**: This class went through a very deep revamp in this new version. *Casts have been improved*, significantly reducing their speed; the *.cloneProcess(Process p)* was also significantly overhauled and, in addition, it now makes up for the base of every Process' *.clone() method*. To wrap things up, all the methods of this class were treated to match necessities (for example, from now on, Casted Processes will not automatically start when casted).
* **Deprecated Processor.mainStart(Process p) method**: Since it was doesn't have an actual use, as of now, I preferred indicating this featured as *deprecated*. It will be surely reintroduced in the future, since it will be needed for some features that I'll develop further in time.
* **Added Delegate\<T\> Class**: A Delegate, pretty much like a *C# delegate*, is a reference to a method that *can be translated to a Java Supplier\<T\>*, where T is the *type of the method's output*. It's a special kind of Object that allows you to constantly keep track of the return value of a certain method. Its constructor allows methods to be passed using the "::" operator, too.
* **Added Natures Enum**: It's an Enum which stores, as Enum Consts, *all the natures that were previously Integer Constants*. I found out that, by using Enum Constants instead, I could hasten many procedures, which now take, overall, less time to be performed.
* **Total Rework of Naturals**: Because of the aforementioned changes, also Naturals went through a whole rework. Now the *.nature()* abstract method of the Natural Interface now returns _a Constant from the Natures Enum_.
* **Removing Java Preview Features**: Reworking Naturals made possible to *replace the whole system of dependencies from Java Preview Features* (such as Switch Pattern Matching) only for them to be replaced with Nature-based expressions, which are *faster and safer*.
* **Future Plans for Natures**: Since the whole aspect that involves Naturals is a pretty important aspect of the framework, I have in mind some future changes that will bring them to the next level... *but, of course, no spoilers!*
* **Renamed Event-related Methods**: For both the Agent Class and EventPlaces/Spaces, the methods used to add/remove Events were renamed *.include(Event e)* and *.exclude(Event e)*, to make them a bit more unique.
* **Small Tweaks to the Processor Class**: Before this update, the Processor Class used to *look for already running Processes* passing from *the Moona Class*, originating an ENDLESS series of method calls that only ended up slowing the code.
* **Tweaked Declarations with Annotations**: Since I grew tired of seeing all the Annotations being declared *on top* of methods, I decided to move them *on the same line as the declaration itself*, by putting them after the access limiter.
* **Overall Reviews and Technical Fixes**: they're *way too many*, and *I have completely forgotten most of those*... so let's just appreciate the fact that *I bothered checking every single corner of this ratherly instable cauldron of messy code*.

## early_dev-0.0.3 - The Object Handling System Update

 Either way, getting back to the _hot topic_, I'll now go over the majour features that I've added:
* **Object Handling Revamp**: Since before this release the majourity of _chores_ were charget to the Moona Class, I've decided to work on this aspect by adding _three new Object Handlers_, which will each be the point of reference when working with certain types of Serial (they're listed below).
* **Agent Class**: The first big Object Handler, a Class which is in charge of dealing with _Events_. The necessity came not only for a design choice, but also because _Events require a specific environment to work properly_ (which consists in an endlessly iterating list, crucial for them to function): this was the majour reason because of why they couldn't be stored alongside all the other Serials.
* **Meet the Processor**: The second Handler does the dirty job of _handling Processes_ instead of Moona. Despite that, all the methods that previously belonged to Moona _have been kept the same_ and there haven't been any majour changes to them.
* **Reflections**: They're very special Objects which _take care of a single Java Reflection operation_. 
* **Annotated(s)**: These were especially entertaining to code, because they allowed to achieve _(almost) automatic Java Annotations detection_. They operate in a very particular way, which I'll not cover because _no one would care_.
* **As a final note.. The Mirror**: Here's the third and last Object Handler I mentioned earlier and, as you might have guessed, _this Class handles Reflections_ (you know... a mirror has to deal with.. reflections... it sounded funnier in my head). Either way, it's built so that you can widely interact with _any Reflection that it contains_. I've designed _plenty_ of.. _overly redundant methods_ for you to perform any kind of interaction that you could ever dream of.
* **Added Synchronizer Process**: A Process which makes multiple other Processes run simultaneously (added *Synced* Interface, too).
* **Added CompositeProcesses**: A brand new type of Process which is highly customizable (in a... _prettier way_ than regular Processes) and which is used in the next feature, too!
* **Added Process Handler Class**: This is a cool one, it's designed to perform all those task that rely on _cloning, casting and building_ Processes! It's very handy to convert a certain Process Type into another one, for example (it uses CompositeProcesses to _clone processes_).
* **Added Custom Functional Interfaces**: _it was all a lie_, THEY'RE THE MAIN CHARACTERS OF THIS RELEASE! Other than being extremely useful all around the framework, they also have _quite a funny backstory_. The idea of creating them popped into my mind when I realized that _using the Runnable Interface to store code snippets was... weird_... and so the Snippet was born, and all the others followed.
* **Added Unique Annotation and UniqueObjectException**: They consist in a way of telling Moona that _you don't want a certain Serial to be added and/or processed more than once_. As a side note, they also use the brand new _Reflections_ to work!
* **Reworked Package Infrastructure**: The old one was... old... so I decided to renew it by splitting each package into _specific areas_... but I'll let you find out how they're arranged.
* **Reworked Statuses and ProcessConditions**: An important aspect of a Process is to understand whether _it's running or it's paused_. The old solution (using two different Statuses) was inefficient (due to having to take care of two different Objects), but now the whole thing received a significant improvement. Now they only require a single **ProcessStatus**, which holds a ProcessCondition: this way, the detection of the condition becomes way more fast. This change _behalved_ the loading time of Processes!
* **Reworked Statuses and added Property Interface**: From now on, the Property Interface will be a super Interface _of all those types which purpose is to monitor a condition or a value_. The most fitting example for this case is the _renewed Status Class_, which became a _limited access Class_ (which uses _protected methods_ and relies on inheritance). 
* **About ProcessStatuses**: They make up for a good solid base for handling ProcessConditions: they're used in Processes as mentioned earlier: they store a ProcessCondition which indicates the condition of the given Process. They extend Statuses and they override some of the protected methods _as public_.

## early_dev-0.0.2.1 - The Exceptions Patch
Added:

.reflection package: a set of tools to operate with java reflection, containing:
* Property Interface: the base of reflections.
* Reflection Abstract Class: it's the fundamental unit for the reflection framework.
* Synced Interface: marks an element as synced with a list of others.
* Synchronizer Class: a particular process which allows to multiple ones to be executed in sync.
* .essential package: the package containing those interfaces which handle the most aspects of the framework.

Fixed:
* Many methods now feature *more exceptions* than before. Some of them didn't have all the exceptions required to filter their inputs.
* Fixed constructors.
* Now the ProcessCondition Enum is much more compact and enum-like, abandoning the abstract methods it used to have.

## early_dev-0.0.2 - The Event Update
Added:
.annotation package, containing:
* Deadlined Annotation: marks those methods which were made useless.
* Timeless Annotation: marks those methods which require a long period of time to execute.

.event package, containing:
* AbstractEvent Abstract Class
* Action Abstract Class
* AutoEvent Abstract Class
* Event Interface
* EventMode Enum
* ModalEvent Interface
* EventSpace and EventPlace Classes: they're two special processes which handle events. EventSpaces end when all the Events they contain get executed, meanwhile EventPlaces last until the end of the framework.
* Phases: parts of Moona which handle Processes and Serials just like her.
* Series: a functional class which generates a series of numbers based on a given function.

Many new Processes:
* Daemon: a process executing until all the other non-daemon processes are dead.
* Devil: being it bound to a Phase, terminates its life only once all the non-daemons in that Phase are dead.
* Worm: it's bound to another Process and keeps going until that doesn't die. It can also run standalone if the host process is null.

Removed:
* Moona.mainStart(Process p) method: it will surely be reimplemented in the future, but will no longer be part of Moona until then.

Fixed:
* Many minor fixes throughout the whole framework.

## early_dev-0.0.1 - The First Release
Added:
.moonaFramework package, containing:
* Benchmark class
* Moona class
* MoonaHandlingException class
* Natural interface
* ProcessCondition class
* Serial interface
* Status class

.relation package, containing:
* Attached interface
* Bindable interface

.time package, containing:
* Chrono class
* Clock class
* Timer class

.util package, containing:
* Condition class
* Conditional interface
* Duo class
* FinalDuo class
* IshMap class
* Reviterable interface
