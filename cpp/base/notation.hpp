#pragma once

#ifndef MOONA_NOTATION
    #define MOONA_NOTATION

    #define PreMain __attribute__((constructor))
    #define PostMain __attribute__((destructor))

    #define static_field inline static

    #define JavaImpl extern "C" JNIEXPORT JNICALL

    #define features >=
    #define is / 100 >=

    #define cpp23 2023L
    #define cpp20 2020L
    #define cpp17 2017L
    #define cpp14 2014L
    #define cpp11 2011L
    #define cpp03 2003L
    #define cpp97 1997L

#endif