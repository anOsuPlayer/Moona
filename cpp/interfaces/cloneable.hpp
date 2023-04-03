#pragma once

#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Cloneable : public Entity<Cloneable> {
        protected:
            Cloneable() = default;
            ~Cloneable() = default;

        public:
            virtual Cloneable* clone() const noexcept abstract;
    };
}