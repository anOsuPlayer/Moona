#pragma once

#ifndef MOONA_NOTATION
    #define MOONA_NOTATION

    #define PreMain __attribute__((constructor))
    #define PostMain __attribute__((destructor))

    #define staticfield inline static

#endif