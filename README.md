# Moona

Welcome to Moona! This project is my very own multi-purpose framework that will feature a whole bunch of tools and classes for all sorts of purposes: from building graphic interfaces up to simply keeping your code more compact and clear!

My hope is to develop this framework as best as I possibly can: I want to offer people a piece of code that not only performs well, but that's also handy, practical and useful at the same time. I do not aim on creating something perfect, but rather something that people can appreciate because of its various and peculiar features.

#### ***Moona is still under heavy developement, many of the features listed above are not yet implemented (but they'll be there... soon).***

Speaking about very far-in-the-future plans, I would really like to give this framework a graphic interface (with integrated IDE and other things) and, also, to port it along with all its features to other programming languages (like C++). We're talking about a very far future, though... who knows if this'll happen.

## How do I get started?

In order to get started with this framework you'll need a few things:

* This framework uses the *Java JDK 18.0.2*, so you'll need to download it from the official [website](https://www.oracle.com/java/technologies/downloads/) (NOTE: if you like, you should be able to use some newer versions of the JDK, too. I hope that it won't cause troubles, though, I haven't tested it yet)

* To avoid making a mess in your IDE and making you regret of your life choices, I would advise you to read the official [*guide*](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md) on how to use the framework (which you can find by clicking on the link or in the repository's files).

The rest.. is really up to you! After telling you how this framework works, the next limit is nothing but your fantasy (and, of course, things like *compiling errors* and *your RAM*, but that's another story). Jokes aside, a good dose of trial and error is the best way for you to learn to use this framework, in my opinion.

## Working with Moona

Once you have a basic understanding of how everything in this framework works, you might want to start and actually code something of your own. In order to access to Moona's features you'll need to *choose a release* and then to *import the .jar file* into your favourite IDE. Following this paragraph you'll find a very handy tutorial on how to import .jar files into either *Eclipse IDE or IntelliJ Idea* (the most well-known editors when it comes to Java. If you plan on using another IDE you'll need to search on the good old google)

*(to be added SOON)*

## Releases Structure

In order to achieve a certain modularity inside this framework, I decided to *allow people to download standalone releases of non-dependent packages outside of the main framework*. For each major release, you'll have the following options when it comes to downloading:

* **Choosing the framework as a whole:** If you'll decide to download the main package (labeled: "Moona.jar"), you'll be downloading *the framework as a whole*. This means that your download will include *the base package* along with *all the dependent and non-dependent packages* (every feature, every package, basically).

* **Choosing the base framework package:** If you plan to make a custom build of Moona, only downloading certain packages that you need, you'll have to download the base package first (the one labeled "MoonaBase.jar"). The base package includes *all the dependent packages* and excludes *ALL the non-dipendent packages*. In order to add certain features, you'll need to download some *standalone jars too*, which will be described next.

* **Choosing a standalone release:** If your idea is to download single features of this framework, then your best option is to download *singular standalone packages* (labeled: "Moona-[something]"). Those packages *are NOT strictly bound to Moona*, so they can be used in every project of yours that does not directly involve the framework. NOT ALL THE PACKAGES WILL BE AVAILABLE AS STANDALONE, only the *non-dependent ones* will, keep this in mind!!

> NOTE: for a better understanding of the package structure, dependent and non-dependent packages, I suggest you to check the [*guide*](https://github.com/anOsuPlayer/Moona/blob/early_dev/GUIDE.md).

Finally, remember those last things:
* Downloading the full framework package **DOES NOT require any extra feature to be imported**.
* If you plan on downloading scattered packages, **EACH ONE OF THEM has to be imported following [this](#working-with-moona) procedure** (the procedure shows how it works with *the whole framework*, though the steps are the same for each .jar file you download).
* Standalone packages **DO NOT REQUIRE the base package to be installed**.
* Always choose one of the two ways: either download the full package OR make your own custom build. **DO NOT import .jar files if not needed** (for example, do not import extra packages in the full installation or the same package multiple times).

## Versions

For now, there are not any public releases yet (because of the framework being still too rough and under development to be published), but, regardless, I want to show the way I plan on releasing new versions to the public. Below this paragraph you'll find a list of all the phases I plan the framework to go through:

* **early_dev :** The first phase of development, where most of the features will be builded and where the framework will take its initial shape.

* **alpha :** Once Moona will reach a satisfying ammount of features I will proceed with the alpha phase, where I will focus on both adding new features and making the ones added in early_dev more perfect.

* **beta :** The closest version to the full release, where the main focus will be to get as close as possible to a fully functional release.

* **release :** The actual full version which is (or rather, *should hopefylly be*) fully stable and safe to use.

* **snapshots :** Between each major release, snapshots will be released to introduce new features (they'll have the same purpose of alphas and betas, but they'll actually be based on stable code, with the only exception of the newly implemented features).

Once each new release is ready, I will put one of these keywords in the tag, in order to make more clear what kind of version you'll download (an exemple of release tag would be something like: *release-1.0* or *alpha-0.2.1*).

## Contributing to the Project

#### ***NOTE: the project DOES NOT DECLARE ANY KIND OF LICENSE YET. It will be added in the future as soon as I have a better idea of how they work and on what they allow the community to do.***

Even though there is no license yet, I want to give the community a possibility to contribute to the project. Via *pull requests* and *issues* tab, I will kindly accept any kind of advice, idea or constructive opinion!

## Thanks!

Thanks for coming until the end of the file. This might not be the best code you'll find around, but, on the other hand, it's the best I can offer you. This project will never be perfect, but I will try my best to keep up with the expectations (if.. someone will ever expect something out of this framework). For now, though, enjoy Moona... Happy coding!

> *The Developer*
