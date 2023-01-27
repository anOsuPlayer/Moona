#pragma once

#ifndef MOONA_ASSERTION_STATUS
    #define MOONA_ASSERTION_STATUS

    #include "../moona.hpp"

    namespace moona {

        class Assertion : public Object<Assertion> {
            protected:
                bool value;

            public:
                Assertion();
                ~Assertion();

                operator bool() const {
                    return this->value;
                }

                virtual BasicString toString() const override;
                bool equals(const Assertion& ass) const override final;
        };
    }

#endif