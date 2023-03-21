#pragma once

#ifndef MOONA_JAVAJVM
    #define MOONA_JAVAJVM

    #include <jni.h>

    #include "../base/moonaclass.hpp"

    namespace moona {

        class JVM {
            private:
                JVM();
                ~JVM();

            public:


            friend class Moona;
        };
    }

#endif