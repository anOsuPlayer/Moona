# Moona

Welcome to Moona! This project is my very own multi-purpose framework that will feature a whole bunch of tools and classes for all sorts of purposes: from building graphic interfaces up to simply keeping your code more compact and clear!

My hope is to develop this framework as best as I possibly can: I want to offer people a piece of code that not only performs well, but that's also handy, practical and useful at the same time. I do not aim on creating something perfect, but rather something that you all can appreciate because of its various and peculiar uses or functions.

#### ***Moona is still under heavy developement, many of the features listed above are not yet implemented (but they'll be there... soon).***

Speaking about very far-in-the-future plans, I would really like to give this framework a graphic interface (with integrated IDE and other things) and, also, to port it along with all its features to other programming languages (like C++). We're talking about a very far future, though... who knows if this'll happen.

## How do I get started?

In order to get started with this framework you'll need a few things:

* This framework uses the *Java JDK 18.0.2*, so you'll need to download it from the official [website](https://www.oracle.com/java/technologies/downloads/) (NOTE: if you like, you should be able to use some newer versions of the JDK, too. I hope that it won't cause troubles, though, I haven't tested it yet)

* To avoid making a mess in your IDE and making you regret of your life choices, I would advise you to read the official [*guide*](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) on how to use the framework (which you can find by clicking on the link or in the repository's files).

Now that you *"really know what you're doing"*, you are ready to take your first look at the [releases tab](https://github.com/anOsuPlayer/Moona/releases) to choose the **release template** that most suits your needings to begin with! ... wait, what's a *release template*...?

## Release Templates

If you decided to download a Moona release, then I'd better lighten your mind a bit by telling you *how releases work*. It's really no complex thing: you'll just have to choose between a way to organize things or another.

While thinking about this project, I also took into count some very neat *modularity feature* (the reason behind those templates). I thought that it would have been nice to *allow people to download standalone releases of non-dependent packages to use them outside of the main framework, too*. For each major release, you'll have the following options to choose between when the download time will come:

### First Template:

* **Choosing the framework as a whole:** If you'll decide to download the main package (labeled: "Moona.jar"), you'll be downloading *the framework as a whole*. This means that your download will include *the base package* along with *all the dependent and non-dependent packages* (every feature from every package, basically).

### Second Template:

* **Choosing the base framework package:** If you plan to make a custom build of Moona, only downloading certain packages that you need, you'll have to download the base package first (the one labeled "Moona-Base.jar"). The base package includes *all the dependent packages* and leaves out *ALL the non-dipendent packages*. In order to add more features, you'll need to add to your brand new configuration some *standalone .jar*, which will be described next.

* **Choosing some standalone features:** If your idea is to download single features of this framework, then your best option is to download *singular standalone packages* (labeled: "Moona-[something]"). Those packages *are NOT strictly bound to Moona*, so they can be used in every project of yours that does not directly involve the framework. NOT ALL THE PACKAGES WILL BE AVAILABLE AS A STANDALONE, only the *non-dependent ones* will, keep this in mind!!

> NOTE: for a better understanding of how the package structure works and of what "dependent" and "non-dependent" actually mean, I suggest you to check the [*guide*](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) attached to this repository.

Finally, in order to *avoid any kind of tragic issue*, remember those last few things:
* Downloading the full framework package **DOES NOT require any extra feature to be imported**.
* If you plan on downloading scattered packages, **EACH ONE OF THEM has to be imported following [this](#working-with-moona) procedure**.
* Standalone packages **DO NOT REQUIRE the base package to be installed**.
* Always choose between one of the two tempates: **EITHER** download the full package **OR** make your own custom build. **DO NOT import .jar files if not needed** (for example, do not import extra packages in the full installation or the same package multiple times).

## Versions

Before diving head first into using the framework, let me just clarify a couple things about how versioning will work for each release.

For now, there are not any public releases yet (because of the framework being still too rough and under development to be published), but, regardless, I want to show the way I plan on releasing new versions to the public. Below this paragraph you'll find a list of all the phases I plan the framework to go through:

* **early_dev :** The first phase of development, where most of the features will be builded and where the framework will take its initial shape.

* **alpha :** Once Moona will reach a satisfying ammount of features I will proceed with the alpha phase, where I will focus on both adding new features and making the ones added in early_dev more perfect.

* **beta :** The closest version to the full release, where the main focus will be to get as close as possible to a fully functional release.

* **release :** The actual full version which is (or rather, *should hopefylly be*) fully stable and safe to use.

* **snapshots :** Between each major release, snapshots will be released to introduce new features (they'll have the same purpose of alphas and betas, but they'll actually be based on stable code, with the only exception of the newly implemented features).

Once each new release is ready, I will put one of these keywords in the tag, in order to make more clear what kind of version you'll download (an exemple of release tag would be something like: *release-1.0* or *alpha-0.2.1*).

## Working with Moona

Now you've covered the basics, understanding how everything in this framework works, it might be the time to actually start *coding something of your own*. In order to access to Moona's features you'll need to *[choose a release](#release-templates)* and then to *import (all) the .jar file(s)* into your favourite IDE. The following paragraph will show you how to import .jar files into Eclipse IDE, in case you needed it (if you plan on using a different IDE to code with Moona, then looking out for some help on google might help you. In case of some weird problem, feel free to use the [issues tab](https://github.com/anOsuPlayer/Moona/issues)).

1. First things first: hop in the [**releases tab**](https://github.com/anOsuPlayer/Moona/releases) and choose you favourite download template for Moona (*full framework* or *base package + something else*, as shown before).

2. Now that you have your files downloaded, head into Eclipse and **choose the project that you're going to work with**: either create a new one or use one that you already have.

3. Once the choosen one has been... well... *chosen*, you'll need to **locate the project folder in your file system**, there are two ways to do it:
    * Using the endless power of *human mind*, your extraordinary brain knows *where your Eclipse Workspace is*. Locate the previously mentioned project's folder and open it.
    * If you have no idea of where your workspace is (either because *you forget* or because *you never thought you'd care*), do not worry, I've got you. Just right click on your Eclipse project and then click on **Show In > System Explorer**: this will open a window of your system's file explorer that will show you an highligted folder.. YOU FOUND IT. **Double click on it** and get inside your project folder.
    
4. At this point, **create a folder called *"lib" (WITHOUT QUOTES)*, if you don't have it already in a project of yours**, do this inside your project's root directory (the aforementioned folder).

5. Moving inside the **lib** folder, **put ALL the Moona .jars inside this folder** (for better organization, you are free to create any number of *sub-folders* inside of the lib directory: this will not matter as long as *you'll keep in mind where all the files are located*).

6. Once the lib folder is properly set up, hop back into Eclipse and, firstly, **right click on your project's folder** and then choose **Refresh**. Then, right click again but, instead, go for **Build Path > Configure Build Path**. You'll be presented with a newly opened window, here you'll want to **click on a label stating "Classpath" one time, with your left click**. Once that is selected, you'll have to **press the Add Jars button (on the right side of the window)**.

7. At this point, just **click on your project's folder** to open an *"explorer-like view"* of its folder. Click on the lib folder and then, *remembering WHERE YOU PUT THE FILES (from step 5)*, search all of them and **highlight them all**. Once that is done, click the **OK button** and then go for the **Apply and Close** button.

8. Finally, right click on your project's folder once again and **click the "Refresh" button**.

GOOD JOB!! now you can start using Moona!

## What if I need help?

In case you were lost, desperately trying to work out how a certain feature works or *why* something is developed in a specific way, there's nothing to fear: you'll probably find your answers by doing something like:

* Checking the [GUIDE.md file](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) will most likely answer to some technical questions: if you're confused on some aspects of the framework that you don't fully understand (such as *modularity* or *dependency*), I highly recommend you to go for a read on this file.

* Consulting the [Wiki](https://github.com/anOsuPlayer/Moona/wiki) here on GitHub will cover your lack of knowledge if your goal is to know some of the framework's feature in particular. There you will find ALL the classes included in this framework together with a fully updated and on-point description to let you know every aspect of every feature.

* Taking a look at the [Issues tab](https://github.com/anOsuPlayer/Moona/issues) or the [Pull Request tab](https://github.com/anOsuPlayer/Moona/pulls). If luck happens to not be on your side and you're faced with some weird, scary looking error, here on the website you will (most likely) find your workaround. If you're lucky, someone already had such an error and therefore you'll just need to read how to solve it in the related tab; if not, you're free to post a pull request or an issue stating the problem that occurred to you and either me, the developer, or the community will try to help you.

## Contributing to the Project

#### ***NOTE: the project DOES NOT DECLARE ANY KIND OF LICENSE YET. It will be added in the future as soon as I have a better idea of how they work and on what they allow the community to do.***

Even though there is no license yet, I want to give the community a possibility to contribute to the project. Via *pull requests* and *issues* tab, I will kindly accept any kind of advice, idea or constructive opinion!

## Thanks!

Thanks for coming until the end of the file. This might not be the best code you'll find around, but, on the other hand, it's the best I can offer you. This project will never be perfect, but I will try my best to keep up with the expectations (if.. someone will ever expect something out of this framework). For now, though, enjoy Moona... Happy coding!

> *The Developer*
