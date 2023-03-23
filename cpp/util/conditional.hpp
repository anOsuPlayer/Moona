#pragma once

#ifndef MOONA_ASSERTION
    #define MOONA_ASSERTION

    #include "../base/entity.hpp"
    #include "property.hpp"

    namespace moona {

        struct Conditional : public Entity<Conditional, Property<bool>> {
            protected:
                Conditional();
                Conditional(const bool& value);
                ~Conditional();
        };
    }

#endif  