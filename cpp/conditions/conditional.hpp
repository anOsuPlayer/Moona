#pragma once

#ifndef MOONA_ASSERTION
    #define MOONA_ASSERTION

    #include "../base/entity.hpp"
    #include "../dll/dllutil.hpp"

    namespace moona {

        struct Conditional : public Entity<Conditional> {
            protected:
                bool value;

                Conditional();
                ~Conditional();

            public:
                operator bool() const;
        };
    }

#endif  