#include <iostream>

/**
 * A repeat(N) loop simply iterates N times a certain instruction.
 */
#define repeat(times) for (ssize_t i = 0; i < times; i++)
/**
 * The range(from, to) loop iterates over the interval between "from" (included) and "to" (not
 * included). You can use the "i" variable to get the number at the current iteration.
 */
#define range(from, to) for (ssize_t i = from; i < to; i++)
/**
 * The rangeinc(from, to, delta) is another version of the range cycle: the thing that changes is
 * the fact that "i" increments of "delta" at each iteration.
 */
#define rangeinc(from, to, delta) for (ssize_t i = from; i < to, i += delta)

/**
 * Alternative syntax for "else if". The use does not change.
 */
#define elif else if
/**
 * Substitutes the ":" operator in foreach loops when it comes to cycling on iterators.
 */
#define in :

/**
 * More friendly way of declaring pointers.
 */
#define ptr *
/**
 * More friendly way of declaring memory addresses of variabless.
 */
#define at &

/**
 * Java-like sintax of declaring public methods or fields in classes. It's either placeable just once
 * at the beginning of a series of public elements or before each one of them.
 */
#define public public:
/**
 * Java-like sintax of declaring protected methods or fields in classes. It's either placeable just once
 * at the beginning of a series of protected elements or before each one of them.
 */
#define protected protected:
/**
 * Java-like sintax of declaring private methods or fields in classes. It's either placeable just once
 * at the beginning of a series of private elements or before each one of them.
 */
#define private private:

/**
 * Alias for virtual elements. It's not meant to entirely replace the keyword, but it's only handy
 * when declaring what Java would interpret as abstract methods.
 */
#define abstract virtual
/**
 * This keyword represents constants. Constant fields inside classes are those which exclusively
 * require readonly access.
 */
#define constant static const

#define getter(field) auto field##_getter() { return this -> field; }

#define setter(field) void field##_setter(auto a) { this -> field = a; }

#define array(LENGTH) [##LENGTH]