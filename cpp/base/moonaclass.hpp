#pragma once

#ifndef MOONA_CLASS
    #define MOONA_CLASS

    #include <string>

    namespace moona {

        class Moona {
            private:
                Moona();
                ~Moona();

                static constexpr std::string_view DEFAULT_OBJECT_TOSTRING = "Object";

            public:
                static void init();

                static std::string_view getDefaultObjectToString();
        };
    }

#endif