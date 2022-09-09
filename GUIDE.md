# Moona guide

Here you'll find everything you need in order to fully understand and start using Moona! Here you'll be introduced to the main framework's features and functionalities, for you to have a better understanding of what to do and how to do it. In this file you will not find any kind of documentation, for that you can consult the [official wiki](https://github.com/anOsuPlayer/Moona/wiki) here on GitHub!

# Index

* [Introduction](#introduction)
* [Packages Organization](#packages-organization)
* [Dependencies](#dependencies)
* [Nature and IDs](#nature-and-ids)
* [Moona Class](#moona-class)
* [Processes](#processes)

## Introduction:
> [^ back](#moona-guide)

In order to fully understand how this framework is structured, you'll need to first comprehend a couple of important concepts. These concepts are those which *the framerwork is built on* and, consequently, they're something you'd better know. Many of these features tend to be explained descending very much into detail, so if your plan is to just look for a particular class I'd recommend you checking the [wiki page](https://github.com/anOsuPlayer/Moona/wiki) instead.

You'll be introduced to how the [*packages*](#packages-organization) are organized and then you'll see how [*dependency*] works and how it improves modularity aroun releases. Then there is an explanation to the first object-like structures that will be mostly essential to you using Moona, those being [*Serials and Naturals*](#nature-and-ids) followed by the [*Moona class*](#moona-class) itself, the one that rules the whole framework and manages most of the elements.

I'm sure that many of these terms might sound a bit... unusual... in a framework's introduction (like, I'm sure that the average Java developer knows what a regular packages are organized) but, regardless, I want to try to describe with my own words the work I've done, so that you can have the best possible experience when using this framework.

This being said, dear guide-consulter, I want to thank you for giving this framework a shot and I wish you a good experience using Moona!

> *The Developer*

## Packages Organization:
> [^ back](#moona-guide)

The first thing I want you to have in mind is the *package organization*: the way packages and classes are organized. I think this is one of the most important things if you want to quickly access the features you are looking for (without endlessly searching for a something, wasting time).

### Listing Packages

> NOTE: Some packages will be marked as *dependent*, *essential* and *not-dependent*, learn more about what these terms mean by consulting the [dependencies](#dependencies) paragraph in this file.

In the first place, there is the most important package of them all, containing all the others: the **moonaFramework** package. This particular one is the root of all the other sub-packages (which means all the other packages will be named *moonaFramework.(something)*. for practical reasons, you'll see only their name). This package also contains the *root classes* of the framework (like, for example, the [Moona class](#moona-class), which you'll see in a bit).

Below this text you'll find a list containing all the packages in this framework. Each of them will have a link that will lead you to a *wiki page*, that wiki page will answer all your questions about the classes in it.

* *[dependent]* **.process** package: Contains the whole *process framework* part of Moona. You'll be able to learn more about Processes in the relative page.

* *[essential]* **.util** package: It's the part of Moona which contains all those objects that *serve specific purposes around the framework, but are not specific enough to be put somewhere else*. Jokes aside, this package basically consists in a collection of objects that are meant to make Moona's features more accessible and easy-to-code. Also, they are meant for programmers to use them as they please, wherever they want (some of them being, in my opinion, very useful, they'll hopefully satisfy some of your needs). 

> NOTE: as new packages will be added, I will immediately add them to this list followed by the usual brief description!.

### Some Practical Use...

As mentioned above, words such as *dependent*, *essential* and *non-dependent* will be clarified further on but, for now, I will just briefly talk about the way all those packages will be organized upon a release:

* *non-dependent AND essential packages* are going to have a **standalone release** as a .jar file released along with the full .jar containing Moona as a whole. This was thought in order to make people access only features they're looking for when not interested to download the entire framework (which, with each passing update, might get *very heavy* in terms of download size, too).

* *dependent packages* will **NOT** be released as standalone .jar files, this is because of their objects *directly depending from the Moona class* (to keep this brief, dependent objects are those that *cannot live without the Moona class*).

To look after some more details regarding how the releases/downloads work, please consult the [README.md](https://github.com/anOsuPlayer/Moona/blob/early_dev/README.md) file in the repository.

## Dependencies:
> [^ back](#moona-guide)

I'm pretty sure that, if you came here, you will not be surprised to be introduced to this topic: I bet you'll have read of those freaky terminology all around the framework. You will now be introduced to the concept of *dependency* that I came up with. In simple words, dependency tells us *how much classes and packages are strictly related to Moona*. By "strictly" I mean *how do those elements DEPEND on Moona* (either to work properly or to *make Moona itself* work properly.

If you're not planning into diving too much into details, I'd recommend you to just check what's written in the [previous paragraph](#packages-organization), there you'll find enough information. But if you're here to really unravel each of this framework's aspect, then keep reading!

### A closer look at Keywords

One of the most used terms I've written throughout the whole documentation might actually be *"dependent"* or *"non-dependent"*... but what do these things mean?

It's actually really simple: those keywords are just *terms that were made to measure dependency* for each class and package. In a few words, you can view them as a simple "expression" of how closely-related they are to the framework. You'll find them all right here:

#### **DEPENDENT:**
An element is referred to as "dependent" when *it CANNOT LIVE outside of the framework's context* or, even better, *its functionality WOULD NOT BE AS EFFECTIVE as if it was alongside Moona*.

Maybe a practical example will make your idea a bit more clear, let's picture a [Process](#processes): processes were thought to give their best performance when cooperating with the [Moona Class](#moona-class), all their starting, (un)pausing and interrupting procedures are specifically designed inside the latter and, thus, using them in an extern context would result in a real mess (NOTE: you *could* make a process work outside of Moona, but it would end up being really tricky for you to make it work the right way)

To sum things up: dependent elements *are better not to use* outside of the framework context: they're designed purposely to give their best when strictly bound to Moona and, as a result, using them by themselves really makes no sense.

#### **NON-DEPENDENT:**
For an element to be called *non-dependent*, it needs *to be able to work properly OUTSIDE the framework's context* (with "the framework context" I'm saying some of your projects that includes the framework's package, giving the object access to the Moona class).

Those are really straight forward: you might want to think about non-dependent objects as *pure regular objects*: they have close to no relation with Moona and they can *live by themselves* even outside of the framework.

As already mentioned in the [README.md file](https://github.com/anOsuPlayer/Moona/blob/early_dev/README.md), those packages which will be *non-dependent* will be *released in A STANDALONE RELEASE, too*. Those features will live all together inside a separated .jar file, which you'll be able to download when every new update comes out (NOTE: *EVERY non-dependent package* will have its own small release). I had in mind this feature in order for people to really get what they most like or what they most need out of this framework.

#### **BASIC:**
There is not much to need on this dependency type: all the *basic elements* are *those CONTAINED INSIDE THE moonaFramework PACKAGE (INCLUDED)*, which you'll be able to see right [here](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework). It's a bit of an obscure property, since it only regards those elements which make up the very base of the framework.

Basic elements *ARE ALSO DEPENDENT:* being them the framework itself it would not make any sense if.. elements that make the framework could live *without the framework*... is this some kind of paradox, maybe.

Also, those elements *follow the same rules of non-dependent elements*: they also can live outside of the framework's context (REMEMBER! they are *essential to Moona*, this means that *MOONA NEEDS THEM*, but *they DON'T NEED MOONA*!) and essential packages will feature their own *standalone release*, too.

Those terms are a bit funky, I know. I came up with them in order to make people's lives easier when downloading features. Well, now that this terminology is out of the way.. **more terminology awaits you ahead**. Jokes aside, now you can consider yourself really ready to start learning *the first basic elements of the framework.*

## Nature and IDs:
> [^ back](#moona-guide)

Moona features a great variety of different objects, each of them doing something specific. Among those elements the most important are [Serial](https://github.com/anOsuPlayer/Moona/wiki/Serial) and [Natural](https://github.com/anOsuPlayer/Moona/wiki/Natural) elements. They are very useful when it comes to *identify a framework's object in a specific way*, that being *the definition of an arbitrary "type" of it* and *the declaration of an unique number for that particular instance*.

### Similar purpose, but different usage

*Serial* and *Natural* are two Interfaces and *Serial EXTENDS Natural*. They're both needed to have methods that *return specific numbers that describe the object*, but they do it in a different way one from another:

> If a type implements *Natural*, this means that it *is required to return a specific number that distinguishes that type from other types*.

Let's create a quick example: you want to create two different types, A and B, *both implementing Natural*; let's suppose that these types are distinguished by two different "natures", e.g. the method *nature()* in the A class returns **1** whilst the one in the B class returns **2**. This might seem a bit.. useless.. since A and B are two completely different objects there is absolutely no need to make them different from each other even further... or is it?

*What if the B class extended the A class*? Let's picture this scenario: you have created a method which *returns true* only if passed objects are *instances of the A class* (something like **boolean isA(Object obj) { ... }**, it states something like this in its body: **if (obj instanceof A) { return true; }**; if that was the case, both instances of A and B *would be accepted in this statement*, since B extends A and, thus, the returned value would be *true*.

Let's now imagine a somewhat more *particular case*: we want to create a method that returns true *ONLY when an instance of A is given as an input* (NOTE: a *direct instance*, which means no subtypes are allowed). It would be almost obvious to write something like this: **boolean method(A obj) { ... }**, accepting only *A types* and then filtering them with countless *instanceof* to see if they're not subtypes... but what if that method required to accept *different types*? What if, for each *different type* a *different operation* was required? Of course, in a case like this, accepting an *Object* as argument and then checking each case with the *instanceof operator* would do the trick... but would, also, take up a lot of performance.

Here comes in help the *Natural type*: since A and B *are both Natural*, our hypothetical method could accept *a Natural instead of an Object*; from there, by just switching the value of the *.nature() method* we're able to tell apart *the nature* of that specific object. Even if the method *accepted a generic Object*, you could tell beforehand if that *was an instance of Natural* and, then, filter the .nature() method's output to do stuff. This operation (on the long term) should be able to save a lot of time in terms of performance.

Another possible use for the Natural type would be to *declare as similar two completely different objects*. For example, if both the A and B classes (*not inheriting each other in any way*) were to return **1** from the .nature() method, you would be able to count them as *equal* or, to use some fancy terminology, *of the same nature*. This would lead to some very neat and specific operations that you could perform with those types, but that's up to your creativity, of course!.

***

> If a type implements *Serial*, *it requires BOTH to return a specific nature AND to return a specific ID which distinguishes it from all the others.*

Serial objects extend the concepts already stated in the previous paragraph, this is because, as already mentioned above, *Serial objects are ALSO natural*. But what is their real purpose? I mean, in order to just tell two objects apart the .equals() method would be enough... rigth? Not quite.

Each Serial needs to declare the *.id() method*: this specific method is the one that returns the aforementioned *ID* as a long number needed to tell one Serial from another. But here comes the cool thing, that specific number *is assigned by the [Moona Class](#moona-class)* inside the constructor of each serial object. You don't think that's cool? Well, what if I told you that *Moona can interact with those IDs*? 

Let's make things clear before going on, what does it mean that *Moona can interact with Serials*? To explain you this concept, I will need to once again *blow your mind* by unraveling another one of the Serial objects' secret: *each of them is stored **inside a "List" which resides inside Moona***. By DEFINITION, *A Serial object is an object that declares a specific ID AND is stored inside Moona*.

As you'll be able to see further on, the Moona Class features a lot of ways to *interact with IDs*. The first thing worth mentioning is that **you are able to recall EVERY SERIAL OBJECT if you know its ID**, thanks to methods that accept a long value and that return the corrisponding Serial. This, of course, applies to a lot of various uses.

As I did before, I'll show you a practical use of this class with an example. Let's imagine two instances **A and B** of a class **C** which *implements Serial*, the two instances have two different IDs: **1 and 2**. Imagine that these two instances are two *private fields* inside of a generic **D** class (NOTE: "generic" does not imply *Java Generics*!) and that inside of the C class there is a method, which takes as an input a Serial and that... *does something*, a method like this: **void acceptSerial(Serial s)**.

Since A and B are *two private fields inside of another class*, there would be no chance to call them from elsewhere... *maybe*. Thanks to a method called *.getElementByID(long id) { ... }* inside of Moona which returns the Serial corresponding to the given ID, we're able to *access to that private element by ONLY KNOWING ITS ID*, doing something like this: **acceptSerial(Moona.getElementByID(1))** (to know more about methods such as this, you can go check the [Moona Class' Wiki page](https://github.com/anOsuPlayer/Moona/wiki/Moona)). This operation will grant you the serial you're trying to access even if the latter *cannot be regularly accessed* though direct call.

Each "call by ID" is made possible since, as already mentioned before, *each Serial ALSO lives inside the Moona class*. As you'll get your chance to see, Serials are implied in a lot of tasks inside of the framework and, even though I've not covered each one of them here, you'll be able to try all those features by yourself, I'm 100% convinced that's the best way you can learn!

## Moona Class:
> [^ back](#moona-guide)

## Processes:
> [^ back](#moona-guide)
