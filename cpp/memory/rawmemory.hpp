#pragma once

#ifndef MOONA_RAWMEMORY
    #define MOONA_RAWMEMORY

    #include <any>
    #include <iostream>

    #include "../base/entity.hpp"
    #include "../base/object.hpp"
    #include "../exceptions/nullptrexception.hpp"
    #include "../exceptions/indexexception.hpp"

    namespace moona {

        class ChainedPointer : public Entity<ChainedPointer> {
            private:
                mutable void* value;

                explicit ChainedPointer(void* value, ChainedPointer* next);
                explicit ChainedPointer(ChainedPointer* next);
                ChainedPointer();
                
                ~ChainedPointer();

                mutable ChainedPointer* next;

            friend class RawMemory;
        };

        class RawMemory : public Entity<RawMemory> {
            private:
                mutable unsigned int elements = 0;

                mutable ChainedPointer* begin;
                mutable ChainedPointer* end;

            public:
                RawMemory() = default;
                ~RawMemory();

                template <typename T> void allocate(const T& obj) const noexcept {
                    if (this->elements == 0) {
                        this->begin = new ChainedPointer((T*)&obj, nullptr);
                        this->elements++;
                        return;
                    }
                    else if (this->elements == 1) {
                        this->begin->next = new ChainedPointer((T*)&obj, nullptr);
                        this->end = this->begin->next;
                        this->elements++;
                        return;
                    }

                    this->end->next = new ChainedPointer((T*)&obj, nullptr);
                    this->end = this->end->next;

                    this->elements++;
                }
                template <typename T> void allocate(const T* obj) const {
                    if (obj == nullptr) {
                        throw NullPointerException("Cannot allocate a nullptr.");
                    }
                    this->allocate<T>(obj);
                }

                template <typename T> void deallocate(unsigned int at) const {
                    if (at >= this->elements) {
                        throw IndexOutOfBoundsException("The given index goes out of bounds for this RawMemory.");
                    }

                    if (at == 0) {
                        ChainedPointer* oldBeg = this->begin;
                        this->begin = this->begin->next;
                        oldBeg->next = nullptr;

                        delete oldBeg;
                        this->elements--;
                        return;
                    }
                    else {
                        ChainedPointer* ref = this->begin;
                        for (int i = 0; i < at-1; i++) {
                            ref = ref->next;
                        }

                        ChainedPointer* del = ref->next;
                        ref->next = ref->next->next;
                        del->next = nullptr;
                        
                        if (at == this->elements-1) {
                            this->end = ref;
                        }

                        delete del;
                        this->elements--;
                        return;
                    }
                }

                template <typename T> const T& get(unsigned int at) const {
                    if (at >= this->elements) {
                        throw IndexOutOfBoundsException("The given index goes out of bounds for this RawMemory.");
                    }

                    ChainedPointer* ptr = this->begin;
                    for (int i = 0; i < at; i++) {
                        ptr = ptr->next;
                    }
                    return *((T*)(ptr->value));
                };

                int size() const noexcept;

                const unsigned short int thisSize() const noexcept;
        };
    }

#endif