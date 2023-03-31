#pragma once

#ifndef MOONA_PROPERTY
    #define MOONA_PROPERTY

    #include <iostream>

    #include "../base/object.hpp"
    #include "../interfaces/deducible.hpp"

    namespace moona {

        template <typename T> class Property : public Object<Property<T>>, public Deducible<T> {
            protected:
                mutable T value;

                Property() = default;
                Property(const T& value) {
                    this->value = value;
                }

            public:
                ~Property() = default;

                operator T() const noexcept override {
                    return this->value;
                }

                const T& evaluate() const noexcept {
                    return this->value;
                }
                void set(const T& value) const noexcept {
                    this->value = value;
                }
        };
    }

#endif