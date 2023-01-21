#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include <iostream>

    namespace moona {

        class Object {
            private:
                long id;

            protected:
                void write(std::ostream& os) const {
                    os << "Object-" << id;
                }

            public:
                Object();
                ~Object();

                Object* clone() const;
                
                template <typename T> bool instanceof() const {
                    return std::is_assignable<T, Object>();
                }

                friend std::ostream& operator << (std::ostream& os, const Object& o) {
                    o.write(os);
                    return os;
                }

                long getId() const {
                    return this->id;
                }

                unsigned short int getSize() const {
                    return sizeof(this);
                }
        };
    }

#endif