#pragma once

#include "conditional.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Setting : public Object<Setting>, public Property<bool> {
        public:
            Setting();
            Setting(bool value);
            virtual ~Setting() = default;

            virtual void enable() const;
            virtual void disable() const;

            virtual const char* toString() const noexcept override;
    };
}