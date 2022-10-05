/**
 * @file mloops.h
 * @author Master_ZEr0
 * @brief This header contains some special loops that might fit well in some scenarios.
 */

#ifndef mloops

    #define mloops

    /**
     * @brief A loop which, for a given variable "VAR", starting from 0, repeats until the variable reaches the
     * "times" parameter.
     */
    #define repeat(VAR, times) for (ssize_t VAR = 0; VAR < times; VAR++)
    /**
     * @brief It's a loop which, for a given variable "VAR", starting from the value of the variable "from",
     * iterates until reaching the value of the third parameter "to".
     */
    #define range(VAR, from, to) for (ssize_t VAR = from; VAR < to;  VAR++)
    /**
     * @brief Basically, the opposite of a while loop. Executes the given block of code until it's proven
     * false (it wouldn't be called "until loop" otherwise, would it?). 
     */
    #define until(condition) while(!(condition))

#endif