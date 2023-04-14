#pragma once

#include "notation.hpp"

namespace moona {

    struct MoonaCore {
        private:
            MoonaCore() = default;
            ~MoonaCore() = default;

            static_field bool on = false;
            static_field bool wasOn = false;

        friend class Moona;
        friend class MoonaSetting;
    };
}