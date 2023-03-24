#pragma once

#ifndef MOONA_ASSERTION
    #define MOONA_ASSERTION

    #include "../base/entity.hpp"
    #include "property.hpp"

    namespace moona {

        struct Conditional : public Entity<Conditional>, public Property<bool> {
            protected:
                Conditional();
                Conditional(const bool& value);

            public:
                ~Conditional();

                void reverse();

                Conditional opposite() const;
        };
    }

#endif  