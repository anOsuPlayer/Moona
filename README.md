# Moona 🌙

> NOTE: This project is in its VERY *VERY* EARLY STAGES. There is NO RELEASE YET and some features haven't been covered yet.

Welcome to Moona! This project is my very own multi-purpose framework that will feature a whole bunch of tools and classes for all sorts of purposes: from building graphic interfaces up to simply keeping your code more compact and clear!

My hope is to develop this framework as best as I can: I want to offer people a piece of code that not only performs well, but that's also handy, practical and useful at the same time. I do not aim on creating something perfect, but rather something that you might appreciate because of its various uses and functions.

In the future, I would really like to give this framework a graphic interface (with integrated IDE and other things) and, also, to make other versions using other programming languages (like C++). We're talking about a very far future, though... who knows if this'll happen.

## How do I get started? 🔎

In order to get started with this framework you'll need a couple things:

* This framework uses the *Java JDK 18.0.2*, so you'll need to download it from the official [website](https://www.oracle.com/java/technologies/downloads/) (NOTE: if you like, you should be able to use some newer versions of the JDK, too. I hope that it won't cause troubles, though, I haven't tested it yet)

* To avoid making a mess in your IDE and making you regret of your life choices, I would advise you to read the official [guide](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) on how to use the framework (which you can find by clicking on the link or in the repository's files) and the [Wiki](https://github.com/anOsuPlayer/Moona/wiki/home).

Now that you covered the basics, you are ready to take your first look at the [releases tab](https://github.com/anOsuPlayer/Moona/releases) to choose the *release* that most suits your needings! ... but first, let me introduce you to *release templates*, so that you'll know what you're going to download.

## Release Templates :package:

This fancy term you're reading up here it's just a nice word to identify two different routes you can follow when downloading Moona. In order to achieve a nice *modularity* with all the features I'm planning to implement, I thought that this idea of mine might make things easier to a lot of you. In a few words, your choice is between *the whole framework* OR *some features that you like*.

You'll just have to choose between the following two ways:

### First Template:
**Choosing the framework as a whole:** If you'll decide to download the main package (labeled: "Moona.jar"), you'll be downloading *the framework as a whole*. This means that your download will include *the base package* along with *all the dependent and non-dependent packages* (every feature from every package, basically).

### Second Template:
**Choosing standalone features:** If your idea is to download single features of this framework to use them somewhere else, then your best option is to download *singular standalone packages* (labeled: "Moona-[something]"). Those packages *are NOT strictly bound to Moona*, so they can also be used in projects of yours that do not directly involve the framework. NOT ALL THE PACKAGES WILL BE AVAILABLE AS A STANDALONE, only the *non-dependent ones* will, keep this in mind!!

> NOTE: for a better understanding of how the package structure works and of what "dependent" and "non-dependent" actually mean, I suggest you to check the [guide](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) attached to this repository.

Finally, in order to *avoid any kind of tragic issue*, remember those last few things:
* Downloading the full framework package **DOES NOT require any extra feature to be imported**.
* For each package you're going to download, **EACH ONE OF THEM has to be imported following [this](#working-with-moona) procedure**.
* Standalone packages **DO NOT REQUIRE the base package to be installed**.
* **ALWAYS CHOOSE** between one of the aforementioned tempates.
* **DO NOT import .jar files if not needed** (for example, do not import extra packages in the full installation or the same package multiple times).

## Working with Moona 💡

After choosing which packages to download, allow me to guide you through a *simple tutorial* on how *import .jar files in your projects* (which is required to access all the features Moona offers). This tutorial covers the steps you have to go through if you use *Eclipse IDE*, if you happen to use another IDE for coding Java, do not worry: just search on google *how to import .jar files* in your IDE of choice, you'll be good to go.

This procedure does not feature any explaination or "spoiler" about some of the framework's elements: is purely a tutorial to help those of you *non-nerdy-programmers* to setup Moona. If you happen to already know about how to import .jar files, then you can skip this part without worrying.

If you decided to stay, here's your fully detailed tutorial:

1. First things first: hop in the [**releases tab**](https://github.com/anOsuPlayer/Moona/releases) and choose you favourite download template for Moona (*full framework* or *base package + something else*, as shown before).

2. Now that you have downloaded your files, head into Eclipse and **choose the project that you're going to work with**: either create a new one or use one that you already have.

3. Once the choosen one has been... well... *chosen*, you'll need to **locate the project folder in your file system**, there are two ways to do it:
    * Using the endless power of *your immense memory*, your extraordinary brain knows *where your Eclipse Workspace is*. Locate the previously mentioned project's folder inside of it and open it.
    * If you have no idea of where your workspace is (either because *you forget* or because *you never thought you'd care*), do not worry, I've got you. Just right click on your Eclipse project and then click on **Show In > System Explorer**: this will open a window of your system's file explorer that will show you an highligted folder.. YOU FOUND IT. **Double click it** and get inside your project folder.
    
4. At this point, **create a folder called *"lib" (WITHOUT QUOTES)*, if you don't have it already in that project**, do this inside your project's root directory (the aforementioned folder).

5. Moving inside the **lib** folder, **put ALL the Moona .jars inside this folder** (for better organization, you are free to create any number of *sub-folders* inside of the lib directory: this will not matter as long as *you'll keep in mind where all the files are located*).

> NOTE: The location you choose to put your .jar files into is *your choice*: the folder *does not need to be necessarily called "lib"*, it's just a convenient way to name the folder which contains libraries or extra stuff for your projects.

6. Once the lib folder is properly set up, hop back into Eclipse and, firstly, **right click on your project's folder** and then choose **Refresh**. Then, right click again but, instead, go for **Build Path > Configure Build Path**. You'll be presented with a newly opened window, here you'll want to **left click once on the label stating "Classpath"**. Once that is selected, you'll have to **press the Add Jars button (on the right side of the window)**.

7. At this point, just **click on your project's folder** to open an *"explorer-like view"* of its folder. Click on the lib folder and then, *remembering WHERE YOU PUT THE FILES (from step 5)*, search for all of them and **highlight them**. Once that is done, click the **OK button** and then go for the **Apply and Close** button.

8. Finally, right click on your project's folder once again and **click the "Refresh" button**.

GOOD JOB!! now you can start using Moona!

## Versions 📑

Before diving head first into using the framework, let me just clarify a couple things about how versioning will work for each release.

For now, there are not any public releases yet (because of the framework being still too rough and under development to be published), but, regardless, I want to show the way I plan on releasing new versions to the public. Below this paragraph you'll find a list of all the phases I plan the framework to go through:

### early_dev versions ⚛️
The *early_dev* era is the one where *the framework will be shaped* and where *all the base elements* will be added. Releases will remain under this condition until Moona will reach enough features to move on. Those versions are likely to have many issues... I mean.. they're *early_dev version* for a reason, after all.

### alpha versions 🛑
Once the early_dev phase will be over, releases will start to be labeled as *alphas*. An alpha version is... *very unstable*: their stability will vary depending on how many features are added and on *how many bugs get spotted*. Alpha versions are a prelude to *betas*, more stable and closer to the final release.

### beta verisons 🔱
As said in the previous paragraph, *beta versions* are those which are the closest to stable releases. The framework will enter its beta stage only once the ammount of features will be satisfying and the stability will be acceptable. With each new beta release, the main focus will be to *stabilize already existing features*.

### release versions ✅
Releases, as you might guess, are *full stable releases of the framework*. Releases will start to be dropped *after the beta phase ends*, meaning that there are going to be lots of fixes to all the features before achieving stability. Once the first release will be published, *no more beta and alpha versions will be dropped*.

### build versions ♾️
After a release, as said before, there will be no more versions such as alpha and betas. At their place *builds* and *snapshots* are going to get released. A new set of builds will begin to be released when *new big features* will be added to the framework. To have a better idea, they're just like *alphas*: they'll introduce a great variety of new stuff for you to test and play with but will also be very unstable.

### snapshot versions 🌀
To end the apparently endless list of possible versions, let me introduce you to *the snapshot*. Snapshots are versions which follow *build versions*: after a build will introduce a new set of features, future snapshots will help to make them more stable, preparing the project to reach its next big release.

After a good ammount of snapshot and (hopefully) a satisfying number of issues solved, a new stable release will be published and, from there on, the procedure will repeat seamlessly *until the end of time* (set of builds > snapshots to bring stability > new big release > repeat).

As a final note, each release will be tagged as following:
* The first thing you'll see in the release's title will be *the kind of version you're dealing with*.
* Following, you'll see the *the tag* of that precise version.

An example of what was explained above would be something like this: *alpha-0.12* or *release-1.0*.

## What if I need help? 📚

In case you were lost, desperately trying to work out how a certain feature works or *why* something is developed in a specific way, there's nothing to fear: you'll probably find your answers by doing something like:

* Checking the [GUIDE.md file](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) will most likely answer to some technical questions: if you're confused on some aspects of the framework that you don't fully understand (such as *modularity* or *dependency*), I highly recommend you to go for a read on that file.

* Consulting the [Wiki](https://github.com/anOsuPlayer/Moona/wiki) here on GitHub will cover your lack of knowledge if your goal is to know one of the framework's feature in particular. There you will find ALL the classes that this framework offers you together with an updated and on-point description to let you know every obscure detail of every feature.

* Taking a look at the [Issues tab](https://github.com/anOsuPlayer/Moona/issues) or the [Pull Request tab](https://github.com/anOsuPlayer/Moona/pulls). If luck doesn't happen to be on your side and you're dealing with some weird, scary looking error, here on the website you will (most likely) find yourself a workaround. If you're lucky, someone already had such an error and therefore you'll just need to read how to solve it in the related tab; if not, you're free to post a pull request or an issue stating the problem that occurred to you and either me, the developer, or the community will try to help you.

## Contributing to the Project 🎓

#### ***NOTE: the project DOES NOT DECLARE ANY KIND OF LICENSE YET. It will be added in the future as soon as I have a better idea of how they work and on what they allow the community to do.***

Even though there is no license yet, I want to give the community a possibility to contribute to the project. Via *pull requests* and *issues* tab, I will kindly accept any kind of advice, idea or constructive opinion!

## Thanks! :heart:

Thanks for reading this file until the end. This might not be the best code you'll find around, but, on the other hand, it's the best I can offer you. This project will never be perfect, but I will try my best to keep up with the expectations (if.. someone will ever expect something out of this framework). For now, though, enjoy Moona... Happy coding!

> *The Developer*
