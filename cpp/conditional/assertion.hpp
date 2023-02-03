#pragma once

#ifndef MOONA_ASSERTION
    #define MOONA_ASSERTION

    #include "../base/entity.hpp"

    namespace moona {

        struct Assertion : public Entity<Assertion> {
            private:
                Assertion(bool value);

                bool value;

            public:
                Assertion();
                ~Assertion();

                operator bool() const;
        };
    }

#endif  