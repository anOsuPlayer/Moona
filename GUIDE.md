# Moona Guide ❓

Welcome to the Moona Guide! This file contains all the major features of this framework along with their own complete description for you to understand what they do. In here you'll not find any kind of *documentation*: the guide's sole purpose is to enlighten your minds on all the merely *technical aspects* that make up Moona. If you're searching for the documentations, you'll be able to find everything on the [Moona Wiki](https://github.com/anOsuPlayer/Moona/wiki/home), where all the elements are reported.

Following this brief introduction, you'll find a nice *index* which will summarize all the small chapters of this guide. With this being said, I wish you a happy learning and have fun using Moona!

> *The Developer*

# Index 📌

* [Dependencies](#dependencies-)
* [Packages Organization](#packages-organization-)
* [Nature and IDs](#nature-and-ids-)
* [Processes](#processes-)
* [Moona Class](#moona-class-)
* [Beginning with Moona](#beginning-with-moona-)

## Dependencies 🔗
> [^ back](#moona-guide-)

I'm pretty sure that, at some point, you'll have read about some this freaky concept around the framework. Fear not, though, it's nothing too complex to comprehend. The concept of *dependency* states *how closely related each element is to the [Moona Class](#moona-class-)*.

Being Moona, basically, the *very core of the whole framework*, this idea can be stretched out even further by saying that dependency tells us *if certain elements can or cannot live outside of the framework*.

### Dependency Identifiers 📜

In order to understand the dependency that each elements has, I've come up with some practical keywords which are meant to tell the user *how dependent* that certain element is. Those keywords are *dependent*, *basic* and *non-dependent*.

#### **DEPENDENT ❌**
We call an element *dependent* when *it's closely related to Moona*, also meaning that *it cannot PROPERLY WORK if not inside the framework's context*.

Their proximity to the Moona Class basically means that *they are required to interact with it in order to function properly*. When it comes to dependent objects, it's always a matter of *"functioning in the right way"*: there is no strict rule that forces you to use them alongside Moona, but not doing so would be... pretty useless. They were designed to give their best when handled by the framework, excluding them from that environment could potentially even be dangerous.

#### **BASIC 💥**
There is not much to say on this dependency: *basic elements* are those which *are contained in the [.moonaFramework](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework) package*. This applies to very few elements but, among those, just remember the [Moona Class](#moona-class-), [Serials](#nature-and-ids-) and [Naturals](#nature-and-ids)

Basic elements *are also dependent:* being them the very base of the framework itself it would not make any sense if.. elements that make the framework were able live *without the framework*... is this some kind of paradox..?

#### **NON-DEPENDENT 🔘**
For elements to be called *non-dependent* they need to be *completely deatached from the Moona Class* and, consequently, *from the framework itself*.

This dependency implies the fact that *non-dependent elements can work properly even ouside of Moona*. This property of them, though, is *not commutative*: even though they don't necessarily need Moona to work, *Moona needs some of those objects to run*. This property is not fundamental to remember, but justifies the fact that, when downloading the whole framework, *non-dependent element don't get left behind*.

### Interdependency ♾️
To top things off, I will introduce you to *interdependency*. Despite the very complex name, it's actually a very simple thing to understand: it just defines *links between non-dependent and dependent objects*. With "link" I mean *whether or not a certain element needs another one to properly function* (basically like dependency, but it's more specific).

Interdependency is based on a few simples rules:
* Types of the same dependency *can be interdependent*: meaning a non-dependent object can require another non-dependent to work, for example.
* Non-dependent types *CANNOT be interdependent* to dependent/basic objects: that would break the non-dependency concept: elements which can be external to the framework *must not* require anything from it to function properly.
* Dependent and basic objects *can be interdependent* to non-dependent types: since the framework contains them all by definition, there are no problems in linking them all to each other.
* There are cases of *multiple interdependency*: it's possible that various objects might need to have contacts with more than one other type. Assuming that the three aforementioned rules don't get broken in any way, this will work just as regular interdependency.

Interdependency was created mainly to explain how *package are organized*: further on you'll be able to see how this technique shines when it comes to divide each different feature in its own package. It's, yet again, not a fundamental concept to know, but it helps to satisfy your doubts.

### Telling Dependency Apart 🔎
In order for you to find out the dependency of a certain object, you'll need to check its related [Wiki page](https://github.com/anOsuPlayer/Moona/wiki/Home). Together with the description of that element, you'll be able to see its grade of dependency by looking at the title of the page: there's a full description listing all the attributes of the class (visibility, finality ecc...) which contains that, too.

In conclusion, all this dependency stuff just *defines relations* between either the Moona Class (regular dependency) or other elements.

## Packages Organization 📦
> [^ back](#moona-guide-)

In this part of the guide, I will introduce you to how the *packages are organized*. There are no quirky concepts to keep in mind here, so don't worry. The organization of packages is based on *interdependency* and *affinity between elements*. 

### Packages Rules ⚖️
To begin with, packages *can have their own dependency*, just like regular classes: there are *non-dependent packages* and *dependent packages* which *can* or *cannot* be used alone outside of the framework. The dependency of a package is dictated by the classes it contains; in fact, packages can only contain types which are *equally dependent* (if that wasn't so, many rules would be broken).

The *interdependency rules* also remain with packages. Since package have the same dependency of the classes they contain, this means that we can assume that interdependency *between those classes* could be interpreted as interdependency *between packages* (packages are not explicitly linked between each other, their interdependency is inferred by the classes).

To make this a bit more clear, you can assume that a *package A* is interdependent to a *package B* if a class inside of A is linked with a class inside of B (always assuming that the general interdependency rules are respected).

The only rule that must be kept in mind is that *non-dependent packages cannot be interdependent to each other*: as you might guess, since non-dependent packages can be used alone outside of the framework, it's not certain whether or not the classes they reqire *will be there*. I've organized packages in order to not have you downloading a lot of packages to have only one feature working: similar features are grouped in the same package and they don't need anything elese to operate alone.

### Hierarchy 📋
The *packages hierarchy* in this framework is ratherly simple: a main package containing all the others. The main package is called the [.moonaFramework package](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework), which, as mentioned, is the root *all the other packages* and, other than that, it's also the container of *all the [basic](#dependencies) elements* that make up the framework.

In order to consult the full list of package, I advise you to check the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework): the page of the main package contains all the .moonaFramework package's sub-packages and all the basic elements (descriptions included!).

### Some Practical Use... 🔮
Coming to an end, packages were made following all those pretty strange rules in order to achieve some kind of *modularity*. I wanted packages to be seen as some kind of "clusters" of similar classes and objects: for each one of them, similar features are grouped together for you to have both an organized environment in which to find what you're looking for and a way to have downloadable separated features.

Speaking of which, as mentioned in this repository's [README.md file](https://github.com/anOsuPlayer/Moona/blob/early_dev/README.md), some of the packages *are available as standalone downloads*:
* Dependent packages *are not downloadable* as standalone (since they need the framework to function, downloading them and using them separately would break them)
* Non-dependent packages, on the other hand, are those who you'll be able to download. They were purposely thought for you to download them if you're interested in *just* that singular set of features.

In a few words, this modularity-kind of environment was built to *make single features available* without downloading the whole framework. It's a kind of freedom I wanted my community to have, to maximize the possibilities: if you only like some feature, just download it! (but pls consider downloadin the whole framework 'cause it's cool \* wink \*).

## Nature and IDs 📐
> [^ back](#moona-guide-)

This framework "features" (as of now, *will feature*) a wide variety of objects. Among them all, though, the most important ones are, for sure, [Serials](https://github.com/anOsuPlayer/Moona/wiki/Serial) and [Naturals](https://github.com/anOsuPlayer/Moona/wiki/Natural).

The reason behind their importance resides in their purpose: they serve as *identifiers*. They are both needed to specify a certain aspect of an object which implements either interfaces, the thing that makes them apart is *how specific* is the aspect of the object they highlight. I know this might sound slightly strange, but it's really easy to understand.

### Similar purpose, but different usage ⚙
On the technical note, *Serial* and *Natural* are nothing but *two interfaces*, with the Serial interface *extending Natural*; from now on, elements which will be referred to as "serials" or "naturals" will be those types *which will implement either Serial or Natural*.

Those two interfaces contain only one method each: the Natural interface contains the *.nature() method*, returning an *integer*; whilst the Serial interface has the *.id() method*, returning a *long* (obviously, due to inheritance, the Serial interface will contain the *.nature() method*, too).

With technical things out of the way, the wait is finally over: it's time to describe what these two magical interfaces do!

### Natural Interface 🏷
To begin with, naturals furnish *an arbitrary number* which *identifies the general aspects of the type that implements them*. In order to use this "special number", the *.nature() method* must be called.

To make things more clear, that number will be referred to as *the nature* of a specific natural. The first *layer of distinction* directly depends from the nature: two naturals can *have the same nature* or *it can be totally different*; the usage of this number is the key to an efficient and functional usage of those types.

> NOTE: Further on, you will encounter the word *"type"*: which simply means that we're referring to *an object (or their respective class class, it's the same)*. Speaking about objects being *of the same type*, it just means either *that one extends the other* or *that they extend the same super class (which is also considered of the same type)*.

An *equality* between two different types having the same nature can make them *have a factor in common*; following the same idea, a *difference* between two natures can make two classes of the same type *have some difference*. We're speaking about a trait that *surpasses the concepts of inheritance*: those two types will never be considered "similar" or "different" from the language's perspective, but they'll appear similar *under our perspective*... and that's what counts!

This is what the nature does: makes *totally different types* have *something in common* or makes *"similar types" have differences*. That difference (or similarity) is totally *arbitrary* and all the naturals are beforehandedly classified in order to return certain natures according to the framework's necessities.

#### Why Naturals? 💭
The main use for this feature is to *add another way of distinguishing types from each others*. This interface was thought in order to prevent the usage of *instanceof*, if we consider the nature as the way of comparing two different types, the instanceof keyword *would not give the same result*: if the natures were different but the objects were of the same type (and vice versa, same nature, not of the same type), the results would differ.

The second reason why I've come up with this feature is to *have similar features grouped*: if multiple naturals share the same nature they can be identified as if part of an hypothetical *bigger group of types*, all having something in common. Normally, objects would achieve similarity through inheritance, but naturals get over this distincton between types.

On the final note, remember that Moona tends to consider more the nature of objects than their type: it uses their natures in order to classify them, performing different operations for each different nature.

#### How To Use Them? ✏️
Naturals, as every other object in this framework, were structured for you to interact with them, too. Inside the [Moona Class](moona-class-), there's a series of *constants* which contains *all the natures* which Moona uses: for example, the nature identifying a *Process* is *0* (check the [Moona.PROCESS](https://github.com/anOsuPlayer/Moona/wiki/Moona#fields-) field in the wiki, along with it you'll find the rest, too).

### Serial Interface 📝
If naturals describe an object in a general way, every serial object *is described with an ID* which identifies it and makes it different from all the others (even if it were of the same type).

Before going on, I'd better remind you of something very important, which is that *serials are also naturals*: if a certain type specifies an ID, it *also* has to specify *a nature*, "classifying" that specific object on both a *general* and *specific* aspect. This is the reason behind why the *Serial interface extends the Natural interface*, inside Moona there cannot be a clear distinction between two object which generalities are not known.

The Serial interface contains the *.id() method*, which returns a long number identifying a certain object (its *ID*). In classes belonging to the framework, they are *never* arbitrarily assigned, but there's a method in the Moona Class which generates them accordingly to how many were already requested.

As mentioned, serials add *another layer of distinction* between objects: if a nature identifies *a large group of objects*, an ID, on the other hand, helps *telling objects apart from each other*. The concept might seem ratherly useless but, believe me, it's not: since each serial has its own unique identifier, via the Moona Class is possible to *recall serials by knowing their ID*: if a serial element [is added](#moona-class-) to the Moona Class, it automatically becomes *dependent from its ID*.

Since IDs are unique for every single serial, their purpose is not limited to a mere distinction anymore, they now offer as *a mirror* to their correspondent Serial. Having access to an ID also means *having access to their related object*, even if it weren't available to a regular access (e.g. if it were private, somewhere not reachable).

#### Why Serials? 💭
I came up with Serials when I was thinking about a way to make accessing important objects an easier task. Not only this feature makes up a quick way to gain access to certain objects, but also makes them unique when it comes to both *distinction* and *identification*.

They're planned to remain simple: no though concepts to keep in mind, just a simple method which can improve quite a lot of quality of life aspects. On the final note, as always, the best way to understand this simple (yet useful) feature by is giving it a try.

#### How To Use Them? ✏️
If you were to create serials of your own, remember to *never* decide the ID of a serial as a default value: use the *[Moona.GenerateID()](https://github.com/anOsuPlayer/Moona/wiki/Moona) method* in order to avoid making a mess.

For the rest, it's all fairly simple: use the *.id() method* to obtain the ID of a Serial and, via the Moona Class, recall one by *calling its ID* (visit the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona) to find out some nice method to do this stuff).

## Processes
> [^ back](#moona-guide-)

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
> [^ back](#moona-guide-)

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
