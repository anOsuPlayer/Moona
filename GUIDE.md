# Moona guide

Here you'll find everything you need in order to fully understand and start using Moona! Here you'll be introduced to the main framework's features and functionalities, for you to have a better understanding of what to do and how to do it. In this file you will not find any kind of documentation, for that you can consult the [official wiki](https://github.com/anOsuPlayer/Moona/wiki) here on GitHub!

# Index

### Basics:
* [Introduction](#introduction)
* [Moona Class](#moona-class)
* [Packages Organization](#packages-organization)
* [Dependencies](#dependencies)

## Introduction:
> [^ back](#moona-guide)

In order to fully understand this framework, you'll need to start by knowing about some concept that will be crucial when using Moona. First thing, you need to have a quick look at how the [*package organization*](#packages-organization) is organized, in order to know where to search for some features and, most importantly, to know about how packages are linked to Moona; second thing, you'll need to learn about the [*Moona class*](#moona-class): that is the class which rules the whole framework and that, most importantly, *makes it work*... it manages the whole branch of [*dependent objects*](#dependencies), too (such a cool class).

I'm sure that many of these terms might sound a bit... unusual... in a framework's introduction (like, I'm sure that the average Java developer knows what a regular package hierarchy looks like) but, regardless, I want to try to describe with my own words the work I've done, so that you can have the best possible experience when using this framework.

This being said, dear guide-consulter, I want to thank you for giving this framework a shot and I wish you a good experience using Moona!

> *The Developer*

## Packages Organization:
> [^ back](#moona-guide)

The first thing I want you to have in mind is the *package organization*: the way packages and classes are organized. I think this is one of the most important things if you want to quickly access the features you are looking for (without endlessly searching for a something, wasting time).

> NOTE: Some packages will be marked as *dependent*, *essential* and *not-dependent*, learn more about what these terms mean by consulting the [dependencies](#dependencies) paragraph in this file.

### Listing Packages

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

## Moona Class:
> [^ back](#moona-guide)

## Dependencies:
> [^ back](#moona-guide)
