# Moona Guide ❓

Welcome to the Moona Guide! This file contains all the major features of this framework along with their own complete description for you to understand what they do. In here you'll not find any kind of *documentation*: the guide's sole purpose is to enlighten your minds on all the merely *technical aspects* that make up Moona. If you're searching for the documentations, you'll be able to find everything on the [Moona Wiki](https://github.com/anOsuPlayer/Moona/wiki/home), where all the elements are reported.

Following this brief introduction, you'll find a nice *index* which will summarize all the small chapters of this guide. With this being said, I wish you a happy learning and have fun using Moona!

> *The Developer*

# Index 📌

* [Dependencies](#dependencies)
* [Packages Organization](#packages-organization)
* [Nature and IDs](#nature-and-ids)
* [Processes](#processes)
* [Moona Class](#moona-class)
* [Beginning with Moona](#beginning-with-moona)

## Dependencies 🔗
> [^ back](#moona-guide)

I'm pretty sure that, at some point, you'll have read about some this freaky concept around the framework. Fear not, though, it's nothing too complex to comprehend. The concept of *dependency* states *how closely related each element is to the [Moona Class](#moona-class)*.

Being Moona, basically, the *very core of the whole framework*, this idea can be stretched out even further by saying that dependency tells us *if certain elements can or cannot live outside of the framework*.

### Dependency Identifiers 📜

In order to understand the dependency that each elements has, I've come up with some practical keywords which are meant to tell the user *how dependent* that certain element is. Those keywords are *dependent*, *basic* and *non-dependent*.

#### **DEPENDENT ❌**
We call an element *dependent* when *it's closely related to Moona*, also meaning that *it cannot PROPERLY WORK if not inside the framework's context*.

Their proximity to the Moona Class basically means that *they are required to interact with it in order to function properly*. When it comes to dependent objects, it's always a matter of *"functioning in the right way"*: there is no strict rule that forces you to use them alongside Moona, but not doing so would be... pretty useless. They were designed to give their best when handled by the framework, excluding them from that environment could potentially even be dangerous.

#### **BASIC 💥**
There is not much to say on this dependency: *basic elements* are those which *are contained in the [.moonaFramework](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework) package*. This applies to very few elements but, among those, just remember the [Moona Class](#moona-class), [Serials](#nature-and-ids) and [Naturals](#nature-and-ids)

Basic elements *are also dependent:* being them the very base of the framework itself it would not make any sense if.. elements that make the framework were able live *without the framework*... is this some kind of paradox..?

#### **NON-DEPENDENT 🔘**
For elements to be called *non-dependent* they need to be *completely deatached from the Moona Class* and, consequently, *from the framework itself*.

This dependency implies the fact that *non-dependent elements can work properly even ouside of Moona*. This property of them, though, is *not commutative*: even though they don't necessarily need Moona to work, *Moona needs some of those objects to run*. This property is not fundamental to remember, but justifies the fact that, when downloading the whole framework, *non-dependent element don't get left behind*.

### Interdependency ♾️
To top things off, I will introduce you to *interdependency*. Despite the very complex name, it's actually a very simple thing to understand: it just defines *links between non-dependent and dependent objects*. With "link" I mean *whether or not a certain element needs another one to properly function* (basically like dependency, but it's more specific).

Interdependency is based on a few simples rules:
* Types of the same dependency *can be interdependent*: meaning a non-dependent object can require another non-dependent to work, for example.
* Non-dependent types *CANNOT be interdependent* with dependent/basic objects: that would break the non-dependency concept: elements which can be external to the framework *must not* require anything from it to function properly.
* Dependent and basic objects *can be interdependent* with non-dependent types: since the framework contains them all by definition, there are no problems in linking them all to each other.
* There are cases of *multiple interdependency*: assuming all the previous three rules, throughout the framework there are some cases of this phenomenon which behaves just like regular interdependency, it just includes multiple types.

Interdependency was created mainly to explain how *package are organized*: further on you'll be able to see how this technique shines when it comes to divide each different feature in its own package. It's, yet again, not a fundamental concept to know, but it helps to satisfy your doubts.

In conclusion, all this dependency stuff just *defines relations* between either the Moona Class (regular dependency) or other elements.

## Packages Organization 📦
> [^ back](#moona-guide)

The first thing I want you to have in mind is the *package organization*: the way packages and classes are organized. I think this is one of the most important things if you want to quickly access the features you are looking for (without endlessly searching for a something, wasting time).

### Listing Packages

> NOTE: Some packages will be marked as *dependent*, *essential* and *not-dependent*, learn more about what these terms mean by consulting the [dependencies](#dependencies) paragraph in this file.

In the first place, there is the most important package of them all, containing all the others: the [**moonaFramework** package](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework). This particular one is the root of all the other sub-packages (which means all the other packages will be named *moonaFramework.(something)*. for practical reasons, you'll see only their name). This package also contains the *root classes* of the framework (like, for example, the [Moona class](#moona-class), which you'll see in a bit).

As menitoned, the moonaFramework package *contains all the others*. If you wanted a place to consult the full list of packages inside Moona, you could check the [Wiki Page](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework): here, once every new update gets dropped, you'll find all the old and newly added packages, with their relative description.

### Some Practical Use...

As mentioned above, words such as *dependent*, *essential* and *non-dependent* will be clarified further on but, for now, I will just briefly talk about the way all those packages will be organized upon a release:

* *non-dependent packages* are going to have a **standalone release** as a .jar file released along with the full .jar containing Moona as a whole. This was thought in order to make people access only features they're looking for when not interested to download the entire framework (which, with each passing update, might get *very heavy* in terms of download size, too).

* *dependent packages* will **NOT** be released as standalone .jar files, this is because of their objects *directly depending from the Moona class* (to keep this brief, dependent objects are those that *cannot live without the Moona class*).

To look after some more details regarding how the releases/downloads work, please consult the [README.md](https://github.com/anOsuPlayer/Moona/blob/early_dev/README.md) file in the repository.

## Nature and IDs
> [^ back](#moona-guide)

Moona features a great variety of different objects, each of them doing something specific. Among those elements the most important are [Serial](https://github.com/anOsuPlayer/Moona/wiki/Serial) and [Natural](https://github.com/anOsuPlayer/Moona/wiki/Natural) elements. They are very useful when it comes to *identify a framework's object in a specific way*, that being *the definition of an arbitrary "type" of it* and *the declaration of an unique number for that particular instance*.

### Similar purpose, but different usage

*Serial* and *Natural* are two Interfaces and *Serial EXTENDS Natural*. They're both needed to have methods that *return specific numbers that describe the object*, but they do it in a different way one from another:

> If a type implements *Natural*, this means that it *is required to return a specific integer number that distinguishes that natural from others OR that makes two different naturals have something in common*.

Let's create a quick example: you want to create two different classes, A and B, *both implementing Natural*; let's suppose that these types are distinguished by two different "natures", e.g. the method *nature()* in the A class returns **1** whilst the one in the B class returns **2**. This might seem a bit.. useless.. since A and B are two completely different objects there is absolutely no need to make them different from each other even further... or is it?

*What if the B class extended the A class*? Let's picture this scenario: you have created a method which *returns true* only if passed objects are *instances of the A class* (something like **boolean isA(A obj) { ... }**; if that was the case, both instances of A and B *would be accepted* and, thus, the same operations would be performed with both instances of A and B, since B extends A.

Let's now imagine a somewhat more *particular case*: we want to create a method that returns true *ONLY when an instance of A is given as an input* (NOTE: a *direct instance*, which means no subtypes are allowed). It would be almost obvious to write something like this: **boolean method(A obj) { ... }**, accepting only *A types* and then filtering them with countless *instanceof* to see if they're not subtypes... but what if that method required to accept *different types* (something like **boolean method(Object obj) { ... }**)? What if, for each *different type* a *different operation* was required? Of course, in a case like this, accepting an *Object* as argument and then checking each case with the *instanceof operator* would do the trick... but would, also, take up a lot of performance.

Here comes in help the *Natural type*: since A and B *are both Natural*, our hypothetical method could accept *a Natural instead of an Object*; from there, by just switching the value of the *.nature() method* we're able to tell apart *the nature* of that specific object. Even if the method *accepted a generic Object*, you could tell beforehand if that *was an instance of Natural* and, then, filter the .nature() method's output to do stuff. This operation (on the long term) should be able to save a lot of time in terms of performance.

Another possible use for the Natural type would be to *declare as similar two completely different objects*. For example, if both the A and B classes (*not inheriting each other in any way*) were to return **1** from the .nature() method, you would be able to count them as *equal* or, to use some fancy terminology, *of the same nature*. This would lead to some very neat and specific operations that you could perform with those types, but that's up to your creativity, of course!

A "nature", as said earlier, is nothing but an *integer number*: all those naturals whose *nature()* method returns that specific integer are said to be *of the same nature*. It's also possible to visualize those integers as *theoretical groups* that keep together all those elements declared as "similar": one example is the nature which value is *-1*: every natural that returns this number is declared to be *of the nature of an Exception*.

This last paragraph was useful to show you that the Nature interface is not only useful to *distinguish* types, but also to *make them have something in common*

***

> If a type implements *Serial*, *it requires BOTH to return a specific nature AND to return a specific ID which distinguishes it from all the others.*

Serial objects extend the concepts already stated in the previous paragraph, this is because, as already mentioned above, *Serial objects are ALSO natural*. But what is their real purpose? I mean, in order to just tell two objects apart the .equals() method would be enough... rigth? Not quite.

Each Serial needs to declare the *.id() method*: this specific method is the one that returns the aforementioned *ID* as a long number needed to tell one Serial from another. But here comes the cool thing, that specific number *is assigned by the [Moona Class](#moona-class)* inside the constructor of each serial object. You don't think that's cool? Well, what if I told you that *Moona can interact with those IDs*? 

Let's make things clear before going on, what does it mean that *Moona can interact with Serials*? To explain you this concept, I will need to once again *blow your mind* by unraveling another one of the Serial objects' secret: *each of them is stored **inside a "List" which resides inside Moona***. By DEFINITION, *A Serial object is an object that declares a specific ID AND is stored inside Moona*.

As you'll be able to see further on, the Moona Class features a lot of ways to *interact with IDs*. The first thing worth mentioning is that **you are able to recall EVERY SERIAL OBJECT if you know its ID**, thanks to methods that accept a long value and that return the corrisponding Serial. This, of course, applies to a lot of various uses.

As I did before, I'll show you a practical use of this class with an example. Let's imagine two instances **A and B** of a class **C** which *implements Serial*, the two instances have two different IDs: **1 and 2**. Imagine that these two instances are two *private fields* inside of a generic **D** class (NOTE: "generic" does not imply *Java Generics*!) and that inside of the C class there is a method, which takes as an input a Serial and that... *does something*, a method like this: **void acceptSerial(Serial s)**.

Since A and B are *two private fields inside of another class*, there would be no chance to call them from elsewhere... *maybe*. Thanks to a method called *.getElementByID(long id) { ... }* inside of Moona which returns the Serial corresponding to the given ID, we're able to *access to that private element by ONLY KNOWING ITS ID*, doing something like this: **acceptSerial(Moona.getElementByID(1))** (to know more about methods such as this, you can go check the [Moona Class' Wiki page](https://github.com/anOsuPlayer/Moona/wiki/Moona)). This operation will grant you the serial you're trying to access even if the latter *cannot be regularly accessed* though direct call.

Each "call by ID" is made possible since, as already mentioned before, *each Serial ALSO lives inside the Moona class*. As you'll get your chance to see, Serials are implied in a lot of tasks inside of the framework and, even though I've not covered each one of them here, you'll be able to try all those features by yourself, I'm 100% convinced that's the best way you can learn!

Of course, though, not everything is automated and not everything will be done by the Moona class itself, so there are some things you might want to keep in mind:
* Serial objects are NOT AUTOMATICALLY ADDED TO MOONA: if you plan on creating a serial object yourself, keep in mind that *you'll need to manually add it to Moona in order to make the magic happen*.
* You CANNOT arbitrarily assign an ID to a Serial! IDs are required to *be assigned by the Moona Class itself*. Since they rely on *the total ammount of serials that entered the list*, you're not free to choose an ID since some really dangerous issue might occur.

> NOTE you can learn more about how to do this stuff by checking the [Moona Class paragraph](#moona-class) in this guide or by consulting the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona).

## Processes
> [^ back](#moona-guide)

In this guide I purposed myself to explain every technical aspect of this framework, and to do so I will also cover those elements which, in my opinion, are due to have a description for them to be more understandable. This section will contain heaps of useful information about some of the most important elements of Moona, *Processes*.

To begin with, processes are *dependent objects* and there are *many different kinds of processes*, you'll be able to check all of them on [this page](https://github.com/anOsuPlayer/Moona/wiki/.process) of the wiki). All those types of processes, though, have *something in common*; by definition, what identifies a process is the *Process Interface*, which, if implemented, automatically makes that type *a real process*.

The Process Interface is *the most basic example of a process*: it contains the basic methods that make up all the things needed to operate with Moona and, consequently, to be a fully functional object. The hierarchy of the aforementioned interface is the following:
* It *extends the Serial Interface*: ALL the processes that can be considered such *are ALSO serials*. This means that *the Moona Class CAN treat processes [the same way](#moona-class) it treats Serial Objects*
* It *extends the java.lang.Runnable interface*: in order for a process to work it needs to *be executed inside another Thread*, implementing this interface is the key element to make processes interoperable with java Threads
> NOTE: the full functionality of a process (being it a *dependent object*) can ONLY be achieved by using it simultaneously alongside Moona. Inside the Moona Class processes are treated in a [specific way](#the-process-hq) such that they can be started correctly, however if you ever wanted to use them as regular Runnables there would not be any problem (just keep in mind the fact that *it would be ratherly useless*).

To wrap things up, processes are none other than *dependent objects* considered as *serials* which *enhance the Runnable interface*. Yes, they're basically *buffed-up runnables*, but I promise that there are a lot of features that will blow your mind, you'll never use runnables again! (maybe)

### A Process' Status
Inside every process there are two methods overridden from the Process Interface, those methods return two [statuses](https://github.com/anOsuPlayer/Moona/wiki/Status) which tell us whether the process is *paused or not paused* and whether it's *running or not running*. From these two statuses we can obtain *4 different possible combinations*: each one of those represents a *condition* in which a process can identify itself into. The aforementioned conditions are the following:

* **DEAD**: a process is considered to be dead only when *it's both NOT paused and NOT running*. Usually, processes are considered to be dead only when *they've just been initialized* or when *Moona terminated them*. A process CANNOT EXIST inside Moona if it's considered dead.
* **AWAITING**: if the given process is *paused but NOT running*, it's called an *awaiting process*. This is a special kind of condition that can happen only *before initializing the process*, when it's added to Moona but *it hasn't been started yet*.
* **PAUSED**: as intuitive as its name is, this condition identifies *a process which is both running AND paused*. This condition can be achieved by pausing the process using specific methods, which you'll se in the [Moona Class paragraph](#moona-class) (or in the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona) if you want a detailed list of all the methods). The running status is needed to mark the fact that *the process is still alive* even though it's paused.
* **RUNNING**: in the end, a *running process* consists in a process which *is running and NOT paused* (marvellous.. right?).

> NOTE 1: All these statuses are controlled by an enumeration called [ProcessCondition](https://github.com/anOsuPlayer/Moona/wiki/ProcessCondition). You can operate with process conditions in different ways using that enum, try it out!

> NOTE 2: In order to cover the procedure behind how processes are initialized, check the [Moona Class paragraph](#moona-class) or the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona). Keep in mind that you'll find many unknown terms that have been descripted or explained in one of the two.
 
### Starting, Pausing and Interrupting processes
After listing each one of the possible statuses a process can assume, now I will proceed by explaining *how a process can be "moved" between different statuses*. In other words, it's just a fancy way of saying how you can start, stop or pause processes. Other than that, I will also tell you how *the complete lifecycle of a process* is structured.

First things first, you need to have a basic understanding of *how you can interact with processes*: you do not operate directly on them (meaning *you don't have to invoke anything from the instance of a process*) but you *interact with them via the [Moona Class](#moona-class)*. There you'll find several methods that will help you making a process run properly.

> NOTE: to have a better understanding of all the methods I'll refer to throughout this explanation, I suggest you to check the [Process Interface](https://github.com/anOsuPlayer/Moona/wiki/Process) or the [Moona Class](https://github.com/anOsuPlayer/Moona/wiki/Moona) wiki pages. 

### Starting
In order to set up a process correctly, you first need to *start it*. After instantiating a certain process *P* you'll have some routes to choose between:
* Using the *.Start(Process p) method*: invoking the Moona.Start(*P*) method you'll have succesfully started you process and thus its execution will begin on a separated thread.
* Using the *.Initiate(Process p) method*: slightly different from the last one, this will start the process exactly like the .Start() method would, except for the fact that *it would not invoke the .initialize() method from the process*. 

The substantial difference between these two methods is the *invocation of the .initialize() method*. In order to achieve a satisfying flexibility with how processes are handled, I planned out this kind of methods in order to give the user more possibilities to manipulate them.

When starting a process, keep in mind those things:
* Once started, the process *will be tagged as RUNNING*.
* After invoking any starting method, the process *will be automatically used to set up a Thread to host it*.
* Starting a process *ALSO* means adding it to the [list of serials](#the-almighty-serial-container) inside of the Moona Class.

### Main Thread Initialization

During your coding using this framework you might encounter some *special processes*. Those particular types are no regular processes: they require to *be initialized on the main thread*. Be aware, though, because *you'll be able to initialize ONLY ONE PROCESS*! Once it's initialized there, you'll be totally excluded from the main thread and, thus, you'll be locked out of it until the process reaches its end.

In order to initialize processes this way we need to use a very particular method: the *.Initialize(Process p)* method. Using this method the [.Init()](#initializing-moona) method will get automatically called (only if the *isOn* boolean is FALSE) and, only after that, the process will be started. The thread will remain occupied *as long as the process keeps running*

### Awaiting and Unlocking

### Sparking

### Interrupting Processes

## Moona Class
> [^ back](#moona-guide)

If you're here reading this guide, I might say that you've probably read something about this very special class throughout the documentations. Well, allow me to present you the most important thing in this framework, containing informations, methods and things that make the framework... *work*.

Let's make some things clear, first:
* This guide DOES NOT FEATURE the list of all the methods in this class, to find out about that, you'll have to consult the [Official Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona) here on GitHub.
* You might need to learn about some *specific terms* that were previously mentioned in this guide, keep in mind that, for each term you're not familiar with, *this guide will provide you the definitions you need*, just look around a bit and you'll be satisfied.
* This section of the guide *is very likely to change in the future*. Since this is one of the classes I'll be developing more actively, so expect new stuff to appear and disappear around here.

As you might have guessed, this class is *the most important in the whole framework.*, you'll see in a bit why. It's also very important to mention that *IT IS NOT instantiable*: the Moona class is accessible ONLY via its *static methods*. But.. what are those methods useful for? Let's find out.

### Initializing Moona
The first thing to know in relation to Moona is only one, and it's *crucial* in order to make things run properly. Since certain elements inside the framework have the necessity to interoperate with libraries, there must be a way in order to *properly start them*.

> NOTE: not ALL the objects in the framework are linked to a library, I suggest you to initialize Moona regardless of library-dependency, though. It's only one line of code and you'll be able to save a lot of troubles by adding it to your code!

Inside the [Moona Class](https://github.com/anOsuPlayer/Moona/wiki/Moona#fields) there is a specific private boolean field that states *whether or not libraries were initialized or not*: it's the *isOn boolean*. This boolean value can *only be set to true* from its original false status, this can be done via the *.Init() method* of the Moona Class: triggering this method will cause the isOn boolean to be permantently set to *true* (as long as the framework keeps running) and you'll never need to call this method again.

Adding the line of code *"Moona.Init();"* before using any feature of this framework is not only a good practice, but will also make things easier when working with Moona.

> NOTE 1: **ALWAYS use the Moona.Init() instruction IN THE MAIN METHOD.**

> NOTE 2: what I do NOT advice is to add this line *in the middle of your code*: it's always better to write this instruction at the beginning of your main method, in order to avoid complications.

### Mother Nature
I chose this title in order to highlight the word "Nature" to your eyes. Inside this class, indeed, are stored *static fields that store ALL the natures inside the framework*.

By definition, [Natural elements](#nature-and-ids) are identified by integer numbers (the *-1* number, for example, identifies *Exceptions*).. but how are they chosen... where can I find them? The answer is *in the Moona Class*. Here you'll find all those aforementioned static fields containing ALL THE NATURES used inside the framework (there is the field *Moona.EXCEPTION*, which evaluates to *-1*).

This was made in order to *make the code more readable*: if the nature() method returned just a normal number it would be much harder to understand, meanwhile, returning the corresponding static field inside of Moona makes the whole thing much more clear to your eyes. In a few words, what's more intuitive *-1* or *Moona.EXCEPTION*?

Another reason those fields were added is because.. well... *I'm not evil*. I thought to the fact that people, for whatever reason, might actually need to operate with those constants and, thus, I chose a fitting place for them to reside and for you to find them easily!

### The Almighty Serial Container
As mentioned in the [Nature and IDs](#nature-and-ids) paragraph (where *serial elements* were introducted), ALL the serials *have to live inside Moona*, for them to work at their best. 

> NOTE: As always, it's a matter of "working at the best" because dependent elements are not made to operate without Moona. Serials *could* live outside of the framework but... the truth is they'd be pretty useless on their own.

Serial elements are listed inside of Moona via an [*IshMap*](https://github.com/anOsuPlayer/Moona/wiki/IshMap) that stores them *alongisde their ID*. This big list is used recall each of those elements when needed. You're able to access those objects by the [methods](https://github.com/anOsuPlayer/Moona/wiki/Moona#methods) that interact with IDs inside of this class: by knowing the ID of one you'll be able to reach that specified object, even if it's stored as private field somewhere!

When explaining serials I mentioned the fact that *they are not magically added to the Moona class* and that *their ID cannot be imposed by the user*. This is because the Moona Class provides *appropriate methods* to do such things: via the **Add(Serial s)** method and the **GiveID() method** you are able to both store them inside the list and provide an adequate ID for it to not mess up. If you want to check some alternative for those methods or if you just want to know how they work, take a look [here](https://github.com/anOsuPlayer/Moona/wiki/Moona#methods).

Please remember the fact that, other than removing them from the list, *there cannot be any direct interference with how Moona stores serial objects*. This means that the user can only *add* or *remove* those elements, they cannot directly access the place where they're stored, though. I've developed this feature in order to prevent any necessity that involves editing the serials' list, hopefully the methods I provided will be enough for you to work!

### The Process HQ
*to be added*

## Beginning with Moona
