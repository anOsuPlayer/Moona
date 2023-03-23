#pragma once

#ifndef MOONA_PROPERTY
    #define MOONA_PROPERTY

    #include <iostream>

    #include "../base/entity.hpp"

    namespace moona {

        template <typename T> struct Property : Entity<Property<T>> {
            protected:
                T value;

            public:
                Property() {
                }
                Property(const T& value) {
                    this->value = value;
                }
                ~Property() {
                }

                operator T() const {
                    return this->value;
                }

                T& evaluate() const {
                    return this->value;
                }
                void set(const T& value) {
                    this->value = value;
                }
        };
    }

#endif