#pragma once

#include "deducible.hpp"
#include "../base/notation.hpp"
#include "../base/entity.hpp"
#include "../util/any.hpp"

namespace moona {

    template <template <typename> typename T> class AnyConvertible {
        protected:
            AnyConvertible() = default;
            ~AnyConvertible() = default;

        public:
            virtual operator T<Any>() const noexcept abstract;
    };
}