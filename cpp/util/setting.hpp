#pragma once

#include "../base/entity.hpp"
#include "conditional.hpp"

namespace moona {

    class Setting : public Object<Setting>, public Property<bool> {
        public:
            Setting();
            Setting(bool value);
            ~Setting() = default;

            void enable() const noexcept;
            void disable() const noexcept;

            virtual const char* toString() const noexcept override;
    };
}