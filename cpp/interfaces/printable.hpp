#pragma once

#include <fstream>

#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Printable : public Entity<Printable> {
        protected:
            Printable() = default;
            ~Printable() = default;

        public:
            virtual const char* toString() const noexcept abstract;

            friend std::ostream& operator << (std::ostream& os, const Printable& obj);
    };
}