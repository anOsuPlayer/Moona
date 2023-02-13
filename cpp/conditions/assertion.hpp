#pragma once

#ifndef MOONA_ASSERTION
    #define MOONA_ASSERTION

    #include "../base/entity.hpp"

    namespace moona {

        struct Assertion : public Entity<Assertion> {
            protected:
                bool value;

            public:
                Assertion();
                ~Assertion();

                operator bool() const;
        };
    }

#endif  