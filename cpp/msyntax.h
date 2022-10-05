/**
 * @file msyntax.h
 * @author Master_ZEr0
 * @brief This header handles specific syntaxes and constructs to make C++'s awful syntax more readable and easy
 * to understand.
 */

#ifndef msyntax

    #define msyntax

    /**
     * @brief Alternative for "else if" statement. 
     */
    #define elif else if
    /**
     * @brief Stands for the ":" operator in foreach cycles.
     */
    #define in :

    /**
     * @brief A more readable alias of the "*" indicating pointers.
     */
    #define ptr *
    /**
     * @brief A more readable alias of the "&" indicating memory addresses.
     */
    #define at & 

    /**
     * @brief Defines public members inside classes. Both usable just once for multiple parameters or multiple
     * times for better readability.
     */
    #define public public:
    /**
     * @brief Defines protected members inside classes. Both usable just once for multiple parameters or multiple
     * times for better readability.
     */
    #define protected protected:
    /**
     * @brief Defines private members inside classes. Both usable just once for multiple parameters or multiple
     * times for better readability.
     */
    #define private private:

    /**
     * @brief Just an alias of the "virtual" keyword. Makes the syntax closer to Java.
     */
    #define abstract virtual
    /**
     * @brief This modifier identifies those variables considered as "constants".
     */
    #define constant static const
    /**
     * @brief Custom keyword for when classes extend each others. Makes the syntax closer to Java.
     */
    #define extends :

    /**
     * @brief Builds a getter for the given variable. The name of the getter is structured as follows "get_VAR",
     * where "VAR" is the name of your variable.
     */
    #define getter(VAR) auto get_##VAR() { return this -> VAR; }
    /**
     * @brief Builds a setter for the given variable. The name of the setter is structured as follows "set_VAR",
     * where "VAR" is the name of your variable.
     */
    #define setter(VAR) void set_##VAR(auto n) { this -> VAR = n; }

#endif
