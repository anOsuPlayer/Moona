#pragma once
#ifndef mloops

    #define mloops

    #define repeat(VAR, times) for (ssize_t VAR = 0; VAR < times; VAR++)
    #define range(VAR, from, to) for (ssize_t VAR = from; VAR < to; VAR++)
    #define until(condition) while(!(condition))

#endif