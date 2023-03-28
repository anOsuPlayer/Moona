#pragma once

#ifndef MOONA_PROPERTY
    #define MOONA_PROPERTY

    #include <iostream>

    #include "../base/entity.hpp"

    namespace moona {

        template <typename T> struct Property : Entity<Property<T>> {
            protected:
                mutable T value;

                Property() {
                }
                Property(const T& value) {
                    this->value = value;
                }

            public:
                ~Property() {
                }

                operator T() const noexcept {
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