# CHANGELOG

## early_dev-0.0.2.1 - The Exceptions Patch
Added:
* .reflection package: a set of tools to operate with java reflection, containing:
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
* .annotation package, containing:
  * Deadlined Annotation: marks those methods which were made useless.
  * Timeless Annotation: marks those methods which require a long period of time to execute.
* .event package, containing:
  * AbstractEvent Abstract Class
  * Action Abstract Class
  * AutoEvent Abstract Class
  * Event Interface
  * EventMode Enum
  * ModalEvent Interface
* EventSpace and EventPlace Classes: they're two special processes which handle events. EventSpaces end when all the Events they contain get executed, meanwhile EventPlaces last until the end of the framework.
* Phases: parts of Moona which handle Processes and Serials just like her.
* Series: a functional class which generates a series of numbers based on a given function.
* Many new Processes:
  * Daemon: a process executing until all the other non-daemon processes are dead.
  * Devil: being it bound to a Phase, terminates its life only once all the non-daemons in that Phase are dead.
  * Worm: it's bound to another Process and keeps going until that doesn't die. It can also run standalone if the host process is null.

Removed:
* Moona.mainStart(Process p) method: it will surely be reimplemented in the future, but will no longer be part of Moona until then.

Fixed:
* Many minor fixes throughout the whole framework.

## early_dev-0.0.1 - The First Release
Added:
* [.moonaFramework package](https://github.com/anOsuPlayer/Moona/wiki/.moonaFramework), containing:
  * [Benchmark class](https://github.com/anOsuPlayer/Moona/wiki/Benchmark)
  * [Moona class](https://github.com/anOsuPlayer/Moona/wiki/Moona)
  * [MoonaHandlingException class](https://github.com/anOsuPlayer/Moona/wiki/MoonaHandlingException)
  * [Natural interface](https://github.com/anOsuPlayer/Moona/wiki/Natural)
  * [ProcessCondition class](https://github.com/anOsuPlayer/Moona/wiki/ProcessCondition)
  * [Serial interface](https://github.com/anOsuPlayer/Moona/wiki/Serial)
  * [Status class](https://github.com/anOsuPlayer/Moona/wiki/Status)
* [.relation package](https://github.com/anOsuPlayer/Moona/wiki/.relation), containing:
  * [Attached interface](https://github.com/anOsuPlayer/Moona/wiki/Attached)
  * [Bindable interface](https://github.com/anOsuPlayer/Moona/wiki/Bindable)
* [.time package](https://github.com/anOsuPlayer/Moona/wiki/.time), containing:
  * [Chrono class](https://github.com/anOsuPlayer/Moona/wiki/Chrono)
  * [Clock class](https://github.com/anOsuPlayer/Moona/wiki/Clock)
  * [Timer class](https://github.com/anOsuPlayer/Moona/wiki/Timer)
* [.util package](https://github.com/anOsuPlayer/Moona/wiki/.util), containing:
  * [Condition class](https://github.com/anOsuPlayer/Moona/wiki/Condition)
  * [Conditional interface](https://github.com/anOsuPlayer/Moona/wiki/Conditional)
  * [Duo class](https://github.com/anOsuPlayer/Moona/wiki/Duo)
  * [FinalDuo class](https://github.com/anOsuPlayer/Moona/wiki/FinalDuo)
  * [IshMap class](https://github.com/anOsuPlayer/Moona/wiki/IshMap)
  * [Reviterable interface](https://github.com/anOsuPlayer/Moona/wiki/Reviterable)
