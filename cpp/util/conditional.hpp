#pragma once

#include "../base/object.hpp"
#include "property.hpp"

namespace moona {

    struct Conditional : public Object<Conditional>, public Property<bool> {
        protected:
            Conditional();
            Conditional(bool value);

        public:
            ~Conditional() = default;

            void reverse() const noexcept;
            Conditional opposite() const noexcept;
    };
}