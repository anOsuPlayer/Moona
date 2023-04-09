#pragma once

#include "conditional.hpp"
#include "../base/entity.hpp"

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