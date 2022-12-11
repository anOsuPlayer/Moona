#pragma once
#ifndef msyntax

    #define msyntax

    #define elif else if

    #define in :

    #define ptr *
    #define at & 

    #define public public:
    #define protected protected:
    #define private private:

    #define abstract virtual
    #define constant static const
    #define extends :

    #define getter(VAR) auto get_##VAR() { return this -> VAR; }
    #define setter(VAR) void set_##VAR(auto n) { this -> VAR = n; }
    
#endif
