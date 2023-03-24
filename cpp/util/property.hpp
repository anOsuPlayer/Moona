#pragma once

#ifndef MOONA_PROPERTY
    #define MOONA_PROPERTY

    #include <iostream>

    #include "../base/entity.hpp"

    namespace moona {

        template <typename T> struct Property : Entity<Property<T>> {
            protected:
                T value;

                Property() {
                }
                Property(const T& value) {
                    this->value = value;
                }

            public:
                ~Property() {
                }

                operator T() const {
                    return this->value;
                }

                const T& evaluate() const {
                    return this->value;
                }
                void set(const T& value) {
                    this->value = value;
                }
        };
    }

#endif