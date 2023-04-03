#pragma once

#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Equalable : public Entity<Equalable> {
        protected:
            Equalable() = default;
            ~Equalable() = default;
        
        public:
            virtual bool equals(const Equalable& eq) const noexcept;
            
            virtual bool operator == (const Equalable& eq) const noexcept;
    };
}