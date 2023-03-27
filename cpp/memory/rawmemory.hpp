#pragma once

#ifndef MOONA_RAWMEMORY
    #define MOONA_RAWMEMORY

    #include <any>
    #include <iostream>

    #include "../base/entity.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class ChainedPointer : public Entity<ChainedPointer> {
            private:
                ChainedPointer(std::any* value, ChainedPointer* next, ChainedPointer* prev) {
                    this->value = value;
                    this->next = next;
                    this->prev = prev;
                }
                
                ChainedPointer(ChainedPointer* next, ChainedPointer* prev) : ChainedPointer(nullptr, next, prev) {

                }
                
                ChainedPointer() : ChainedPointer(nullptr, nullptr, nullptr) {

                }
                
                ~ChainedPointer() {
                    delete this->value;
                    delete this->next;
                }

                mutable ChainedPointer* prev;

                void setPrev(ChainedPointer* prev) const {
                    this->prev = prev;
                }

                mutable ChainedPointer* next;

                void setNext(ChainedPointer* next) const {
                    this->next = next;
                }

                mutable std::any* value;

            public:
                const std::any& get() const {
                    return *this->value;
                }

                const ChainedPointer* getNext() const {
                    return this->next;
                }
                const ChainedPointer* getPrev() const {
                    return this->prev;
                }

            friend class RawMemory;
        };

        class RawMemory : public Object<RawMemory> {
            private:
                RawMemory(ChainedPointer* begin, ChainedPointer* end);

                mutable unsigned int size = 0;

                mutable ChainedPointer* begin;
                mutable ChainedPointer* end;

            public:
                RawMemory();
                ~RawMemory();

                template <typename T> void allocate(const T& obj) const {
                    this->end->prev->next = new ChainedPointer(new std::any(obj), this->end, this->end->prev);
                    this->end->prev = this->end->prev->next;

                    this->size++;
                };
                template <typename T> void allocate(const T* obj) const {
                    this->allocate(*obj);
                };

                template <typename T> const T& get(int at) const {
                    ChainedPointer* ptr = this->begin;
                    for (int i = 0; i <= at; i++) {
                        ptr = ptr->next;
                    }
                    return *std::any_cast<T>(ptr->value);
                };

                int getSize() const;

                virtual RawMemory* clone() const override final {
                    return new RawMemory(this->begin, this->end);
                }
        };
    }

#endif