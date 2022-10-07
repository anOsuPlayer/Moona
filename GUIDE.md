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

The Serial interface contains the *.id() method*, which returns a long number identifying a certain object (its *ID*). In classes belonging to the framework, they are *never* arbitrarily assigned, but there's a method in the Moona Class which generates them accordingly to how many were already requested. IDs are *always stored inside a field in every object*; since the ID is required to *not change in time* is always saved like this (in many cases it's declared as final, too).

As mentioned, serials add *another layer of distinction* between objects: if a nature identifies *a large group of objects*, an ID, on the other hand, helps *telling objects apart from each other*. The concept might seem ratherly useless but, believe me, it's not: since each serial has its own unique identifier, via the Moona Class is possible to *recall serials by knowing their ID*: if a serial element [is added](#moona-class-) to the Moona Class, it automatically becomes *dependent from its ID*.

Since IDs are unique for every single serial, their purpose is not limited to a mere distinction anymore, they now offer as *a mirror* to their correspondent Serial. Having access to an ID also means *having access to their related object*, even if it weren't available to a regular access (e.g. if it were private, somewhere not reachable).

#### Why Serials? 💭
I came up with Serials when I was thinking about a way to make accessing important objects an easier task. Not only this feature makes up a quick way to gain access to certain objects, but also makes them unique when it comes to both *distinction* and *identification*.

They're planned to remain simple: no though concepts to keep in mind, just a simple method which can improve quite a lot of quality of life aspects. On the final note, as always, the best way to understand this simple (yet useful) feature by is giving it a try.

#### How To Use Them? ✏️
If you were to create serials of your own, remember to *never* decide the ID of a serial as a default value: use the *[Moona.GenerateID()](https://github.com/anOsuPlayer/Moona/wiki/Moona) method* inside of the type's constructor to declare a field: this will make so that the ID will be different for each object, but it will not change during time.

For the rest, it's all fairly simple: use the *.id() method* to obtain the ID of a Serial and, via the Moona Class, recall one by *calling its ID* (visit the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/Moona) to find out some nice method to do this stuff).

## Processes 💾
> [^ back](#moona-guide-)

## Moona Class ❤
> [^ back](#moona-guide-)
