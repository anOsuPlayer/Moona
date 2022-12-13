# CHANGELOG

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
