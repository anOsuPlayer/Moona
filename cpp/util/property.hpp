#pragma once

#include <iostream>

#include "../base/object.hpp"

namespace moona {

    template <typename T> class Property : public Object<Property<T>> {
        protected:
            mutable T value;

            Property() = default;
            Property(const T& value) {
                this->value = value;
            }

        public:
            virtual ~Property() = default;

            virtual const Property<T>& operator = (const T& ref) noexcept final {
                this->value = ref;

                return *this;
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

            virtual const char* toString() const noexcept override {
                return "Property";
            }
    };
}