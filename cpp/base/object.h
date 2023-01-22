#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    namespace moona {

        template <typename O> class Object {

            private:
                long id;

            protected:
                virtual void write(std::ostream& os) const {
                    os << "Object-" << id;
                }

            public:
                Object() {
                    this->id = Moona::generateId();
                }
                virtual ~Object() {
                }

                O* clone() const {
                    return new O();
                }

                friend std::ostream& operator << (std::ostream& os, const Object& o) {
                    o.write(os);
                    return os;
                }

                template <typename T> bool instanceof() const {
                    return std::is_assignable<T, O>();
                }

                unsigned short int getSize() const {
                    return sizeof(O);
                }
        };
    }

#endif