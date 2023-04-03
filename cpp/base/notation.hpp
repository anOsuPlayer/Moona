#pragma once

#define PreMain __attribute__((constructor))
#define PostMain __attribute__((destructor))

#define static_field inline static

#define abstract =0;

#define features >=

#define cpp23 202300L
#define cpp20 202000L
#define cpp17 201700L
#define cpp14 201400L
#define cpp11 201100L
#define cpp03 200300L
#define cpp97 199700L