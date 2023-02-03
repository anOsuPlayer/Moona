#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include <string>

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

            public:
                static void init();
        };
    }

#endif