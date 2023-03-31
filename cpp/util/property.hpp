#pragma once

#ifndef MOONA_PROPERTY
    #define MOONA_PROPERTY

    #include <iostream>

    #include "../base/object.hpp"
    #include "../interfaces/deducible.hpp"
    #include "../interfaces/assignable.hpp"

    namespace moona {

        template <typename T> class Property : public Object<Property<T>>, public Deducible<T>, public Assignable<const T&> {
            protected:
                mutable T value;

                Property() = default;
                Property(const T& value) {
                    this->value = value;
                }

            public:
                ~Property() = default;

                virtual const Property<T>& operator = (const T& ref) const noexcept override final {
                    this->value = ref;

                    return *this;
                }
                operator T() const noexcept override {
                    return this->value;
                }

                const T& evaluate() const noexcept {
                    return this->value;
                }
                void set(const T& value) const noexcept {
                    this->value = value;
                }

                virtual const char* toString() const noexcept override {
                    return "Property";
                }
        };
    }

#endif