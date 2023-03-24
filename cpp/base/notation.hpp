#pragma once

#ifndef MOONA_NOTATION
    #define MOONA_NOTATION

    #define PreMain __attribute__((constructor))
    #define PostMain __attribute__((destructor))

    #define static_field inline static

    #define JavaImpl extern "C" JNIEXPORT JNICALL

#endif